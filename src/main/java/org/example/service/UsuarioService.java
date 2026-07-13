package org.example.service;

import org.example.entity.Usuarios;
import org.example.repository.UsuariosRepository;

public class UsuarioService {
    static UsuariosRepository usuarios = new UsuariosRepository();

    public String adicionarUsuarioService(String nome, String email){
        if (usuarios.existePorNome(nome)) {
            return "Já existe um usuário cadastrado com esse nome.";
        }

        int maiorId = usuarios.buscarMaiorId();
        usuarios.salvar(new Usuarios(nome, email, maiorId));
        return "Usuario cadastrado com sucesso";
    }
}
