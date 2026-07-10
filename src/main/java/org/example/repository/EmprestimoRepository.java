package org.example.repository;

import org.example.entity.Emprestimo;
import org.example.entity.Usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmprestimoRepository implements Repositorio<Emprestimo>{

    List<Emprestimo> emprestimos = new ArrayList<>();

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return List.copyOf(emprestimos);
    }

    @Override
    public Optional<Emprestimo> buscarPorNome(String nome) {
        return Optional.empty();
    }

    @Override
    public void excluir(Emprestimo emprestimo) {

    }
}
