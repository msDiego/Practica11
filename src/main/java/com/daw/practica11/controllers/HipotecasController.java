package com.daw.practica11.controllers;

import com.daw.practica11.models.Hipoteca;
import com.daw.practica11.repositories.IHipotecaDAO;
import com.daw.practica11.services.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller("/user")
public class HipotecasController {

    private IHipotecaDAO hipotecaDAO;
    private UsuarioServiceImpl usuarioService;

    @PostMapping("/hipoteca")
    public String saveHipoteca(@Valid Hipoteca hipoteca, BindingResult result, Model model, Authentication auth) {

        if (result.hasErrors()) {
            model.addAttribute("hipoteca", hipoteca);
            return "userHipotecas";
        }
        String username = auth.getName();
        hipoteca.setUser(usuarioService.findByUsername(username));
        model.addAttribute("hipoteca", hipotecaDAO.save(hipoteca));
        return "redirect:/hipoteca";
    }

    @GetMapping("/hipoteca")
    public String verHipoteca(@ModelAttribute Hipoteca hipoteca, Model model) {
        model.addAttribute("cuadroAmortizacion", calcularHipoteca(hipoteca));
        return "verHipoteca";
    }

    private ArrayList<ArrayList<Double>> calcularHipoteca(Hipoteca hipoteca) {

        double totalValue = hipoteca.getTotal();
        int numCuotas = hipoteca.getCuotas();
        double interesAnual = hipoteca.getCuotas();
        double cAmortizable = (totalValue * (interesAnual / 12)) / (1 - Math.pow(1 + interesAnual / 12, -numCuotas));
        double totalPagado = 0;
        double interes;
        double amortizado;
        ArrayList<ArrayList<Double>> cuadroAmortizacion = new ArrayList<>();

        while (totalPagado < totalValue * (1 + interesAnual)) {

            ArrayList<Double> valores = new ArrayList<>();
            amortizado = cAmortizable - ((totalValue - totalPagado) * interesAnual / 12);
            interes = cAmortizable - amortizado;
            totalPagado = totalPagado + cAmortizable;
            valores.add(totalPagado);
            valores.add(cAmortizable);
            valores.add(interes);
            valores.add(amortizado);
            cuadroAmortizacion.add(valores);

        }

        return cuadroAmortizacion;
    }
}
