package org.example.dto;

import org.example.entity.Autor;

import java.util.ArrayList;
import java.util.List;

public class AutorDto {

    public List<Autor> Autores = new ArrayList<>();

    public void cadastrarAutor(String nomeAutor){
        Autor autor = new Autor(nomeAutor);
        Autores.add(autor);
    }

    public List<Autor> listarAutores(){
        return Autores;
    }

    public List<Autor> listarAutorPeloNome(String nomeAutor){
        return Autores.stream()
                .filter(Autor -> Autor.getNomeAutor().equals(nomeAutor))
                .findFirst()
                .stream().toList();
    }
}
