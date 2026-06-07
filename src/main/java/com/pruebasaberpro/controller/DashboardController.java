package com.pruebasaberpro.controller;

import com.pruebasaberpro.model.Rol;
import com.pruebasaberpro.model.Estudiante;
import com.pruebasaberpro.repository.UsuarioRepository;
import com.pruebasaberpro.repository.EstudianteRepository;
import com.pruebasaberpro.repository.ResultadoRepository;
import com.pruebasaberpro.service.InformeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final InformeService informeService; 
    private final UsuarioRepository usuarioRepository;
    private final EstudianteRepository estudianteRepository;
    private final ResultadoRepository resultadoRepository;

    public DashboardController(InformeService informeService, 
                               UsuarioRepository usuarioRepository,
                               EstudianteRepository estudianteRepository,
                               ResultadoRepository resultadoRepository) { 
        this.informeService = informeService; 
        this.usuarioRepository = usuarioRepository;
        this.estudianteRepository = estudianteRepository;
        this.resultadoRepository = resultadoRepository;
    }

    @GetMapping("/") public String home() { return "redirect:/dashboard"; }
    @GetMapping("/login") public String login() { return "login"; }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        
        // Lógica específica para Estudiante
        if (hasRole(authentication, "ROLE_ESTUDIANTE")) {
            // Obtenemos el registro (username) del estudiante logueado
            String registro = authentication.getName();
            
            // Buscamos por Registro, no por documento
            Estudiante estudiante = estudianteRepository.findByNumeroRegistro(registro).orElse(null);
            
            if (estudiante != null) {
                model.addAttribute("estudiante", estudiante);
                // Usamos la relación definida en el modelo @OneToOne
                model.addAttribute("resultado", estudiante.getResultado());
            }
            return "dashboard-estudiante";
        }

        // Datos comunes para los demás roles
        model.addAttribute("totalEstudiantes", informeService.totalEstudiantes());
        model.addAttribute("promedioGlobal", informeService.promedioGlobal());
        model.addAttribute("aprobados", informeService.totalAprobados());

        // Redirecciones
        if (hasRole(authentication, "ROLE_ADMINISTRADOR")) {
            model.addAttribute("topResultados", informeService.topResultados());
            model.addAttribute("docentes", usuarioRepository.countByRol(Rol.DOCENTE));
            return "dashboard-admin";
        }
        
        if (hasRole(authentication, "ROLE_COORDINACION")) {
            return "dashboard-coordinacion";
        }
        
        if (hasRole(authentication, "ROLE_DOCENTE")) {
            return "dashboard-docente";
        }

        return "redirect:/login";
    }

    private boolean hasRole(Authentication authentication, String role) { 
        return authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(role)); 
    }
}