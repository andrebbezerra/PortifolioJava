package org.example.repository;

import org.example.entity.Emprestimo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmprestimoRepository implements Repositorio<Emprestimo>{

    List<Emprestimo> emprestimos = new ArrayList<>();

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public boolean devolverEmprestimo(String usuario, String livro) {
        
        List<Emprestimo> filtrados = emprestimos.stream()
                .filter(e -> e.livro().equals(livro))
                .toList();

        try{
            boolean remove = emprestimos.remove(filtrados);
            return true;
        }catch(Exception e){
            return false;
        }

    }

    @Override
    public List<Emprestimo> listarTodos() {
        return List.of();
    }

    @Override
    public Optional<Emprestimo> buscarPorNome(String nome) {
        return Optional.empty();
    }

    @Override
    public void excluir(Emprestimo emprestimo) {

    }
}
