package com.daw.practica11.models;

import jakarta.persistence.*;

@Entity
@Table(name = "hipoteca")
public class Hipoteca {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;
    @Column
    private int cuotas;
    @Column
    private double intereses;
    @Column
    private double total;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    public Long getId() {
        return id;
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
