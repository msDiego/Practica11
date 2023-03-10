package com.daw.practica11.controllers;

import com.daw.practica11.models.Hipoteca;
import com.daw.practica11.models.User;
import com.daw.practica11.services.UsuarioServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public String userPage(Authentication auth, Model model, HttpSession session){
        String username = auth.getName();
        User userById = usuarioService.findByUsername(username);
        model.addAttribute("hipoteca", new Hipoteca());
        if (session.getAttribute("usuario") == null){
            User user = usuarioService.findByUsername(username);
            user.setPassword(null);
            session.setAttribute("usuario", user);
        }
        return "crearHipoteca";
    }

}
