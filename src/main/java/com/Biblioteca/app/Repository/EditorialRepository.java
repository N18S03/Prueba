package com.Biblioteca.app.Repository;

import com.Biblioteca.app.Entidad.Editorial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends MongoRepository<Editorial, String> {
}
