package com.daw.practica11.repositories;

import com.daw.practica11.models.Hipoteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHipotecaDAO extends JpaRepository<Hipoteca, Long> {

    Hipoteca findByUserId(Long id);
}
