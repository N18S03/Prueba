package com.Biblioteca.app.Controlador;

import com.Biblioteca.app.Entidad.Prestamo;
import com.Biblioteca.app.Repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoRestController {

    @Autowired
    private PrestamoRepository prestamoRepositorio;

    @GetMapping("/")
    public List<Prestamo> getAllPrestamos() {
        return prestamoRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Prestamo getPrestamoById(@PathVariable String id) {
        return prestamoRepositorio.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Prestamo addPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoRepositorio.save(prestamo);
    }

    @PutMapping("/{id}")
    public Prestamo updatePrestamo(@PathVariable String id, @RequestBody Prestamo prestamo) {
        prestamo.setId(id);
        return prestamoRepositorio.save(prestamo);
    }

    @DeleteMapping("/{id}")
    public void deletePrestamo(@PathVariable String id) {
        prestamoRepositorio.deleteById(id);
    }
}
