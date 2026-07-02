package org.example.entity;

import org.example.dto.AutorDto;

public record Livros(String nomeLivro, AutorDto autorDto, String ISBN, GeneroLiterario generoliterario) {
    @Override
    public String nomeLivro() {
        return nomeLivro;
    }
}
