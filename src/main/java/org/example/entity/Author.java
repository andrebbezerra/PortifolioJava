package org.example.entity;

public record Author(String nomeAuthor) {

    public String getNomeAuthor() {
        return nomeAuthor;
    }

}
