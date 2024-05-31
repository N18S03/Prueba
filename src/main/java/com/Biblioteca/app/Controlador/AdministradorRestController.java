package com.Biblioteca.app.Controlador;


import com.Biblioteca.app.Entidad.Administrador;
import com.Biblioteca.app.Repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorRestController {

    @Autowired
    private AdministradorRepository administradorRepositorio;

    @GetMapping("/")
    public List<Administrador> getAllAdministradores() {
        return administradorRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Administrador getAdministradorById(@PathVariable String id) {
        return administradorRepositorio.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Administrador addAdministrador(@RequestBody Administrador administrador) {
        return administradorRepositorio.save(administrador);
    }

    @PutMapping("/{id}")
    public Administrador updateAdministrador(@PathVariable String id, @RequestBody Administrador administrador) {
        administrador.setId(id);
        return administradorRepositorio.save(administrador);
    }

    @DeleteMapping("/{id}")
    public void deleteAdministrador(@PathVariable String id) {
        administradorRepositorio.deleteById(id);
    }
}
