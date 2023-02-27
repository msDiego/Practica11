package com.daw.practica11.controllers;

import com.daw.practica11.models.HipotecaModel;
import com.daw.practica11.models.UsuarioModel;
import com.daw.practica11.repositories.UsuarioRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/users")
    public ArrayList<UsuarioModel> getAll() {
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UsuarioModel> getOne(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El usuario no ha sido encontrado o no existe: " + id));

        return ResponseEntity.ok(usuarioModel);
    }

    @PostMapping("/users")
    public UsuarioModel create(@Validated @RequestBody UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UsuarioModel> update(@PathVariable(value = "id") Long id, @Validated @RequestBody UsuarioModel usuarioModel) throws ResourceNotFoundException {

        UsuarioModel newUser = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El usuario no ha sido encontrado o no existe: " + id)
        );

        newUser.setNombre(usuarioModel.getNombre());
        newUser.setEmail(usuarioModel.getEmail());
        newUser.setPassword(usuarioModel.getPassword());

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
