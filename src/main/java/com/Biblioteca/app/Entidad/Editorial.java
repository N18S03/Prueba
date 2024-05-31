package com.Biblioteca.app.Entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "editoriales")
public class Editorial {

	@Id
	private String id;
	private String nombre;
	private String correo;
	private String numeroTel;
	
	public Editorial() {
		super();
	}

	public Editorial(String id, String nombre, String correo, String numeroTel) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.numeroTel = numeroTel;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}
}
