package com.Biblioteca.app.Entidad;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "libros")
public class Libro {

	@Id
	private String id;
	private String nombre;
	private String reseña;
	private String autor;
	private int cantidad;
	
	@DocumentReference
	private List<Editorial> editoriales;

	public Libro() {
		super();
	}

	public Libro(String id, String nombre, String reseña, String autor, int cantidad, List<Editorial> editorial) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.reseña = reseña;
		this.autor = autor;
		this.cantidad = cantidad;
		this.editoriales = editoriales;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getReseña() {
		return reseña;
	}

	public void setReseña(String reseña) {
		this.reseña = reseña;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public List<Editorial> getEditorial() {
		return editoriales;
	}

	public void setEditorial(List<Editorial> editorial) {
		this.editoriales = editorial;
	}	
	
}
