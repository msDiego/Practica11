package com.daw.practica11.controllers;

import com.daw.practica11.models.User;
import com.daw.practica11.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
// TODO controller para seguridad y login
@Controller
@RequestMapping("/")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/users")
    public String getAll(Model model) {
        ArrayList<User> allUsers = (ArrayList<User>) usuarioRepository.findAll();
        model.addAttribute("users", allUsers);
        return "users";
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getOne(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User user = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El usuario no ha sido encontrado o no existe: " + id));

        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public User create(@Validated @RequestBody User user) {
        return usuarioRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable(value = "id") Long id, @Validated @RequestBody User user) throws ResourceNotFoundException {

        User newUser = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El usuario no ha sido encontrado o no existe: " + id)
        );

        newUser.setNombre(user.getNombre());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        usuarioRepository.save(newUser);

        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> delete(@PathVariable (value = "id") Long id) throws ResourceNotFoundException {

        usuarioRepository.deleteById(id);

        Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
