package com.daw.practica11.controllers;

import com.daw.practica11.models.User;
import com.daw.practica11.services.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    private boolean isAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())){
            return false;
        }
        return authentication.isAuthenticated();
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        if (isAuthenticated()){
            return "redirect:/user";
        }
        model.addAttribute("usuario", new User());
        return "login";
    }

    @GetMapping("/signin")
    public String createAccount(Model model) {
        model.addAttribute("usuario", new User());
        return "crearCuenta";
    }

    @PostMapping("/signin")
    public String registrarCuenta(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("usuario", user);
            return "crearCuenta";
        }

        model.addAttribute("usuario", usuarioService.register(user));
        return "redirect:/login";

    }

}
