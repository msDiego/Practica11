package com.daw.practica11.services;

import com.daw.practica11.models.User;
import com.daw.practica11.repositories.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private IUserDAO IUserDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User us = IUserDAO.findByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;

        if (us != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.disabled(false);
            builder.password(us.getPassword());
            builder.authorities(new SimpleGrantedAuthority("USER"));

        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return builder.build();

    }
}
