package com.Biblioteca.app.Repository;

import com.Biblioteca.app.Entidad.Administrador;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador, String> {
	Administrador findByCorreoAndContraseña(String correo, String contraseña);
}
