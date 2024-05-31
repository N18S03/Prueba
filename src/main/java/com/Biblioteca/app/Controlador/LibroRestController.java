package com.Biblioteca.app.Controlador;

import com.Biblioteca.app.Entidad.Libro;
import com.Biblioteca.app.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroRestController {

    @Autowired
    private LibroRepository libroRepositorio;

    @GetMapping("/")
    public List<Libro> getAllLibros() {
        return libroRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable String id) {
        return libroRepositorio.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Libro addLibro(@RequestBody Libro libro) {
        return libroRepositorio.save(libro);
    }

    @PutMapping("/{id}")
    public Libro updateLibro(@PathVariable String id, @RequestBody Libro libro) {
        libro.setId(id);
        return libroRepositorio.save(libro);
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable String id) {
        libroRepositorio.deleteById(id);
    }
}
