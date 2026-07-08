package org.example.repository;

import org.example.entity.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorRepository implements Repositorio<Author> {

    private final List<Author> autores = new ArrayList<>();

    @Override
    public Author salvar(Author author) {
        autores.add(author);
        return author;
    }

    public List<Author> listarTodos(){
        return List.copyOf(autores);
    }

    public Optional<Author> buscarPorNome(String nomeAutor){
        return autores.stream()
                .filter(a -> a.getNomeAuthor().equals(nomeAutor))
                .findFirst();
    }

    @Override
    public void editar(String nome) {

    }

    @Override
    public void excluir(Author author) {
        autores.remove(author);
    }


}
