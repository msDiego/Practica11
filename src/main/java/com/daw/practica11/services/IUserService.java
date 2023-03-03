package com.daw.practica11.services;

import com.daw.practica11.models.User;

public interface IUserService {
    User findByUsername(String username);

    User register(User user);

}
