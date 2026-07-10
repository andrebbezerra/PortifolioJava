package org.example.entity;

import java.io.Serializable;

public record Usuarios(String nome, String email, int id) implements Serializable {

    @Override
    public int id() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
