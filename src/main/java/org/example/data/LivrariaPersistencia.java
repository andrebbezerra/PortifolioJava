// src/main/java/org/example/data/LivrariaPersistencia.java
package org.example.data;

import org.example.entity.Livros;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LivrariaPersistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Livros> livros;

    public LivrariaPersistencia() {
        this.livros = new ArrayList<>();
    }

    public LivrariaPersistencia(List<Livros> livros) {
        this.livros = new ArrayList<>(livros);
    }

    public List<Livros> getLivros() {
        return new ArrayList<>(livros);
    }

    public void setLivros(List<Livros> livros) {
        this.livros.clear();
        this.livros.addAll(livros);
    }
}
