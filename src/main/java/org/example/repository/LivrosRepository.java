package org.example.repository;

import org.example.entity.GeneroLiterario;
import org.example.entity.Livros;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivrosRepository implements Repositorio<Livros>{

    private final static List<Livros> listaLivros = new ArrayList<>();

    @Override
    public Livros salvar(Livros livro) {
        listaLivros.add(livro);
        return livro;
    }

    @Override
    public List<Livros> listarTodos() {
        return List.of();
    }

    public Optional<Livros> buscarPorNome(String nomeLivro) {
        return listaLivros.stream()
                .filter(l -> l.nomeLivro().equals(nomeLivro))
                .findFirst();
    }
}
