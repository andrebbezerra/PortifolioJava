package org.example.repository;

import org.example.data.Serializacao;
import org.example.entity.Livros;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// LivrosRepository.java
public class LivrosRepository extends Serializacao implements Repositorio<Livros> {

    private static final String ARQUIVO = "livraria.byte";
    private final List<Livros> listaLivros;

    public LivrosRepository() {
        Object dados = deserializar(ARQUIVO);
        this.listaLivros = (dados instanceof List<?>) ? (List<Livros>) dados : new ArrayList<>();
    }

    @Override
    public Livros salvar(Livros livro) {
        listaLivros.add(livro);
        serializar(listaLivros, ARQUIVO); // grava o SNAPSHOT inteiro
        return livro;
    }

    @Override
    public List<Livros> listarTodos() {
        return List.copyOf(listaLivros);
    }

    public Optional<Livros> buscarPorNome(String nomeLivro) {
        return listaLivros.stream().filter(l -> l.getNomeLivro().equals(nomeLivro)).findFirst();
    }

    public Optional<Livros> buscarPorAuthor(String nomeAuthor) {
        return listaLivros.stream()
                .filter(l -> l.getAuthor().getNomeAuthor().equals(nomeAuthor))
                .findFirst();
    }

    public Optional<Livros> buscarPorCategoria(String categoria) {
        return listaLivros.stream()
                .filter(l -> l.getGeneroliterario().name().equalsIgnoreCase(categoria))
                .findFirst();
    }

    @Override
    public void excluir(Livros livro) {
        listaLivros.remove(livro);
        serializar(listaLivros, ARQUIVO); // regrava sem o item excluído
    }
}