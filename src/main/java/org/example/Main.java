package org.example;
import org.example.dto.AutorDto;
import org.example.dto.LivrosDto;
import org.example.entity.Autor;
import org.example.entity.GeneroLiterario;
import org.example.entity.Livros;

import java.util.Scanner;

public class Main {
    static String nomeLivro;
    static String ISBN;
    static String generoLiterario;

    static Scanner sc = new Scanner(System.in);
     public static void main(String[] args) {

        boolean sairdoSitema = true;
        int opcao;

        while (sairdoSitema){
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
                    sairdoSitema = false;
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
        ISBN = sc.nextLine();
        System.out.println("Digite o Autor:");
        AutorDto autorDto = new AutorDto();
        autorDto.cadastrarAutor(sc.nextLine());
        System.out.println("Escolha o Genero do livro: DRAMA,TERROR, FICCAOCIENTIFICA, ACAO, AVENTURA, BIBLIOGRAFIA");
        generoLiterario = sc.nextLine();
        generoLiterario = generoLiterario.toUpperCase();

        try {
            LivrosDto livrodto = new LivrosDto();
            livrodto.cadastrarLivros(nomeLivro,autorDto,ISBN,GeneroLiterario.valueOf(generoLiterario));
            return "Livro "+LivrosDto.getListaLivrosPeloNome(nomeLivro)+" Cadastrado com sucesso!";
        } catch (IllegalArgumentException e) {
            return "Erro ao cadastrar livro: Gênero literário inválido. Por favor, escolha um dos gêneros listados.";
        }
    }
}
