package com.pruebasaberpro.controller;

import com.pruebasaberpro.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estudiante")
public class EstudiantePortalController {

    private final UsuarioRepository usuarioRepository;

    public EstudiantePortalController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public String portal(Authentication authentication, Model model) {
        var usuarioOpt = usuarioRepository.findByUsername(authentication.getName());
        
        if (usuarioOpt.isEmpty()) return "redirect:/login";

        var usuario = usuarioOpt.get();
        model.addAttribute("usuario", usuario);
        
        // Verificación segura para evitar errores de puntero nulo
        if (usuario.getEstudiante() != null) {
            model.addAttribute("estudiante", usuario.getEstudiante());
            model.addAttribute("resultado", usuario.getEstudiante().getResultado());
        }
        
        return "dashboard-estudiante";
    }
}