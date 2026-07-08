package org.example.entity;

import java.io.Serializable;

public class Livros implements Serializable {

    private final String nomeLivro;
    private final Author author;
    private final String isbn;
    private final GeneroLiterario generoliterario;

    public String getNomeLivro() {
        return nomeLivro;
    }

    public Author getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public GeneroLiterario getGeneroliterario() {
        return generoliterario;
    }

    public Livros(String nomeLivro, Author author, String isbn, GeneroLiterario generoliterario) {
        this.nomeLivro = nomeLivro;
        this.author = author;
        this.isbn = isbn;
        this.generoliterario = generoliterario;
    }

    public String nomeLivro() {
        return nomeLivro;
    }
}
