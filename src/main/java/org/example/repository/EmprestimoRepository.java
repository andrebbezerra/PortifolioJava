package org.example.repository;

import org.example.data.EmprestimoPersistencia;
import org.example.entity.Emprestimo;
import org.example.data.Serializacao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmprestimoRepository implements Repositorio<Emprestimo> {

    private final List<Emprestimo> emprestimos;
    private static final String ARQUIVO = "emprestimo.byte";
    private final Serializacao serializador = new Serializacao();

    public EmprestimoRepository() {
        Object dados = serializador.deserializar(ARQUIVO);

        if (dados instanceof EmprestimoPersistencia) {
            EmprestimoPersistencia persistencia = (EmprestimoPersistencia) dados;
            this.emprestimos = persistencia.getEmprestimos();
        } else {
            this.emprestimos = new ArrayList<>();
        }
    }

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
        serializador.serializar(new EmprestimoPersistencia(emprestimos), ARQUIVO);
        return emprestimo;
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return List.copyOf(emprestimos);
    }

    /**
     * Localiza o empréstimo ATIVO (ainda não devolvido) de um usuario para um livro.
     * É essa busca que a devolução vai usar.
     */
    public Optional<Emprestimo> buscarEmprestimoAtivo(String nomeUsuario, String nomeLivro) {
        return emprestimos.stream()
                .filter(Emprestimo::estaAtivo)
                .filter(e -> e.usuario().nome().equals(nomeUsuario))
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
     * Isso é o equivalente, em coleção imutável, a um atualizacao de linha.
     */
    public Emprestimo devolver(Emprestimo emprestimoAtivo, java.time.LocalDate dataDevolucao) {
        emprestimos.remove(emprestimoAtivo);
        Emprestimo devolvido = emprestimoAtivo.devolver(dataDevolucao);
        emprestimos.add(devolvido);
        serializador.serializar(new EmprestimoPersistencia(emprestimos), ARQUIVO);
        return devolvido;
    }

    @Override
    public void excluir(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
        serializador.serializar(new EmprestimoPersistencia(emprestimos), ARQUIVO);
    }
}
