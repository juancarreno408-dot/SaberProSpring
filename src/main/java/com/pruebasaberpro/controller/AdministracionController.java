package com.pruebasaberpro.controller;

import com.pruebasaberpro.model.Usuario;
import com.pruebasaberpro.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdministracionController {

    private final UsuarioService usuarioService;

    public AdministracionController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard-admin";
    }

    @GetMapping("/create-{rol}")
    public String mostrarFormulario(@PathVariable String rol, Model model) {
        model.addAttribute("usuario", new Usuario());
        // Pasamos el rol tal cual viene de la URL
        model.addAttribute("rol", rol.toUpperCase());
        return "admin/formularioUsuario";
    }

    @PostMapping("/save-usuario")
    public String guardar(@ModelAttribute Usuario usuario, 
                          @RequestParam("rolSeleccionado") String rol) {
        // El controlador envía el String 'rol' al servicio
        usuarioService.registrarUsuario(usuario, rol);
        return "redirect:/admin/dashboard";
    }
}