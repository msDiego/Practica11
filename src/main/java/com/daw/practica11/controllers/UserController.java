package com.daw.practica11.controllers;

import com.daw.practica11.models.User;
import com.daw.practica11.services.UsuarioServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/")
    public String userMain(){
        return "redirect:/{id}";
    }

    @GetMapping("/{id}")
    public String userPage(Authentication auth, HttpSession session){
        String username = auth.getName();
        if (session.getAttribute("usuario") == null){
            User user = usuarioService.findByUsername(username);
            user.setPassword(null);
            session.setAttribute("usuario", user);
        }

        return "userHipotecas";
    }
}
