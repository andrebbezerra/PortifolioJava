package org.example.repository;

import org.example.data.Serializacao;
import org.example.entity.Livros;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivrosRepository extends Serializacao implements Repositorio<Livros> {

    private final List<Livros> listaLivros = new ArrayList<>();
    Serializacao serializacao;

    @Override
    public Livros salvar(Livros livro) {
        listaLivros.add(livro);
        serializacao.serializar(livro);
        return livro;
    }

    @Override
    public List<Livros> listarTodos() {
        return listaLivros;
    }

    public Optional<Livros> buscarPorNome(String nomeLivro) {
        return listaLivros.stream()
                .filter(l -> l.nomeLivro().equals(nomeLivro))
                .findFirst();
    }

    public Optional<Livros> buscarPorAuthor(String nomeAuthor) {
        return listaLivros.stream()
                .filter(a -> a.getAuthor().getNomeAuthor().equals(nomeAuthor))
                .findFirst();
    }

    public Optional<Livros> buscarPorCategoria(String categoria) {
        return listaLivros.stream()
                .filter(c -> c.getGeneroliterario().equals(categoria))
                .findFirst();
    }



    @Override
    public void editar(String nome) {
        Livros livroEncontrado = listaLivros.stream()
                .filter(l -> l.nomeLivro()
                .equals(nome)).
                findFirst()
                .orElse(null);
    }

    @Override
    public void excluir(Livros livros) {
        listaLivros.remove(livros);
    }

}
