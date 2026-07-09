package org.example.entity;

import java.time.LocalDate;
import java.util.Optional;

public record Emprestimo(java.util.Optional<Usuarios> usuario, java.util.Optional<Livros> livro, LocalDate dataEmprestimo, LocalDate dataFinalEmprestimo) {

    @Override
    public String toString() {
        return "Emprestimo{" +
                "usuario=" + usuario +
                ", livro=" + livro +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataFinalEmprestimo=" + dataFinalEmprestimo +
                '}';
    }
}
