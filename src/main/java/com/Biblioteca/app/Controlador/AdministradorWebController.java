package com.Biblioteca.app.Controlador;

import com.Biblioteca.app.Entidad.Administrador;
import com.Biblioteca.app.Repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/administradores")
public class AdministradorWebController {

    @Autowired
    private AdministradorRepository administradorRepositorio;

    @GetMapping("/buscar")
    public String buscarAdministrador(@RequestParam String id, Model model) {
        Administrador administrador = administradorRepositorio.findById(id).orElse(null);
        model.addAttribute("administrador", administrador);
        return "mostrarAdministrador";
    }
    
    @GetMapping("/informe/{id}")
    public String mostrarInformeAdministrador(@PathVariable String id, Model model) {
        Administrador administrador = administradorRepositorio.findById(id).orElse(null);
        model.addAttribute("administrador", administrador);
        return "informeAdministrador"; // Nombre del HTML que muestra el informe del administrador
    }
    
    @GetMapping("/")
    public String getAllAdministradores(Model model) {
        model.addAttribute("administradores", administradorRepositorio.findAll());
        return "administradores";
    }

    @GetMapping("/add")
    public String addAdministradorForm(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "addAdministrador";
    }

    @PostMapping("/add")
    public String addAdministradorSubmit(@ModelAttribute Administrador administrador) {
        administradorRepositorio.save(administrador);
        return "redirect:/administradores/";
    }

    @GetMapping("/edit/{id}")
    public String editAdministradorForm(@PathVariable("id") String id, Model model) {
        Administrador administrador = administradorRepositorio.findById(id).orElse(null);
        model.addAttribute("administrador", administrador);
        return "editAdministrador";
    }

    @PostMapping("/edit")
    public String editAdministradorSubmit(@ModelAttribute Administrador administrador) {
        Administrador existingAdministrador = administradorRepositorio.findById(administrador.getId()).orElse(null);

        if (existingAdministrador != null) {
            existingAdministrador.setNombre(administrador.getNombre());
            existingAdministrador.setApellido(administrador.getApellido());
            existingAdministrador.setTelefono(administrador.getTelefono());
            existingAdministrador.setCorreo(administrador.getCorreo());
            existingAdministrador.setContraseña(administrador.getContraseña());

            administradorRepositorio.save(existingAdministrador);
        }

        return "redirect:/administradores/";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdministrador(@PathVariable("id") String id) {
        administradorRepositorio.deleteById(id);
        return "redirect:/administradores/";
    }
}
