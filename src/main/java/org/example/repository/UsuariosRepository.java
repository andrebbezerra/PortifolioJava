package org.example.repository;

import org.example.entity.Usuarios;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuariosRepository implements Repositorio<Usuarios> {

    private final List<Usuarios> usuarios = new ArrayList<>();

    @Override
    public Usuarios salvar(Usuarios usuarios) {
        this.usuarios.add(usuarios);
        return usuarios;
    }

    @Override
    public List<Usuarios> listarTodos() {
        return List.copyOf(usuarios);
    }

    @Override
    public Optional<Usuarios> buscarPorNome(String nome) {
        return usuarios.stream()
                .filter(u -> u.buscarPorNome().equals(nome))
                .findFirst();
    }

    public boolean existePorNome(String nome) {
        return usuarios.stream().anyMatch(u -> u.buscarPorNome().equals(nome));
    }

    public int buscarMaiorId(){
        int maiorId = usuarios.stream()
                .mapToInt(Usuarios::id)
                .max()
                .orElse(0);
        return maiorId;
    }

    @Override
    public void excluir(Usuarios entidade) {

    }
}
