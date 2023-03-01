package com.daw.practica11;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Practica11ApplicationTests {

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

}
