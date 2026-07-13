package org.example.service;

import org.example.entity.Emprestimo;
import org.example.entity.Livros;
import org.example.entity.Usuarios;
import org.example.repository.EmprestimoRepository;
import org.example.repository.LivrosRepository;
import org.example.repository.UsuariosRepository;

import java.time.LocalDate;
import java.util.Optional;



public class EmprestimoService {

    static UsuariosRepository usuarios = new UsuariosRepository();
    static LivrosRepository livros = new LivrosRepository();
    static EmprestimoRepository emprestimoRepository = new EmprestimoRepository();


    public String solicitarEmprestimoService(String nomeUsuario, String nomeLivro){

        Optional<Usuarios> usuarioEncontrado = usuarios.buscarPorNome(nomeUsuario);
        if (usuarioEncontrado.isEmpty()) {
            return "Usuario não encontrado";
        }

        Optional<Livros> livroEncontrado = livros.buscarPorNome(nomeLivro);
        if (livroEncontrado.isEmpty()) {
            return "Livro não encontrado";
        }

        if (emprestimoRepository.existeEmprestimoAtivoParaLivro(nomeLivro)) {
            return "Este livro já está emprestado e ainda não foi devolvido.";
        }

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataFinalEmprestimo = dataEmprestimo.plusDays(30);

        emprestimoRepository.salvar(new Emprestimo(
                usuarioEncontrado.get(),
                livroEncontrado.get(),
                dataEmprestimo,
                dataFinalEmprestimo,
                null // ainda não devolvido
        ));

        return "O livro foi emprestado com sucesso, você tem até o dia: " + dataFinalEmprestimo + " para devolver o livro.";
    }

    public String devolverEmprestimoService(String nomeUsuario, String nomeLivro){

        Optional<Emprestimo> emprestimoAtivo =
                emprestimoRepository.buscarEmprestimoAtivo(nomeUsuario, nomeLivro);

        if (emprestimoAtivo.isEmpty()) {
            return "Não foi encontrado um empréstimo ativo para esse usuário e livro.";
        }

        emprestimoRepository.devolver(emprestimoAtivo.get(), LocalDate.now());

        return "Livro devolvido com sucesso!";
    }
}
