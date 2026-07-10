package org.example.repository;
import java.util.List;
import java.util.Optional;

public interface Repositorio<T>{
    T salvar(T entidade);
    void excluir(T entidade);
    List<T> listarTodos();
}
