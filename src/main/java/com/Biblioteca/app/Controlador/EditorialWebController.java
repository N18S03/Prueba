package com.Biblioteca.app.Controlador;

import com.Biblioteca.app.Entidad.Editorial;
import com.Biblioteca.app.Repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editoriales")
public class EditorialWebController {

    @Autowired
    private EditorialRepository editorialRepositorio;

    @GetMapping("/buscar")
    public String buscarEditorial(@RequestParam("id") String id, Model model) {
        Editorial editorial = editorialRepositorio.findById(id).orElse(null);
        model.addAttribute("editorial", editorial);
        return "mostrarEditorial";
    }
    
    @GetMapping("/informe/{id}")
    public String mostrarInformeEditorial(@PathVariable("id") String id, Model model) {
        Editorial editorial = editorialRepositorio.findById(id).orElse(null);
        model.addAttribute("editorial", editorial);
        return "informeEditorial"; // Nombre del HTML que muestra el informe de la editorial
    }
    
    @GetMapping("/")
    public String getAllEditoriales(Model model) {
        model.addAttribute("editoriales", editorialRepositorio.findAll());
        return "editoriales";
    }

    @GetMapping("/add")
    public String addEditorialForm(Model model) {
        model.addAttribute("editorial", new Editorial());
        return "addEditorial";
    }

    @PostMapping("/add")
    public String addEditorialSubmit(@ModelAttribute Editorial editorial) {
        editorialRepositorio.save(editorial);
        return "redirect:/editoriales/";
    }

    @GetMapping("/edit/{id}")
    public String editEditorialForm(@PathVariable("id") String id, Model model) {
        Editorial editorial = editorialRepositorio.findById(id).orElse(null);
        model.addAttribute("editorial", editorial);
        return "editEditorial";
    }

    @PostMapping("/edit")
    public String editEditorialSubmit(@ModelAttribute Editorial editorial) {
        Editorial existingEditorial = editorialRepositorio.findById(editorial.getId()).orElse(null);

        if (existingEditorial != null) {
            existingEditorial.setNombre(editorial.getNombre());
            existingEditorial.setCorreo(editorial.getCorreo());
            existingEditorial.setNumeroTel(editorial.getNumeroTel());

            editorialRepositorio.save(existingEditorial);
        }

        return "redirect:/editoriales/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEditorial(@PathVariable("id") String id) {
        editorialRepositorio.deleteById(id);
        return "redirect:/editoriales/";
    }
}
