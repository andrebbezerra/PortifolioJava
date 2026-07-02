package org.example.dto;

import org.example.entity.Autor;
import org.example.entity.GeneroLiterario;
import org.example.entity.Livros;

import java.util.ArrayList;
import java.util.List;

public class LivrosDto {
    String nomeLivro;
    AutorDto autorDto;
    String ISBN;
    GeneroLiterario generoliterario;

    static List<Livros> listaLivros = new ArrayList<>();

    public void cadastrarLivros(String nomeLivro, AutorDto autorDto, String ISBN, GeneroLiterario generoliterario){
        Livros livro = new Livros(nomeLivro, autorDto, ISBN, generoliterario);
        listaLivros.add(livro);
    }

    public static List<Livros> getListaLivrosPeloNome(String nomeLivro) {
        return listaLivros.stream()
                .filter(Livros -> Livros.nomeLivro().equals(nomeLivro))
                .findFirst()
                .stream().toList();
    }

    @Override
    public String toString() {
        return "LivrosDto{" +
                "nomeLivro='" + nomeLivro + '\'' +
                ", autorDto=" + autorDto +
                ", ISBN='" + ISBN + '\'' +
                ", generoliterario=" + generoliterario +
                '}';
    }
}
