package com.pruebasaberpro.service;

import com.pruebasaberpro.model.CompetenciaResumen;
import com.pruebasaberpro.model.NivelResumen;
import com.pruebasaberpro.model.ResultadoSaberPro;
import com.pruebasaberpro.repository.EstudianteRepository;
import com.pruebasaberpro.repository.ResultadoRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InformeService {
    private final EstudianteRepository estudianteRepository;
    private final ResultadoRepository resultadoRepository;
    public InformeService(EstudianteRepository estudianteRepository, ResultadoRepository resultadoRepository) { this.estudianteRepository = estudianteRepository; this.resultadoRepository = resultadoRepository; }
    public long totalEstudiantes() { return estudianteRepository.count(); }
    public long totalAprobados() { return estudianteRepository.countByAprobadoSaberProTrue(); }
    public double promedioGlobal() { return resultadoRepository.findAll().stream().map(ResultadoSaberPro::getPuntaje).filter(Objects::nonNull).mapToInt(Integer::intValue).average().orElse(0); }
    public List<ResultadoSaberPro> topResultados() { return resultadoRepository.findTop5ByPuntajeIsNotNullOrderByPuntajeDesc(); }
    public List<NivelResumen> nivelesGlobales() {
        Map<String, Long> conteo = resultadoRepository.findAll().stream().map(ResultadoSaberPro::getNivelGlobal).filter(Objects::nonNull).collect(Collectors.groupingBy(n -> n, Collectors.counting()));
        return conteo.entrySet().stream().map(e -> new NivelResumen(e.getKey(), e.getValue())).sorted(Comparator.comparing(NivelResumen::nivel)).toList();
    }
    public List<CompetenciaResumen> promediosCompetencias() {
        var r = resultadoRepository.findAll();
        return List.of(
            new CompetenciaResumen("Comunicacion escrita", promedio(r.stream().map(ResultadoSaberPro::getComunicacionEscrita).toList())),
            new CompetenciaResumen("Razonamiento cuantitativo", promedio(r.stream().map(ResultadoSaberPro::getRazonamientoCuantitativo).toList())),
            new CompetenciaResumen("Lectura critica", promedio(r.stream().map(ResultadoSaberPro::getLecturaCritica).toList())),
            new CompetenciaResumen("Competencias ciudadanas", promedio(r.stream().map(ResultadoSaberPro::getCompetenciasCiudadanas).toList())),
            new CompetenciaResumen("Ingles", promedio(r.stream().map(ResultadoSaberPro::getIngles).toList())),
            new CompetenciaResumen("Formulacion proyectos", promedio(r.stream().map(ResultadoSaberPro::getFormulacionProyectos).toList())),
            new CompetenciaResumen("Pensamiento cientifico", promedio(r.stream().map(ResultadoSaberPro::getPensamientoCientifico).toList())),
            new CompetenciaResumen("Diseno de software", promedio(r.stream().map(ResultadoSaberPro::getDisenoSoftware).toList()))
        );
    }
    private double promedio(List<Integer> valores) { return valores.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).average().orElse(0); }
}
