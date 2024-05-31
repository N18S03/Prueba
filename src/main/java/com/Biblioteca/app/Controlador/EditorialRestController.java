package com.Biblioteca.app.Controlador;


import com.Biblioteca.app.Entidad.Editorial;
import com.Biblioteca.app.Repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
public class EditorialRestController {

    @Autowired
    private EditorialRepository editorialRepositorio;

    @GetMapping("/")
    public List<Editorial> getAllEditoriales() {
        return editorialRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Editorial getEditorialById(@PathVariable String id) {
        return editorialRepositorio.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Editorial addEditorial(@RequestBody Editorial editorial) {
        return editorialRepositorio.save(editorial);
    }

    @PutMapping("/{id}")
    public Editorial updateEditorial(@PathVariable String id, @RequestBody Editorial editorial) {
        editorial.setId(id);
        return editorialRepositorio.save(editorial);
    }

    @DeleteMapping("/{id}")
    public void deleteEditorial(@PathVariable String id) {
        editorialRepositorio.deleteById(id);
    }
}