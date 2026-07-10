package org.example.entity;

import java.util.Objects;

public final class Manga extends Livros {

    private final String edicao;

    public Manga(String nomeLivro, Author author, String isbn, GeneroLiterario generoliterario,String edicao) {
        super(nomeLivro, author, isbn, generoliterario);
        this.edicao = edicao;
    }

    public String getEdicao() {
        return edicao;
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

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Manga manga = (Manga) object;
        return Objects.equals(edicao, manga.edicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), edicao);
    }
}
