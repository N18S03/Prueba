package com.Biblioteca.app.Controlador;


import com.Biblioteca.app.Entidad.Usuario;
import com.Biblioteca.app.Exception.NotFoundException;
import com.Biblioteca.app.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @GetMapping("/")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable String id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioRepositorio.save(usuario);
    }

    @DeleteMapping("/{id}")
    public Usuario deleteUsuario(@PathVariable String id) {
    	Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new NotFoundException("usuario no encontrado"));
        usuarioRepositorio.deleteById(id);
        return usuario;
    }
}
