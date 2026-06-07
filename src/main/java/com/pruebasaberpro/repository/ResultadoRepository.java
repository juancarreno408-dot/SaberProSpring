package com.pruebasaberpro.repository;

import com.pruebasaberpro.model.ResultadoSaberPro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ResultadoRepository extends JpaRepository<ResultadoSaberPro, Long> {
    Optional<ResultadoSaberPro> findByEstudianteId(Long estudianteId);
    List<ResultadoSaberPro> findTop5ByPuntajeIsNotNullOrderByPuntajeDesc();
}
