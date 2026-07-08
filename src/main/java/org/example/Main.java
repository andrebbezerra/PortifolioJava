package org.example;
import org.example.entity.Author;
import org.example.entity.GeneroLiterario;
import org.example.entity.Livros;
import org.example.entity.Manga;
import org.example.repository.AuthorRepository;
import org.example.repository.LivrosRepository;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    static String nomeLivro;
    static String isbn;
    static String generoLiterario;
    static AuthorRepository autorRepository = new AuthorRepository();
    static LivrosRepository livros = new LivrosRepository();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean continuarExecutando  = true;
        int opcao;

        while (continuarExecutando ){
            System.out.println("Qual opção você gostaria de executar?");
            System.out.println("-------------------------------------");
            System.out.println("1 - Adicionar um livro");
            System.out.println("2 - Atualizar um livro");
            System.out.println("3 - Excluir um livro");
            System.out.println("4 - Consultar um livro Cadastrado pelo nome");
            System.out.println("5 - Consultar Lista de livros Cadastrados");
            System.out.println("6 - Consultar Livros por Autor");
            System.out.println("7 - Consultar Livros por Categoria(DRAMA,TERROR,FICCAOCIENTIFICA,ACAO,AVENTURA,BIOGRAFIA)");
            System.out.println("8 - Sair do Programa");
            opcao = sc.nextInt();
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
                    continuarExecutando  = false;
                    break;
                default: System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 8.");
            }
        }
        System.out.println("Obrigado por usar o nosso sistema");
         sc.close();
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
