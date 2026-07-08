package org.example.entity;

import java.io.Serializable;

public record Author(String nomeAuthor) implements Serializable {

    public String getNomeAuthor() {
        return nomeAuthor;
    }

}
