package com.Biblioteca.app.Controlador;

import com.Biblioteca.app.Entidad.Libro;
import com.Biblioteca.app.Entidad.Usuario;
import com.Biblioteca.app.Repository.LibroRepository;
import com.Biblioteca.app.Repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioWebController {

    @Autowired
    private UsuarioRepository usuarioRepositorio;
    
    @Autowired
    private LibroRepository libroRepositorio;


    @GetMapping("/buscar")
    public String buscarUsuario(@RequestParam String id, Model model) {
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "mostrarUsuario";
    }
    
    @GetMapping("/informe/{id}")
    public String mostrarInformeUsuario(@PathVariable String id, Model model) {
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "informeUsuario"; // Nombre del HTML que muestra el informe del usuario
    }
    
    @GetMapping("/")
    public String getAllUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepositorio.findAll());
        return "usuarios";
    }

    @GetMapping("/add")
    public String addUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "addUsuario";
    }

    @PostMapping("/add")
    public String addUsuarioSubmit(@ModelAttribute Usuario usuario) {
        usuarioRepositorio.save(usuario);
        return "redirect:/usuarios/";
    }

    @GetMapping("/edit/{id}")
    public String editUsuarioForm(@PathVariable("id") String id, Model model) {
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "editUsuarios"; // Nombre del HTML que muestra el formulario de edición del usuario
    }

    @PostMapping("/edit")
    public String editUsuarioSubmit(@ModelAttribute("usuario") Usuario usuario,
                                    @RequestParam("id") String id) {
        Usuario existingUsuario = usuarioRepositorio.findById(id).orElse(null);

        if (existingUsuario != null) {
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setApellido(usuario.getApellido());
            existingUsuario.setTelefono(usuario.getTelefono());
            existingUsuario.setCorreo(usuario.getCorreo());
            existingUsuario.setContraseña(usuario.getContraseña());

            usuarioRepositorio.save(existingUsuario);
        }

        return "redirect:/indexUser";
    }

    @GetMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable("id") String id) {
        usuarioRepositorio.deleteById(id);
        return "redirect:/usuarios/";
    }
    
    @GetMapping("/nuestrosObjetivosUsuario")
    public String mostrarObjetivosUsuario(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        }
        return "nuestrosObjetivosUsuario";
    }
    
    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session, Model model) {
        session.removeAttribute("usuario");
        List<Libro> libros = libroRepositorio.findAll();
        model.addAttribute("libros", libros);
        return "redirect:/"; 
    }

}
