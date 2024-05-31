package com.Biblioteca.app.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Biblioteca.app.Entidad.Administrador;
import com.Biblioteca.app.Entidad.Usuario;
import com.Biblioteca.app.Repository.LibroRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

	
    @Autowired
    private LibroRepository libroRepositorio;
	
    @GetMapping("/")
    public String getAllLibros(Model model) {
    	model.addAttribute("libros", libroRepositorio.findAll());
        return "index"; 
    }
    
    @GetMapping("/indexAdmin")
    public String indexAdmin(Model model, HttpSession session) {
        Administrador administrador = (Administrador) session.getAttribute("administrador");
        if (administrador != null) {
            model.addAttribute("administrador", administrador);
        }
        model.addAttribute("libros", libroRepositorio.findAll());
        return "indexAdmin";
    }


    @GetMapping("/indexUser")
    public String indexUser(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        }
        model.addAttribute("libros", libroRepositorio.findAll());
        return "indexUser";
    }
    
    @GetMapping("/nuestrosObjetivos")
    public String indexObjetivos() {
        return "nuestrosObjetivos";
    }
}