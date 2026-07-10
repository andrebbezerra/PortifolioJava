package org.example;
import org.example.entity.*;
import org.example.repository.AuthorRepository;
import org.example.repository.EmprestimoRepository;
import org.example.repository.LivrosRepository;
import org.example.repository.UsuariosRepository;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    static String nomeLivro;
    static String isbn;
    static String generoLiterario;
    static AuthorRepository autorRepository = new AuthorRepository();
    static LivrosRepository livros = new LivrosRepository();
    static UsuariosRepository usuarios = new UsuariosRepository();
    static EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean continuarExecutando  = true;
        int opcao = 11;

        while (continuarExecutando ){
            System.out.println("Qual opção você gostaria de executar?");
            System.out.println("-------------------------------------");
            System.out.println("1  - Adicionar um livro");
            System.out.println("2  - Atualizar um livro");
            System.out.println("3  - Excluir um livro");
            System.out.println("4  - Consultar um livro Cadastrado pelo nome");
            System.out.println("5  - Consultar Lista de livros Cadastrados");
            System.out.println("6  - Consultar Livros por Autor");
            System.out.println("7  - Consultar Livros por Categoria(DRAMA,TERROR,FICCAOCIENTIFICA,ACAO,AVENTURA,BIOGRAFIA)");
            System.out.println("8  - Cadastrar Usuario:");
            System.out.println("9  - Solicitar Emprestimo de Livro:");
            System.out.println("10 - Devolver Emprestimo de Livro:");
            System.out.println("11 - Sair do Programa");
            if(sc.hasNextInt()){
                opcao = sc.nextInt();
            }
            else{
                System.out.println("Erro: O valor digitado não é um número inteiro válido.");
            }
            sc.nextLine();
            switch(opcao){
                case 1:
                    System.out.println(adicionarLivro());
                    break;
                case 2:
                    System.out.println(atualizarLivro());
                    break;
                case 3:
                    System.out.println(excluirLivro());
                    break;
                case 4:
                    System.out.println("Digite o nome:");
                    nomeLivro = sc.nextLine();
                    System.out.println(livros.buscarPorNome(nomeLivro).map(l -> "Livro " + l.getNomeLivro() + " Está Cadastrado").orElse("Livro não encontrado"));
                    break;
                case 5:
                    System.out.println(livros.listarTodos());
                    break;
                case 6:
                    System.out.println("Digite o nome do autor:");
                    System.out.println(livros.buscarPorAuthor(sc.nextLine()).map(Livros::getNomeLivro).orElse("Autor não encontrado"));
                    break;
                case 7:
                    System.out.println("Digite a categoria:");
                    System.out.println(livros.buscarPorCategoria(sc.nextLine()).map(Livros::getNomeLivro).orElse("Categoria não encontrada"));
                    break;
                case 8:
                    System.out.println(cadastrarUsuario());
                    break;
                case 9:
                    System.out.println(solicitarEmprestimoLivro());
                    break;
                case 10:
                    System.out.println(devolverEmprestimoLivro());
                    break;
                case 11:
                    continuarExecutando  = false;
                    break;
                default: System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 11.");
            }
        }
        System.out.println("Obrigado por usar o nosso sistema");
         sc.close();
    }

    private static String solicitarEmprestimoLivro() {
        System.out.println("Digite o usuario:");
        String usuario = sc.nextLine();

        Optional<Usuarios> usuarioEncontrado = usuarios.buscarPorNome(usuario);
        if (usuarioEncontrado.isEmpty()) {
            return "Usuario não encontrado";
        }

        System.out.println("Digite o nome livro que você quer pegar emprestado:");
        String nomeLivro = sc.nextLine();

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

    private static String devolverEmprestimoLivro() {
        System.out.println("Digite o usuario para devolver o livro:");
        String usuario = sc.nextLine();

        System.out.println("Digite o nome do livro que quer devolver:");
        String nomeLivro = sc.nextLine();

        Optional<Emprestimo> emprestimoAtivo =
                emprestimoRepository.buscarEmprestimoAtivo(usuario, nomeLivro);

        if (emprestimoAtivo.isEmpty()) {
            return "Não foi encontrado um empréstimo ativo para esse usuário e livro.";
        }

        emprestimoRepository.devolver(emprestimoAtivo.get(), LocalDate.now());

        return "Livro devolvido com sucesso!";
    }

    private static String cadastrarUsuario() {
        System.out.println("Digite o nome do usuario:");
        String nome = sc.nextLine();
        System.out.println("Digite o email:");
        String email = sc.nextLine();
        int maiorId = usuarios.buscarMaiorId();

        try {
            usuarios.salvar(new Usuarios(nome, email, maiorId));
            return "Usuario cadastrado com sucesso";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    private static String adicionarLivro(){

        System.out.println("Digite o nome:");
        nomeLivro = sc.nextLine();
        System.out.println("Digite o ISBN:");
        isbn = sc.nextLine();
        System.out.println("Digite o Autor:");
        Author author = new Author(sc.nextLine());
        autorRepository.salvar(author);
        System.out.println("Escolha o Genero do livro: DRAMA,TERROR, FICCAOCIENTIFICA, ACAO, AVENTURA, BIOGRAFIA,MANGA");
        generoLiterario = sc.nextLine();
        generoLiterario = generoLiterario.toUpperCase();
        if(generoLiterario.equals("MANGA")){
            System.out.println("Digite a edição do Mangá:");
            try {
                Manga manga = new Manga(nomeLivro, author, isbn, GeneroLiterario.valueOf(generoLiterario), sc.nextLine());
                livros.salvar(manga);
                return "Livro Cadastrado com Sucesso";
            }catch (IllegalArgumentException e) {
                return "Erro ao cadastrar livro: Gênero literário inválido. Por favor, escolha um dos gêneros listados.";
            }
        } else{
            try {
                livros.salvar(new Livros(nomeLivro,author,isbn,GeneroLiterario.valueOf(generoLiterario)));

                return "Livro Cadastrado com Sucesso";
            } catch (IllegalArgumentException e) {
                return "Erro ao cadastrar livro: Gênero literário inválido. Por favor, escolha um dos gêneros listados.";
            }
        }
    }

    private static String atualizarLivro(){
        System.out.println("Digite o nome do livro a ser alterado:");
        nomeLivro = sc.nextLine();
        Optional<Livros> livroAlterar= livros.buscarPorNome(nomeLivro);
        if(livroAlterar.isPresent()){
            System.out.println("Livro encontrado com sucesso!");
            System.out.println("-------------------------------------");
            System.out.println("Digite o nome:");
            nomeLivro = sc.nextLine();
            System.out.println("Digite o ISBN:");
            isbn = sc.nextLine();
            System.out.println("Digite o Autor:");
            Author author = new Author(sc.nextLine());
            autorRepository.salvar(author);
            System.out.println("Escolha o Genero do livro: DRAMA,TERROR, FICCAOCIENTIFICA, ACAO, AVENTURA, BIOGRAFIA,MANGA");
            generoLiterario = sc.nextLine();
            generoLiterario = generoLiterario.toUpperCase();
            livros.excluir(livroAlterar.get());
            if(generoLiterario.equals("MANGA")){
                System.out.println("Digite a edição do Mangá:");
                try {
                    Manga manga = new Manga(nomeLivro, author, isbn, GeneroLiterario.valueOf(generoLiterario), sc.nextLine());
                    livros.salvar(manga);
                    return livros.buscarPorNome(nomeLivro)
                            .map(l -> "Livro " + l.nomeLivro() + " cadastrado com sucesso!")
                            .orElse("Erro ao cadastrar o livro.");
                }catch (IllegalArgumentException e) {
                    return "Erro ao cadastrar livro: Gênero literário inválido. Por favor, escolha um dos gêneros listados.";
                }
            } else{
                try {
                    livros.salvar(new Livros(nomeLivro,author,isbn,GeneroLiterario.valueOf(generoLiterario)));

                    return livros.buscarPorNome(nomeLivro)
                            .map(l -> "Livro " + l.nomeLivro() + " cadastrado com sucesso!")
                            .orElse("Erro ao cadastrar o livro.");
                } catch (IllegalArgumentException e) {
                    return "Erro ao cadastrar livro: Gênero literário inválido. Por favor, escolha um dos gêneros listados.";
                }
            }

        }
        else return "Erro ao atualizar livro.";

    }

    private static String excluirLivro() {
        System.out.println("Digite o nome do livro a ser excluido:");
        nomeLivro = sc.nextLine();
        Optional<Livros> livroAlterar= livros.buscarPorNome(nomeLivro);
        if(livroAlterar.isPresent()){
            livros.excluir(livroAlterar.get());
            return "Livro Excluido com sucesso";
        }
        else{
            return "Falha ao excluir livro";
        }
    }
}
