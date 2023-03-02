package com.daw.practica11.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (unique = true)
    private Long id;
    @Column (unique = true)
    private String username;
    @Column
    private String password;
    @Column (unique = true)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String nombre) {
        this.username = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UsuarioModel = {" +
                "id:" + id +
                ", nombre:'" + username + '\'' +
                ", password:'" + password + '\'' +
                ", email:'" + email + '\'' +
                '}';
    }
}
