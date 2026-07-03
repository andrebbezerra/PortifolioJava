package org.example.dto;

import org.example.entity.GeneroLiterario;

public class LivrosDto {
    String nomeLivro;
    AutorDto autorDto;
    String isbn;
    GeneroLiterario generoliterario;


    @Override
    public String toString() {
        return "LivrosDto{" +
                "nomeLivro='" + nomeLivro + '\'' +
                ", autorDto=" + autorDto +
                ", isbn='" + isbn + '\'' +
                ", generoliterario=" + generoliterario +
                '}';
    }
}
