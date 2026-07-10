// src/main/java/org/example/data/EmprestimoPersistencia.java
package org.example.data;

import org.example.entity.Emprestimo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoPersistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Emprestimo> emprestimos;

    public EmprestimoPersistencia() {
        this.emprestimos = new ArrayList<>();
    }

    public EmprestimoPersistencia(List<Emprestimo> emprestimos) {
        this.emprestimos = new ArrayList<>(emprestimos);
    }

    public List<Emprestimo> getEmprestimos() {
        return new ArrayList<>(emprestimos);
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos.clear();
        this.emprestimos.addAll(emprestimos);
    }
}
