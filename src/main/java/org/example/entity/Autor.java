package org.example.entity;

public record Autor(String nomeAutor) {

    public String getNomeAutor() {
        return nomeAutor;
    }
}
