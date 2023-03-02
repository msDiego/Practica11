package com.daw.practica11.services;

import com.daw.practica11.models.User;

public interface IUserService {
    public User findByUsername(String username);

    public User register(User user);
}
