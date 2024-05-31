package com.Biblioteca.app.Entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "prestamos")
public class Prestamo {

    @Id
    private String id;
    private String idUsuario;
    private String idLibro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    
	public Prestamo() {
		super();
	}

	public Prestamo(String id, String idUsuario, String idLibro, Date fechaPrestamo, Date fechaDevolucion) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idLibro = idLibro;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(String idLibro) {
		this.idLibro = idLibro;
	}

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
}
