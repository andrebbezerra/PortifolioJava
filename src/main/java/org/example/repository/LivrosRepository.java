// src/main/java/org/example/repository/LivrosRepository.java
package org.example.repository;

import org.example.data.Serializacao;
import org.example.data.LivrariaPersistencia;
import org.example.entity.Livros;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivrosRepository implements Repositorio<Livros> {

    private static final String ARQUIVO = "livraria.byte";
    private final List<Livros> listaLivros;
    private final Serializacao serializador = new Serializacao(); // Composição!

    public LivrosRepository() {
        Object dados = serializador.deserializar(ARQUIVO);

        if (dados instanceof LivrariaPersistencia) {
            LivrariaPersistencia persistencia = (LivrariaPersistencia) dados;
            this.listaLivros = persistencia.getLivros();
        } else {
            this.listaLivros = new ArrayList<>();
        }
    }

    @Override
    public Livros salvar(Livros livro) {
        listaLivros.add(livro);
        serializador.serializar(new LivrariaPersistencia(listaLivros), ARQUIVO);
        return livro;
    }

    @Override
    public List<Livros> listarTodos() {
        return List.copyOf(listaLivros);
    }

    public Optional<Livros> buscarPorNome(String nomeLivro) {
        return listaLivros.stream()
                .filter(l -> l.getNomeLivro().equals(nomeLivro))
                .findFirst();
    }

    public boolean existePorNome(String nome) {
        return listaLivros.stream()
                .anyMatch(l -> l.getNomeLivro().equals(nome));
    }

    public Optional<Livros> buscarPorAuthor(String nomeAuthor) {
        return listaLivros.stream()
                .filter(l -> l.getAuthor().nomeAuthor().equals(nomeAuthor))
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
        serializador.serializar(new LivrariaPersistencia(listaLivros), ARQUIVO);
    }
}