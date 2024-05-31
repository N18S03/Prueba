package com.Biblioteca.app.Controlador;

import com.Biblioteca.app.Entidad.Prestamo;
import com.Biblioteca.app.Repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prestamos")
public class PrestamoWebController {

    @Autowired
    private PrestamoRepository prestamoRepositorio;

    @GetMapping("/buscar")
    public String buscarPrestamo(@RequestParam String id, Model model) {
        Prestamo prestamo = prestamoRepositorio.findById(id).orElse(null);
        model.addAttribute("prestamo", prestamo);
        return "mostrarPrestamo";
    }
    
    @GetMapping("/informe/{id}")
    public String mostrarInformePrestamo(@PathVariable String id, Model model) {
        Prestamo prestamo = prestamoRepositorio.findById(id).orElse(null);
        model.addAttribute("prestamo", prestamo);
        return "informePrestamo"; // Nombre del HTML que muestra el informe del prestamo
    }
    
    @GetMapping("/")
    public String getAllPrestamos(Model model) {
        model.addAttribute("prestamos", prestamoRepositorio.findAll());
        return "prestamos";
    }

    @GetMapping("/add")
    public String addPrestamoForm(Model model) {
        model.addAttribute("prestamo", new Prestamo());
        return "addPrestamo";
    }

    @PostMapping("/add")
    public String addPrestamoSubmit(@ModelAttribute Prestamo prestamo) {
        prestamoRepositorio.save(prestamo);
        return "redirect:/prestamos/";
    }

    @GetMapping("/edit/{id}")
    public String editPrestamoForm(@PathVariable String id, Model model) {
        Prestamo prestamo = prestamoRepositorio.findById(id).orElse(null);
        model.addAttribute("prestamo", prestamo);
        return "editPrestamo";
    }

    @PostMapping("/edit")
    public String editPrestamoSubmit(@ModelAttribute Prestamo prestamo) {
        Prestamo existingPrestamo = prestamoRepositorio.findById(prestamo.getId()).orElse(null);

        if (existingPrestamo != null) {
            existingPrestamo.setIdUsuario(prestamo.getIdUsuario());
            existingPrestamo.setIdLibro(prestamo.getIdLibro());
            existingPrestamo.setFechaPrestamo(prestamo.getFechaPrestamo());
            existingPrestamo.setFechaDevolucion(prestamo.getFechaDevolucion());

            prestamoRepositorio.save(existingPrestamo);
        }

        return "redirect:/prestamos/";
    }

    @GetMapping("/delete/{id}")
    public String deletePrestamo(@PathVariable String id) {
        prestamoRepositorio.deleteById(id);
        return "redirect:/prestamos/";
    }
}
