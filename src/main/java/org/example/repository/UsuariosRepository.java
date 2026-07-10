package org.example.repository;

import org.example.data.Serializacao;
import org.example.data.UsuariosPersistencia;
import org.example.entity.Usuarios;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuariosRepository extends Serializacao implements Repositorio<Usuarios>,BuscaRepositorio<Usuarios> {

    private List<Usuarios> usuarios;
    private static final String ARQUIVO = "usuario.byte";

    public UsuariosRepository() {
        Object dados = deserializar(ARQUIVO);

        if (dados instanceof UsuariosPersistencia) {
            UsuariosPersistencia persistencia = (UsuariosPersistencia) dados;
            this.usuarios = persistencia.getUsuarios();
        } else {
            this.usuarios = new ArrayList<>();
        }
    }

    @Override
    public Usuarios salvar(Usuarios usuario) {
        this.usuarios.add(usuario);
        serializar(new UsuariosPersistencia(usuarios), ARQUIVO);
        return usuario;
    }

    @Override
    public void excluir(Usuarios usuario) {
        this.usuarios.remove(usuario);
        serializar(new UsuariosPersistencia(usuarios), ARQUIVO);
    }

    @Override
    public List<Usuarios> listarTodos() {
        return List.copyOf(usuarios);
    }

    public boolean existePorNome(String nome) {
        return usuarios.stream().anyMatch(u -> u.nome().equals(nome));
    }

    public int buscarMaiorId(){
        int maiorId = usuarios.stream()
                .mapToInt(Usuarios::id)
                .max()
                .orElse(0);
        return maiorId + 1;
    }

    @Override
    public Optional<Usuarios> buscarPorNome(String nome) {
        return Optional.empty();
    }
}
