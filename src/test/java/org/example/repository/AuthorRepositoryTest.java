package org.example.repository;

import org.example.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class AuthorRepositoryTest {

    private AuthorRepository repository;

    @BeforeEach
    void setUp() {
        repository = new AuthorRepository(); // sem arquivo em disco, não precisa limpar nada
    }

    @Test
    void salvarDeveAdicionarAutorNaLista() {
        Author author = new Author("Clarice Lispector");

        repository.salvar(author);

        assertEquals(1, repository.listarTodos().size());
    }

    @Test
    void listarTodosDeveRetornarTodosOsAutoresSalvos() {
        repository.salvar(new Author("Autor 1"));
        repository.salvar(new Author("Autor 2"));

        assertEquals(2, repository.listarTodos().size());
    }

    @Test
    void buscarPorNomeDeveEncontrarAutorExistente() {
        repository.salvar(new Author("Machado de Assis"));

        Optional<Author> encontrado = repository.buscarPorNome("Machado de Assis");

        assertTrue(encontrado.isPresent());
    }

    @Test
    void buscarPorNomeDeveRetornarVazioQuandoAutorNaoExiste() {
        Optional<Author> encontrado = repository.buscarPorNome("Inexistente");

        assertTrue(encontrado.isEmpty());
    }
}