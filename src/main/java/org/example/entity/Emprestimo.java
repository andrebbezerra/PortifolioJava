package org.example.entity;

import java.io.Serializable;
import java.time.LocalDate;

public record Emprestimo (
        Usuarios usuario,
        Livros livro,
        LocalDate dataEmprestimo,
        LocalDate dataEmprestimoFinal,
        LocalDate dataDevolucaoReal) implements Serializable{

    /** Um empréstimo está ativo enquanto não tiver data real de devolução. */
    public boolean estaAtivo() {
        return dataDevolucaoReal == null;
    }
    /**
     * "Atualiza" o empréstimo — na prática, devolve um Emprestimo NOVO
     * com a data de devolução preenchida. O objeto original continua imutável.
     */
    public Emprestimo devolver(LocalDate dataDevolucao) {
        return new Emprestimo(usuario, livro, dataEmprestimo, dataEmprestimoFinal, dataDevolucao);
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "usuario=" + usuario.buscarPorNome() +
                ", livro=" + livro.getNomeLivro() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataFinalEmprestimo=" + dataEmprestimoFinal +
                ", devolvido=" + !estaAtivo() +
                '}';
    }
}
