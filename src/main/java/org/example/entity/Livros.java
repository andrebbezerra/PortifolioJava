package org.example.entity;

import java.io.Serializable;
import java.util.Objects;

public class Livros implements  Serializable {

    private final String nomeLivro;
    private final Author author;
    private final String isbn;
    private final GeneroLiterario generoliterario;

    public Livros(String nomeLivro, Author author, String isbn, GeneroLiterario generoliterario) {
        this.nomeLivro = nomeLivro;
        this.author = author;
        this.isbn = isbn;
        this.generoliterario = generoliterario;
    }

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


    public String nomeLivro() {
        return nomeLivro;
    }

    @Override
    public String toString() {
        return "Livros{" +
                "nomeLivro='" + nomeLivro + '\'' +
                ", author=" + author +
                ", isbn='" + isbn + '\'' +
                ", generoliterario=" + generoliterario +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Livros livros = (Livros) object;
        return Objects.equals(nomeLivro, livros.nomeLivro) && Objects.equals(author, livros.author) && Objects.equals(isbn, livros.isbn) && generoliterario == livros.generoliterario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeLivro, author, isbn, generoliterario);
    }
}
