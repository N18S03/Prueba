package com.Biblioteca.app.Repository;

import com.Biblioteca.app.Entidad.Prestamo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends MongoRepository<Prestamo, String> {
}
