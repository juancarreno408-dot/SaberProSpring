package com.pruebasaberpro.security;

import com.pruebasaberpro.model.Rol;
import com.pruebasaberpro.model.Usuario;
import com.pruebasaberpro.repository.UsuarioRepository;
import com.pruebasaberpro.repository.EstudianteRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final EstudianteRepository estudianteRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserDetailsService(UsuarioRepository usuarioRepository, 
                                 EstudianteRepository estudianteRepository,
                                 PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.estudianteRepository = estudianteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // 1. PRIORIDAD: Buscar en la tabla de usuarios (Admin, Docentes, Coordinadores)
        var usuarioOpt = usuarioRepository.findByUsername(username);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), 
                    true, true, true, 
                    List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name())));
        }

        // 2. SI NO ES USUARIO DE BD, buscamos si es un estudiante (registro automático)
        var estudianteOpt = estudianteRepository.findByNumeroRegistro(username);
        if (estudianteOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario o estudiante no encontrado: " + username);
        }
        
        var estudiante = estudianteOpt.get();
        var usuarioEstudianteOpt = usuarioRepository.findByEstudianteId(estudiante.getId());
        
        Usuario usuario;
        if (usuarioEstudianteOpt.isPresent()) {
            usuario = usuarioEstudianteOpt.get();
            // Validación de formato BCrypt para estudiantes
            if (!usuario.getPassword().startsWith("$2a$")) {
                usuario.setPassword(passwordEncoder.encode(estudiante.getNumeroDocumento()));
                usuarioRepository.save(usuario);
            }
        } else {
            // Crear usuario estudiante al vuelo
            usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setPassword(passwordEncoder.encode(estudiante.getNumeroDocumento()));
            usuario.setRol(Rol.ESTUDIANTE);
            usuario.setEnabled(true);
            usuario.setEstudiante(estudiante);
            // Usamos el nombre completo del estudiante
            usuario.setNombre(estudiante.getNombreCompleto());
            usuarioRepository.save(usuario);
        }

        return new User(
            usuario.getUsername(), 
            usuario.getPassword(), 
            usuario.isEnabled(), 
            true, true, true, 
            List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()))
        );
    }
}