package com.daw.practica11.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "hipoteca")
public class Hipoteca {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;
    @Column
    @Size(min = 1, message = "El valor introducido no es válido")
    private int cuotas;
    @Column
    @Size(min = 1, message = "El valor introducido no es válido")
    private double intereses;
    @Column
    @Size(min = 1, message = "El valor introducido no es válido")
    private double total;

    @Column
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date creacion;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    public Long getId() {
        return id;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getUsuarioModel() {
        return user;
    }

    public void setUsuarioModel(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "HipotecaModel = {" +
                "id: " + id +
                ", cuotas: " + cuotas +
                ", intereses: " + intereses +
                ", total: " + total +
                ", usuarioModel: " + user +
                '}';
    }
}
