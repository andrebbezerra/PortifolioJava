package org.example.repository;

import org.example.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoRepositoryTest {

    private static final String ARQUIVO_TESTE = "emprestimo.byte";
    private EmprestimoRepository repository;
    private Usuarios usuario;
    private Livros livro;

    @BeforeEach
    void setUp() {
        new File(ARQUIVO_TESTE).delete();
        repository = new EmprestimoRepository();
        usuario = new Usuarios("Pedro", "pedro@email.com", 1);
        livro = new Livros("Dom Casmurro", new Author("Machado de Assis"), "123", GeneroLiterario.DRAMA);
    }

    @AfterEach
    void tearDown() {
        new File(ARQUIVO_TESTE).delete();
    }

    @Test
    void salvarDeveRegistrarEmprestimoComoAtivo() {
        Emprestimo emprestimo = new Emprestimo(usuario, livro, LocalDate.now(), LocalDate.now().plusDays(30), null);

        repository.salvar(emprestimo);

        assertEquals(1, repository.listarTodos().size());
        assertTrue(repository.listarTodos().get(0).estaAtivo());
    }

    @Test
    void buscarEmprestimoAtivoDeveEncontrarEmprestimoNaoDevolvido() {
        repository.salvar(new Emprestimo(usuario, livro, LocalDate.now(), LocalDate.now().plusDays(30), null));

        Optional<Emprestimo> encontrado = repository.buscarEmprestimoAtivo("Pedro", "Dom Casmurro");

        assertTrue(encontrado.isPresent());
    }

    @Test
    void buscarEmprestimoAtivoNaoDeveEncontrarEmprestimoJaDevolvido() {
        Emprestimo devolvido = new Emprestimo(
                usuario, livro, LocalDate.now().minusDays(10), LocalDate.now().plusDays(20), LocalDate.now());
        repository.salvar(devolvido);

        Optional<Emprestimo> encontrado = repository.buscarEmprestimoAtivo("Pedro", "Dom Casmurro");

        assertTrue(encontrado.isEmpty());
    }

    @Test
    void existeEmprestimoAtivoParaLivroDeveRetornarTrueQuandoEmprestado() {
        repository.salvar(new Emprestimo(usuario, livro, LocalDate.now(), LocalDate.now().plusDays(30), null));

        assertTrue(repository.existeEmprestimoAtivoParaLivro("Dom Casmurro"));
    }

    @Test
    void devolverDeveMarcarEmprestimoComoInativo() {
        Emprestimo emprestimo = repository.salvar(
                new Emprestimo(usuario, livro, LocalDate.now(), LocalDate.now().plusDays(30), null));

        Emprestimo devolvido = repository.devolver(emprestimo, LocalDate.now());

        assertFalse(devolvido.estaAtivo());
        assertTrue(repository.buscarEmprestimoAtivo("Pedro", "Dom Casmurro").isEmpty());
    }

    @Test
    void devolverNaoDeveDuplicarRegistroDoEmprestimo() {
        Emprestimo emprestimo = repository.salvar(
                new Emprestimo(usuario, livro, LocalDate.now(), LocalDate.now().plusDays(30), null));

        repository.devolver(emprestimo, LocalDate.now());

        // continua existindo só 1 empréstimo (o antigo foi removido, o novo — devolvido — foi adicionado)
        assertEquals(1, repository.listarTodos().size());
    }
}