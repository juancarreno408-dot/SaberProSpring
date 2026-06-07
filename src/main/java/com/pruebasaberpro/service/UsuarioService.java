package com.pruebasaberpro.service;

import com.pruebasaberpro.model.Rol;
import com.pruebasaberpro.model.Usuario;
import com.pruebasaberpro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void registrarUsuario(Usuario usuario, String rolStr) {
        // Normalizamos el string de entrada
        String rolLimpio = rolStr.toUpperCase().trim();
        
        // Lógica de mapeo para evitar el error "No enum constant"
        // Esto traduce lo que llega del formulario a lo que espera tu Enum
        if (rolLimpio.equals("COORDINADOR")) {
            rolLimpio = "COORDINACION";
        }

        // Asignamos el rol al usuario
        usuario.setRol(Rol.valueOf(rolLimpio));
        
        // Configuraciones adicionales
        usuario.setPassword("{noop}" + usuario.getPassword());
        usuario.setEnabled(true);
        
        // Guardamos
        usuarioRepository.save(usuario);
    }
}