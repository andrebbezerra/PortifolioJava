package org.example.entity;

import org.example.repository.AutorRepository;

public record Livros(String nomeLivro, AutorRepository autor, String isbn, GeneroLiterario generoliterario) {
    @Override
    public String nomeLivro() {
        return nomeLivro;
    }
}
