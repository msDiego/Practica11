package com.daw.practica11;

import com.daw.practica11.models.User;
import com.daw.practica11.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class Practica11ApplicationTests {

    /*
    @Test
     void testCalculadora(){

        int totalValue = 200000;
        int numCuotas = 60;
        double interesAnual = 0.03;
        double cAmortizable = (totalValue * (interesAnual / 12) ) / (1 - Math.pow(1+interesAnual/12, -numCuotas));
        double totalPagado = 0;
        double interes;
        double amortizado;

        while (totalPagado < totalValue * (1 + interesAnual)){

            amortizado = cAmortizable - ( (totalValue - totalPagado) * interesAnual / 12);
            interes = cAmortizable - amortizado;
            totalPagado = totalPagado + cAmortizable;

            System.out.println("Total pagado: " + totalPagado + " | Interés: " + interes + " | Amortizado: " + amortizado );

        }


    }

     */

    @Autowired
    private UsuarioRepository usuarioRepository;

     @Autowired
     private BCryptPasswordEncoder encoder;


    @Test
    void testCreateUser(){

        User user = new User();
        user.setNombre("test2");
        user.setEmail("test@gmail.com");
        user.setPassword(encoder.encode("1234"));

        User savedUser = usuarioRepository.save(user);

        assert user.getEmail().equalsIgnoreCase(savedUser.getEmail());
    }

}
