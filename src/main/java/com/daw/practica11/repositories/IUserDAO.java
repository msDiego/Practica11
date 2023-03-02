package com.daw.practica11.repositories;

import com.daw.practica11.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDAO extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
