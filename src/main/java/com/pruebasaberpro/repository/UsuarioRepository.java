package com.pruebasaberpro.repository;

import com.pruebasaberpro.model.Rol;
import com.pruebasaberpro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    
    // Agrega esta línea para evitar la llave duplicada
    Optional<Usuario> findByEstudianteId(Long estudianteId);
    
    long countByRol(Rol rol);
}