package com.daw.practica11.services;

import com.daw.practica11.models.UsuarioModel;
import com.daw.practica11.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> index(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

}
