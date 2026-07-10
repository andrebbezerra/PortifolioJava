// src/test/java/org/example/repository/LivrosRepositoryTypeErasureTest.java
package org.example.repository;

import org.example.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivrosRepositoryTypeErasureTest {

    private static final String ARQUIVO_TESTE = "livraria_teste.byte";

    @BeforeEach
    void setUp() {
        new File(ARQUIVO_TESTE).delete();
    }

    @AfterEach
    void tearDown() {
        new File(ARQUIVO_TESTE).delete();
    }

    @Test
    void deveCarregarLivrosCorretamenteDoArquivo() {
        // Arrange: salva alguns livros
        LivrosRepository repo = new LivrosRepository();
        Livros livro1 = new Livros("1984", new Author("George Orwell"), "123", GeneroLiterario.FICCAOCIENTIFICA);
        repo.salvar(livro1);

        // Act: inicia um novo repositório (que carrega do arquivo)
        LivrosRepository repo2 = new LivrosRepository();

        // Assert: verifica que o livro foi carregado corretamente
        assertEquals(1, repo2.listarTodos().size());
        assertEquals("1984", repo2.listarTodos().get(0).getNomeLivro());
    }

    @Test
    void deveHandleArquivoCorruptidoGraciosamente() {
        // Arrange: salva uma lista de strings (simulando corrupção)
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_TESTE))) {
            List<String> stringsList = Arrays.asList("corrupted", "data");
            out.writeObject(stringsList);
        } catch (IOException e) {
            fail("Erro ao criar arquivo corrompido");
        }

        // Act: tenta carregar com o novo repositório (que usa wrapper)
        // Não deve lançar ClassCastException!
        LivrosRepository repo = new LivrosRepository();

        // Assert: deve ter criado uma lista vazia (fallback)
        assertTrue(repo.listarTodos().isEmpty());
    }

    @Test
    void deveHandleArquivoComTipoErradoGraciosamente() {
        // Arrange: salva um String simples (não uma lista)
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_TESTE))) {
            out.writeObject("just a string");
        } catch (IOException e) {
            fail("Erro ao criar arquivo com tipo errado");
        }

        // Act: tenta carregar
        LivrosRepository repo = new LivrosRepository();

        // Assert: deve ter criado uma lista vazia
        assertTrue(repo.listarTodos().isEmpty());
    }
}
