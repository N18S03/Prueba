package com.Biblioteca.app.Controlador;

import com.Biblioteca.app.Entidad.Libro;
import com.Biblioteca.app.Entidad.Prestamo;
import com.Biblioteca.app.Entidad.Usuario;
import com.Biblioteca.app.Repository.PrestamoRepository;
import com.Biblioteca.app.Repository.LibroRepository; // Importar el repositorio de libros
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/prestamos")
public class PrestamoWebController {

    @Autowired
    private PrestamoRepository prestamoRepositorio;

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping("/solicitar/{idLibro}")
    public String solicitarPrestamo(@PathVariable("idLibro") String idLibro, Model model) {
        model.addAttribute("idLibro", idLibro);
        return "prestamos";
    }

    @PostMapping("/guardar")
    public String guardarPrestamo(@RequestParam("idLibro") String idLibro,
                                  @RequestParam("fechaDevolucion") String fechaDevolucionStr,
                                  HttpSession session,
                                  Model model) {
        // Obtener el usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            // Manejar el caso en el que no haya un usuario en la sesión
            return "redirect:/login";
        }
        String idUsuario = usuario.getId(); // Suponiendo que el ID del usuario es una propiedad de la entidad Usuario

        Date fechaDevolucion;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            fechaDevolucion = formatter.parse(fechaDevolucionStr);
        } catch (ParseException e) {
            // Manejar el error de conversión de fecha
            model.addAttribute("error", "Formato de fecha inválido");
            return "prestamos";
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setIdUsuario(idUsuario);
        prestamo.setIdLibro(idLibro);
        prestamo.setFechaPrestamo(new Date());
        prestamo.setFechaDevolucion(fechaDevolucion);

        // Guardar el préstamo en la base de datos
        prestamoRepositorio.save(prestamo);

        // Obtener el libro correspondiente al préstamo
        Libro libro = libroRepository.findById(idLibro).orElse(null);
        if (libro != null) {
            // Disminuir la cantidad de libros
            int cantidadActual = libro.getCantidad();
            if (cantidadActual > 0) {
                libro.setCantidad(cantidadActual - 1);
                // Guardar la actualización en el repositorio de libros
                libroRepository.save(libro);
            } else {
                // Manejar el caso en el que no haya suficientes libros disponibles
                model.addAttribute("error", "No hay suficientes libros disponibles");
                return "prestamos";
            }
        }

        return "redirect:/prestamos";
    }


    @GetMapping
    public String verPrestamos(Model model, HttpSession session) {
        // Obtener el usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            // Manejar el caso en el que no haya un usuario en la sesión
            return "redirect:/login";
        }
        
        // Agregar el usuario al modelo
        model.addAttribute("usuario", usuario);

        // Obtener la lista de préstamos
        List<Prestamo> prestamos = prestamoRepositorio.findAll();
        
        // Obtener información adicional de los libros correspondientes a los préstamos
        List<Libro> libros = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            Libro libro = libroRepository.findById(prestamo.getIdLibro()).orElse(null);
            if (libro != null) {
                libros.add(libro);
            }
        }

        model.addAttribute("prestamos", prestamos);
        model.addAttribute("libros", libros);
        
        return "prestamos";
    }
    
    @PostMapping("/devolver/{idPrestamo}")
    public String devolverPrestamo(@PathVariable("idPrestamo") String idPrestamo,
                                    HttpSession session,
                                    Model model) {
        // Obtener el usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            // Manejar el caso en el que no haya un usuario en la sesión
            return "redirect:/login";
        }

        // Obtener el préstamo a devolver
        Prestamo prestamo = prestamoRepositorio.findById(idPrestamo).orElse(null);
        if (prestamo == null) {
            // Manejar el caso en el que no se encuentre el préstamo
            model.addAttribute("error", "No se encontró el préstamo");
            return "redirect:/prestamos";
        }

        // Obtener el libro correspondiente al préstamo
        Libro libro = libroRepository.findById(prestamo.getIdLibro()).orElse(null);
        if (libro != null) {
            // Aumentar la cantidad de libros
            libro.setCantidad(libro.getCantidad() + 1);
            // Guardar la actualización en el repositorio de libros
            libroRepository.save(libro);
        }

        // Eliminar el préstamo de la base de datos
        prestamoRepositorio.delete(prestamo);

        return "redirect:/prestamos";
    }


}
