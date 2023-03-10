package com.daw.practica11.controllers;

import com.daw.practica11.models.Hipoteca;
import com.daw.practica11.repositories.IHipotecaDAO;
import com.daw.practica11.services.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller("/user")
public class HipotecasController {
    @Autowired
    private IHipotecaDAO hipotecaDAO;
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("user")
    public String saveHipoteca(@Valid Hipoteca hipoteca, BindingResult result, Model model, Authentication auth) {

        if (result.hasErrors()) {
            model.addAttribute("hipoteca", hipoteca);
            return "crearHipoteca";
        }
        String username = auth.getName();
        hipoteca.setUser(usuarioService.findByUsername(username));
        hipoteca.setCreacion(LocalDate.now());
        model.addAttribute("hipoteca", hipotecaDAO.save(hipoteca));
        model.addAttribute("cuadroAmortizacion",calcularHipoteca(hipoteca));
        return "verHipoteca";
    }

    private ArrayList<ArrayList<String>> calcularHipoteca(Hipoteca hipoteca){

        double totalValue = hipoteca.getTotal();
        double interesAnual = hipoteca.getIntereses() / 100;
        int numCuotas = hipoteca.getCuotas();

        double tasaInteresMensual = interesAnual / 12.0;
        double cuotaMensual = (totalValue * tasaInteresMensual) / (1 - Math.pow(1 + tasaInteresMensual, -numCuotas));

        double saldoPendiente = totalValue;
        ArrayList<ArrayList<String>> cuadroAmortizacion = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.##");

        for (int i = 1; i <= numCuotas; i++) {

            ArrayList<String> valores = new ArrayList<>();

            double interes = saldoPendiente * tasaInteresMensual;
            double capital = cuotaMensual - interes;
            saldoPendiente -= capital;
            valores.add(df.format(totalValue - saldoPendiente));
            valores.add(df.format(cuotaMensual));
            valores.add(df.format(interes));
            valores.add(df.format(capital));

            cuadroAmortizacion.add(valores);
        }

        return cuadroAmortizacion;
    }
}
