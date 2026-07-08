package org.example.repository;
import java.util.List;
import java.util.Optional;

public interface Repositorio<T>{
    T salvar(T entidade);
    List<T> listarTodos();
    Optional<T> buscarPorNome(String nome);
    void editar(String nome);
    void excluir(T entidade);
}
