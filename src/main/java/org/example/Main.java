package org.example;
import org.example.entity.Autor;
import org.example.entity.GeneroLiterario;
import org.example.entity.Livros;
import org.example.repository.AutorRepository;
import org.example.repository.LivrosRepository;

import java.util.Scanner;

public class Main {
    static String nomeLivro;
    static String isbn;
    static String generoLiterario;

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
            System.out.println("7 - Consultar Livros por Categoria(DRAMA,TERROR,FICCAOCIENTIFICA,ACAO,AVENTURA,BIBLIOGRAFIA");
            System.out.println("8 - Sair do Programa");
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 1:
                    String retorno = adicionarLivro();
                    System.out.println(retorno);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
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
        AutorRepository autorRepository = new AutorRepository();
        autorRepository.salvar(new Autor(sc.nextLine()));
        System.out.println("Escolha o Genero do livro: DRAMA,TERROR, FICCAOCIENTIFICA, ACAO, AVENTURA, BIBLIOGRAFIA");
        generoLiterario = sc.nextLine();
        generoLiterario = generoLiterario.toUpperCase();

        try {
            LivrosRepository livros = new LivrosRepository();
            livros.salvar(new Livros(nomeLivro,autorRepository,isbn,GeneroLiterario.valueOf(generoLiterario)));
            return "Livro "+livros.buscarPorNome(nomeLivro)+" Cadastrado com sucesso!";
        } catch (IllegalArgumentException e) {
            return "Erro ao cadastrar livro: Gênero literário inválido. Por favor, escolha um dos gêneros listados.";
        }
    }
}
