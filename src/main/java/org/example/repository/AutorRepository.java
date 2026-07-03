package org.example.repository;

import org.example.entity.Autor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutorRepository implements Repositorio<Autor> {

    private final List<Autor> autores = new ArrayList<>();

    @Override
    public Autor salvar(Autor autor) {
        autores.add(autor);
        return autor;
    }

    public List<Autor> listarTodos(){
        return List.copyOf(autores);
    }

    public Optional<Autor> buscarPorNome(String nomeAutor){
        return autores.stream()
                .filter(a -> a.getNomeAutor().equals(nomeAutor))
                .findFirst();
    }
}
