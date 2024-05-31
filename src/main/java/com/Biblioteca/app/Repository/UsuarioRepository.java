package com.Biblioteca.app.Repository;

import com.Biblioteca.app.Entidad.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	Usuario findByCorreoAndContraseña(String correo, String contraseña);

}
