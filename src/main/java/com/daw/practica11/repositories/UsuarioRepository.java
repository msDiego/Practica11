package com.daw.practica11.repositories;

import com.daw.practica11.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<User, Long> {

    User findByNombre(String nombre);
}
