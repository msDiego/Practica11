package com.daw.practica11.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


@Entity
@Table(name = "hipoteca")
public class Hipoteca {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;
    @Column
    @Min(value = 1, message = "El valor introducido no es válido" )
    private int cuotas;
    @Column
    @Min(value = 1, message = "El valor introducido no es válido")
    private double intereses;
    @Column
    @Min(value = 1, message = "El valor introducido no es válido")
    private double total;

    @Column
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate creacion;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    public Long getId() {
        return id;
    }

    public LocalDate getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDate creacion) {
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
