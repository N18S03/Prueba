package com.Biblioteca.app.Controlador;

import com.Biblioteca.app.Entidad.Administrador;
import com.Biblioteca.app.Entidad.Usuario;
import com.Biblioteca.app.Repository.AdministradorRepository;
import com.Biblioteca.app.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/registro")
    public String showRegistroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("administrador", new Administrador());
        return "registro";
    }

    @PostMapping("/registroUsuario")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        usuarioRepository.save(usuario);
        model.addAttribute("mensaje", "Usuario registrado exitosamente");
        return "login";
    }

    @PostMapping("/registroAdministrador")
    public String registrarAdministrador(@ModelAttribute Administrador administrador, Model model) {
        administradorRepository.save(administrador);
        model.addAttribute("mensaje", "Administrador registrado exitosamente");
        return "login";
    }
}
