package com.daw.practica11.controllers;

import com.daw.practica11.models.User;
import com.daw.practica11.services.UserDetailsService;
import com.daw.practica11.services.UsuarioServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("usuario", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(Authentication auth, HttpSession session){
        String username = auth.getName();
        if (session.getAttribute("usuario") == null){
            User user = usuarioService.findByUsername(username);
            user.setPassword(null);
            session.setAttribute("usuario", user);
        }
        return "loginCheck";
    }

    @GetMapping("/logincheck")
    public String login(){
        return "loginCheck";
    }

    @GetMapping("/signin")
    public String createAccount(Model model) {
        model.addAttribute("usuario", new User());
        return "crearCuenta";
    }

    @PostMapping("/signin")
    public String registrarCuenta(@Validated @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/signin";
        } else {
            model.addAttribute("usuario", usuarioService.register(user));
        }
        return "redirect:/exito";

    }

    @GetMapping("/exito")
    public String exito(){
        return "exito";
    }


}
