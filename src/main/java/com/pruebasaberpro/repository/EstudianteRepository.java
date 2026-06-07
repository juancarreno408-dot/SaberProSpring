package com.pruebasaberpro.repository;

import com.pruebasaberpro.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    
    // Método agregado para buscar por documento (para el login/dashboard)
    Estudiante findByNumeroDocumento(String numeroDocumento);
    
    Optional<Estudiante> findByNumeroRegistro(String numeroRegistro);
    
    List<Estudiante> findByPrimerApellidoContainingIgnoreCaseOrNumeroRegistroContainingIgnoreCase(String apellido, String registro);
    
    long countByAprobadoSaberProTrue();
}