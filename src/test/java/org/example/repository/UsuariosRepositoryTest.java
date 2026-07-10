package org.example.repository;

import org.example.entity.Usuarios;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsuariosRepositoryTest {

    private static final String ARQUIVO_TESTE = "usuario.byte";
    private UsuariosRepository repository;

    @BeforeEach
    void setUp() {
        new File(ARQUIVO_TESTE).delete();
        repository = new UsuariosRepository();
    }

    @AfterEach
    void tearDown() {
        new File(ARQUIVO_TESTE).delete();
    }

    @Test
    void salvarDeveAdicionarUsuarioNaLista() {
        Usuarios usuario = new Usuarios("Maria", "maria@email.com", 1);

        repository.salvar(usuario);

        assertEquals(1, repository.listarTodos().size());
    }

    @Test
    void buscarPorNomeDeveEncontrarUsuarioExistente() {
        repository.salvar(new Usuarios("João", "joao@email.com", 1));

        Optional<Usuarios> encontrado = repository.buscarPorNome("João");

        assertTrue(encontrado.isPresent());
        assertEquals("joao@email.com", encontrado.get().email());
    }

    @Test
    void excluirDeveRemoverUsuarioDaLista() {
        Usuarios usuario = new Usuarios("Carlos", "carlos@email.com", 1);
        repository.salvar(usuario);

        repository.excluir(usuario);

        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void existePorNomeDeveDistinguirUsuariosExistentesENaoExistentes() {
        repository.salvar(new Usuarios("Ana", "ana@email.com", 1));

        assertTrue(repository.existePorNome("Ana"));
        assertFalse(repository.existePorNome("Outra Pessoa"));
    }

    @Test
    void buscarMaiorIdDeveRetornarProximoIdDisponivelSemDuplicar() {
        int primeiroId = repository.buscarMaiorId();
        repository.salvar(new Usuarios("A", "a@email.com", primeiroId));

        int segundoId = repository.buscarMaiorId();
        repository.salvar(new Usuarios("B", "b@email.com", segundoId));

        assertNotEquals(primeiroId, segundoId); // é exatamente esse o bug que você corrigiu antes — agora com teste travando a regressão
    }
}