package com.pruebasaberpro.controller;

import com.pruebasaberpro.repository.EstudianteRepository;
import com.pruebasaberpro.repository.ResultadoRepository;
import com.pruebasaberpro.service.InformeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reports")
public class ReportController {
    private final InformeService informeService; private final EstudianteRepository estudianteRepository; private final ResultadoRepository resultadoRepository;
    public ReportController(InformeService informeService, EstudianteRepository estudianteRepository, ResultadoRepository resultadoRepository) { this.informeService = informeService; this.estudianteRepository = estudianteRepository; this.resultadoRepository = resultadoRepository; }
    @GetMapping public String general(Model model) {
        model.addAttribute("totalEstudiantes", informeService.totalEstudiantes()); model.addAttribute("promedioGlobal", informeService.promedioGlobal()); model.addAttribute("niveles", informeService.nivelesGlobales()); model.addAttribute("competencias", informeService.promediosCompetencias()); model.addAttribute("topResultados", informeService.topResultados()); return "reports/general";
    }
    @GetMapping("/students") public String detailed(Model model) { model.addAttribute("estudiantes", estudianteRepository.findAll()); return "reports/detail"; }
    @GetMapping("/students/{id}") public String student(@PathVariable Long id, Model model) { var estudiante = estudianteRepository.findById(id).orElseThrow(); model.addAttribute("estudiante", estudiante); model.addAttribute("resultado", resultadoRepository.findByEstudianteId(id).orElse(null)); return "reports/student-result"; }
}
