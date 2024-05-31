package com.Biblioteca.app.Controlador;

import com.Biblioteca.app.Entidad.Administrador;
import com.Biblioteca.app.Entidad.Usuario;
import com.Biblioteca.app.Repository.AdministradorRepository;
import com.Biblioteca.app.Repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

import com.Biblioteca.app.Repository.LibroRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "correo") String correo, @RequestParam(name = "contraseña") String contraseña, Model model, HttpSession session) {
        Administrador administrador = administradorRepository.findByCorreoAndContraseña(correo, contraseña);
        Usuario usuario = usuarioRepository.findByCorreoAndContraseña(correo, contraseña);

        if (administrador != null) {
            model.addAttribute("administrador", administrador);
            session.setAttribute("administrador", administrador); // Agrega esta línea
            return "redirect:/indexAdmin"; // Redireccionar a la ruta del IndexController
        } else if (usuario != null) {
            model.addAttribute("usuario", usuario);
            session.setAttribute("usuario", usuario); // Agrega esta línea
            return "redirect:/indexUser"; // Redireccionar a la ruta del IndexController
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos. ¿No tienes una cuenta? <a href='/registro'>Regístrate aquí</a>");
            return "login";
        }
    }
}
