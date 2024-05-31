package com.Biblioteca.app.Entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {

	@Id
	private String id;
	private String nombre;
	private String apellido;
	private String cedula;
	private String telefono;	
	private String correo;
	private String contraseña;
	
	public Usuario() {
		super();
	}

	public Usuario(String id, String nombre, String apellido, String cedula, String telefono, String correo,
			String contraseña) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.telefono = telefono;
		this.correo = correo;
		this.contraseña = contraseña;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
}
