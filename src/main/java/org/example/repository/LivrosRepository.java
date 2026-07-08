package org.example.repository;

import org.example.data.Serializacao;
import org.example.entity.Livros;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivrosRepository extends Serializacao implements Repositorio<Livros> {

    private final List<Livros> listaLivros = new ArrayList<>();

    @Override
    public Livros salvar(Livros livro) {
        serializar(livro);
        return livro;
    }

    @Override
    public List<Livros> listarTodos() {
        return listaLivros;
    }

    public Optional<Livros> buscarPorNome(String nomeLivro) {
        return (Optional<Livros>) deserializar();
    }

    public Optional<Livros> buscarPorAuthor(String nomeAuthor) {
        List<Livros> listaLivrosRecuperados = (List<Livros>) deserializar();
        return listaLivrosRecuperados.stream()
                .filter(a -> a.getAuthor().getNomeAuthor().equals(nomeAuthor))
                .findFirst();
    }

    public Optional<Livros> buscarPorCategoria(String categoria) {
        List<Livros> listaLivrosRecuperados = (List<Livros>) deserializar();
        return listaLivrosRecuperados.stream()
                .filter(c -> c.getGeneroliterario().equals(categoria))
                .findFirst();
    }

    @Override
    public void excluir(Livros livros) {
        listaLivros.remove(livros);
    }

}
