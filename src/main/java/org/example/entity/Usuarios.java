package org.example.entity;

public record Usuarios(String nome,String email,int id) {

    public String buscarPorNome() {
        return nome;
    }

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
