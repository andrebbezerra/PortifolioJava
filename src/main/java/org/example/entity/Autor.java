package org.example.entity;

public class Autor {

    private String nomeAutor;

    public Autor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nomeAutor='" + nomeAutor + '\'' +
                '}';
    }
}
