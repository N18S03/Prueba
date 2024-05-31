package com.Biblioteca.app.Repository;

import com.Biblioteca.app.Entidad.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends MongoRepository<Libro, String> {

}
