package org.example.repository;

import org.example.entity.Emprestimo;
import org.example.data.Serializacao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmprestimoRepository extends Serializacao implements Repositorio<Emprestimo>{

    private final List<Emprestimo> emprestimos;
    private static final String ARQUIVO = "emprestimo.byte";

    public EmprestimoRepository() {
        Object dados = deserializar(ARQUIVO);
        this.emprestimos = (dados instanceof List<?>) ? (List<Emprestimo>) dados : new ArrayList<>();
    }

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
        serializar(emprestimos, ARQUIVO); // grava o SNAPSHOT inteiro
        return emprestimo;
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return List.copyOf(emprestimos);
    }

    @Override
    public Optional<Emprestimo> buscarPorNome(String nome) {
        // Emprestimo não tem "nome" — esse método existe só pra cumprir o contrato
        // da interface genérica. Ver nota sobre ISP no fim da explicação.
        return Optional.empty();
    }

    /**
     * Localiza o empréstimo ATIVO (ainda não devolvido) de um usuário para um livro.
     * É essa busca que a devolução vai usar.
     */
    public Optional<Emprestimo> buscarEmprestimoAtivo(String nomeUsuario, String nomeLivro) {
        return emprestimos.stream()
                .filter(Emprestimo::estaAtivo)
                .filter(e -> e.usuario().buscarPorNome().equals(nomeUsuario))
                .filter(e -> e.livro().getNomeLivro().equals(nomeLivro))
                .findFirst();
    }

    /** Verifica se um livro já está emprestado (uso na hora de EMPRESTAR, evita duplo empréstimo). */
    public boolean existeEmprestimoAtivoParaLivro(String nomeLivro) {
        return emprestimos.stream()
                .filter(Emprestimo::estaAtivo)
                .anyMatch(e -> e.livro().getNomeLivro().equals(nomeLivro));
    }

    /**
     * "Atualiza" a devolução: remove a versão antiga (ativa) e insere a nova (devolvida).
     * Isso é o equivalente, em coleção imutável, a um UPDATE de linha.
     */
    public Emprestimo devolver(Emprestimo emprestimoAtivo, java.time.LocalDate dataDevolucao) {
        emprestimos.remove(emprestimoAtivo);
        Emprestimo devolvido = emprestimoAtivo.devolver(dataDevolucao);
        emprestimos.add(devolvido);
        serializar(emprestimos, ARQUIVO); // grava o SNAPSHOT inteiro
        return devolvido;
    }

    @Override
    public void excluir(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
        serializar(emprestimos, ARQUIVO); // grava o SNAPSHOT inteiro
    }
}
