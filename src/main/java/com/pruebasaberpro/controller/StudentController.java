package com.pruebasaberpro.controller;

import com.pruebasaberpro.model.Estudiante;
import com.pruebasaberpro.repository.EstudianteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final EstudianteRepository estudianteRepository;
    public StudentController(EstudianteRepository estudianteRepository) { this.estudianteRepository = estudianteRepository; }

    @GetMapping
    public String list(@RequestParam(defaultValue = "") String q, Model model) {
        var estudiantes = q.isBlank() ? estudianteRepository.findAll() : estudianteRepository.findByPrimerApellidoContainingIgnoreCaseOrNumeroRegistroContainingIgnoreCase(q, q);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("q", q);
        return "students/list";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "students/form";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("estudiante", estudianteRepository.findById(id).orElseThrow());
        return "students/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Estudiante form, BindingResult result) {
        if (result.hasErrors()) return "students/form";
        Estudiante estudiante = form.getId() == null ? form : estudianteRepository.findById(form.getId()).orElseThrow();
        estudiante.setTipoDocumento(form.getTipoDocumento());
        estudiante.setNumeroDocumento(form.getNumeroDocumento());
        estudiante.setPrimerNombre(form.getPrimerNombre());
        estudiante.setSegundoNombre(form.getSegundoNombre());
        estudiante.setPrimerApellido(form.getPrimerApellido());
        estudiante.setSegundoApellido(form.getSegundoApellido());
        estudiante.setCorreoElectronico(form.getCorreoElectronico());
        estudiante.setNumeroTelefonico(form.getNumeroTelefonico());
        estudiante.setNumeroRegistro(form.getNumeroRegistro());
        estudiante.setAprobadoSaberPro(form.isAprobadoSaberPro());
        estudianteRepository.save(estudiante);
        return "redirect:/students";
    }
}
