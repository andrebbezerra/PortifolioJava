package org.example.repository;

import org.example.data.Serializacao;
import org.example.data.UsuariosPersistencia;
import org.example.entity.Usuarios;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuariosRepository implements Repositorio<Usuarios>,BuscaRepositorio<Usuarios> {

    private List<Usuarios> usuarios;
    private static final String ARQUIVO = "usuario.byte";
    private final Serializacao serializador = new Serializacao(); // Composição!

    public UsuariosRepository() {
        Object dados = serializador.deserializar(ARQUIVO);

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
        serializador.serializar(new UsuariosPersistencia(usuarios), ARQUIVO);
        return usuario;
    }

    @Override
    public void excluir(Usuarios usuario) {
        this.usuarios.remove(usuario);
        serializador.serializar(new UsuariosPersistencia(usuarios), ARQUIVO);
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
        return usuarios.stream()
                .filter(u -> u.nome().equals(nome))
                .findFirst();
    }
}
