package com.Biblioteca.app.Controlador;

import com.Biblioteca.app.Entidad.Editorial;
import com.Biblioteca.app.Entidad.Libro;
import com.Biblioteca.app.Repository.EditorialRepository;
import com.Biblioteca.app.Repository.LibroRepository;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroWebController {

    @Autowired
    private LibroRepository libroRepositorio;
    @Autowired
    private EditorialRepository editorialesRepository;

    @GetMapping("/buscar")
    public String buscarLibro(@RequestParam String id, Model model) {
        Libro libro = libroRepositorio.findById(id).orElse(null);
        model.addAttribute("libro", libro);
        return "mostrarLibro";
    }
    
    @GetMapping("/informe/{id}")
    public String mostrarInformeLibro(@PathVariable String id, Model model) {
        Libro libro = libroRepositorio.findById(id).orElse(null);
        model.addAttribute("libro", libro);
        return "informeLibro";
    }
    
    @GetMapping("/")
    public String getAllLibros(Model model) {
        model.addAttribute("libros", libroRepositorio.findAll());
        return "libros";
    }

    @GetMapping("/add")
    public String addLibroForm(Model model) {
		List<Editorial> editorialesList = editorialesRepository.findAll();
		model.addAttribute("editorialesList", editorialesList);
        model.addAttribute("libro", new Libro());
        return "addLibro";
    }

    @PostMapping("/add")
    public String addLibroSubmit(@ModelAttribute Libro libro) {
        libroRepositorio.save(libro);
        return "redirect:/indexAdmin";
    }

    @GetMapping("/edit/{id}")
    public String editLibroForm(@PathVariable("id") String id, Model model) {
        List<Editorial> editorialesList = editorialesRepository.findAll();
        model.addAttribute("editorialesList", editorialesList);
        Libro libro = libroRepositorio.findById(id).orElse(null);
        model.addAttribute("libro", libro);
        return "editLibro";
    }

    @PostMapping("/edit")
    public String editLibroSubmit(@ModelAttribute Libro libro) {
        Libro existingLibro = libroRepositorio.findById(libro.getId()).orElse(null);
        if (existingLibro != null) {
            existingLibro.setNombre(libro.getNombre());
            existingLibro.setReseña(libro.getReseña());
            existingLibro.setAutor(libro.getAutor());
            existingLibro.setCantidad(libro.getCantidad());
            existingLibro.setEditorial(libro.getEditorial());
            libroRepositorio.save(existingLibro);
        }
        return "redirect:/indexAdmin";
    }

    @GetMapping("/delete/{id}")
    public String deleteLibro(@PathVariable("id") String id) {
        libroRepositorio.deleteById(id);
        return "redirect:/indexAdmin";
    }
    
    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session, Model model) {
        session.removeAttribute("administrador");
        List<Libro> libros = libroRepositorio.findAll();
        model.addAttribute("libros", libros);
        return "redirect:/"; 
    }
}
