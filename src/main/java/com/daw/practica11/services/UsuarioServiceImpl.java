package com.daw.practica11.services;

import com.daw.practica11.models.User;
import com.daw.practica11.repositories.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUserService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUserDAO userRepository;
    @Override
    public User findByUsername(String username) {
       return userRepository.findByUsername(username);
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


}
