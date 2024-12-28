package model.services;

import java.util.List;
import java.util.Scanner;

import db.GrupoDB;
import model.entities.Grupo;

public class AdmGrupo {

    public static void imprimirGrupos() throws Exception {
        List<Grupo> grupos = GrupoDB.listarGrupos();

        for (Grupo gp : grupos) {
            System.out.println("Código: " + gp.getId());
            System.out.println("Nome: " + gp.getNome());
            System.out.println("Coordenador: " + gp.getNomeCoordenador());
            System.out.println("Telefone do Coordenador: " + gp.getTelefoneCoordenador());
            System.out.println("Quantidade de Músicos: " + gp.getQuantidadeMusicos());
            System.out.println("Usuario: " + gp.getUser());
            System.out.println("Senha: " + gp.getSenha());
            System.out.println("-------------------------------");
        }
    }

    public static Integer autenticacaoLogin(Scanner input) throws Exception {

        System.out.println("User: ");
        String user = input.nextLine();
        System.out.println("User: ");
        String senha = input.nextLine();

        return GrupoDB.verificarUsuarioESenha(user, senha);
    }

    public static void loginGrupo(Scanner input) throws Exception {
        Integer grupo = autenticacaoLogin(input);
        if (grupo == null) {
            System.out.println("Usuário ou senha incorretos!");
        } else {
            System.out.println("Grupo: código " + grupo + " logado com sucesso!");

        }
    }

    public static void cadastrarGrupo(Scanner input) {
        try {
            System.out.println("Cadastro de Novo Grupo");
            System.out.print("Nome do grupo: ");
            String nome = input.nextLine();

            System.out.print("Nome do coordenador: ");
            String nomeCoordenador = input.nextLine();

            System.out.print("Telefone do coordenador: ");
            String telefoneCoordenador = input.nextLine();

            System.out.print("Quantidade de músicos: ");
            Integer quantidadeMusicos = null;
            while (quantidadeMusicos == null) {
                if (input.hasNextInt()) {
                    quantidadeMusicos = input.nextInt();
                    input.nextLine(); // Consome o restante da linha
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    input.next(); // Consome a entrada inválida
                }
            }

            System.out.print("Usuário para login: ");
            String user = input.nextLine();

            System.out.print("Senha: ");
            String senha = input.nextLine();

            // Cria uma instância do grupo
            Grupo novoGrupo = new Grupo(nome, nomeCoordenador, telefoneCoordenador, quantidadeMusicos, user, senha);

            // Insere o grupo no banco de dados
            GrupoDB.inserirGrupo(novoGrupo);

            System.out.println("Grupo cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar grupo: " + e.getMessage());
        }
    }

}
