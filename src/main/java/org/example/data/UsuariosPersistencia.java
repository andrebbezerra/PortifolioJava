// src/main/java/org/example/data/UsuariosPersistencia.java
package org.example.data;

import org.example.entity.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuariosPersistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Usuarios> usuarios;

    public UsuariosPersistencia() {
        this.usuarios = new ArrayList<>();
    }

    public UsuariosPersistencia(List<Usuarios> usuarios) {
        this.usuarios = new ArrayList<>(usuarios);
    }

    public List<Usuarios> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios.clear();
        this.usuarios.addAll(usuarios);
    }
}
