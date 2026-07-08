package org.example.entity;

public final class Manga extends Livros {

    public Manga(String nomeLivro, Author author, String isbn, GeneroLiterario generoliterario,String edicao) {
        super(nomeLivro, author, isbn, generoliterario);
    }

    @Override
    public String toString() {
        return "Manga{" +
                "nomeLivro='" + getNomeLivro() + '\'' +
                ", author=" + getAuthor() +
                ", isbn='" + getIsbn() + '\'' +
                ", generoliterario=" + getGeneroliterario() +
                '}';
    }
}
