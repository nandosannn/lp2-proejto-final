package util;

import java.sql.Connection;
import java.util.Scanner;

import model.entities.Adm;
import model.entities.Solicitante;
import model.services.AdmGrupo;
import model.services.AdmSolicitante;

public final class Util {

    private Util() {
        throw new UnsupportedOperationException("Não pode ser iniciada!");
    }

    public static void menuMain(Connection conn, Scanner input) throws Exception {
        int opcao;

        do {
            System.out.println("[1] Solicitante");
            System.out.println("[2] Grupo");
            System.out.println("[3] Adm");
            System.out.println("[4] Finalizar");

            System.out.print("Digite o número da operação: ");
            if (input.hasNextInt()) {
                opcao = input.nextInt();
                input.nextLine(); // Consome a quebra de linha

                switch (opcao) {
                    case 1:
                        menuSolicitante(input);
                        break;
                    case 2:
                        menuGrupo(input);
                        break;
                    case 3:
                        System.out.print("Digite o login: ");
                        String login = input.nextLine();
                        System.out.print("Digite a senha: ");
                        String senha = input.nextLine();

                        if (Adm.validarLogin(login, senha)) {
                            System.out.println("Login feito com sucesso");
                        } else {
                            System.out.println("Acesso negado!");
                        }
                        break;
                    case 4:
                        System.out.println("A Escola de Música da UFRN agradece a confiança!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                input.next(); // Consome a entrada inválida
                opcao = -1; // Garante que o loop continue
            }
        } while (opcao != 4);
    }

    public static void menuSolicitante(Scanner input) throws Exception {
        int opcao;

        do {
            System.out.println("\n--- Menu Solicitante ---");
            System.out.println("[1] Login");
            System.out.println("[2] Cadastrar Conta");
            System.out.println("[3] Finalizar");

            System.out.print("Digite o número da operação: ");
            if (input.hasNextInt()) {
                opcao = input.nextInt();
                input.nextLine(); // Consome a quebra de linha

                switch (opcao) {
                    case 1:
                        AdmSolicitante.loginSolicitante(input);
                        break;
                    case 2:
                        AdmSolicitante.cadastrarSolicitante(input);
                        break;
                    case 3:
                        System.out.println("Saindo do menu solicitante.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                input.next(); // Consome a entrada inválida
                opcao = -1; // Garante que o loop continue
            }
        } while (opcao != 3);
    }

    public static void menuGrupo(Scanner input) throws Exception {
        int opcao;

        do {
            System.out.println("[1] login");
            System.out.println("[2] Cadastrar conta");
            System.out.println("[3] Finalizar");

            System.out.print("Digite o número da operação: ");
            if (input.hasNextInt()) {
                opcao = input.nextInt();
                input.nextLine(); // Consome a quebra de linha

                switch (opcao) {
                    case 1:
                        AdmGrupo.loginGrupo(input);
                        break;
                    case 2:
                        AdmGrupo.cadastrarGrupo(input);
                        break;
                    case 3:
                        System.out.println("Saindo do menu solicitante.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                input.next(); // Consome a entrada inválida
                opcao = -1; // Garante que o loop continue
            }
        } while (opcao != 3);
    }

    public static void menuContaGrupo(Scanner input) {
        int opcao;

        do {
            System.out.println("\nMenu de Eventos");
            System.out.println("[1] Eventos Abertos");
            System.out.println("[2] Confirmar Disponibilidade");
            System.out.println("[3] Acompanhar Eventos");
            System.out.println("[4] Voltar ao Menu Principal");

            System.out.print("Digite o número da operação: ");
            opcao = input.nextInt();
            input.nextLine(); // Consome o restante da linha

            switch (opcao) {
                case 1:
                    // listarEventosAbertos();
                    break;
                case 2:
                    // confirmarDisponibilidade(input);
                    break;
                case 3:
                    // acompanharEventos();
                    break;
                case 4:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
            }
        } while (opcao != 4);
    }

    public static void menuContaSolicitante(Solicitante solicitante, Scanner input) throws Exception {
        int opcao;

        do {
            System.out.println("\n=== Menu Conta Solicitante ===");
            System.out.println("[1] Solicitar apresentações");
            System.out.println("[2] Acompanhar apresentações");
            System.out.println("[3] Confirmar transporte");
            System.out.println("[4] Editar apresentação");
            System.out.println("[5] Sair");
            System.out.print("Escolha uma opção: ");

            opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    AdmSolicitante.cadastrarEvento(solicitante, input);
                    break;
                case 2:
                    AdmSolicitante.acompanharApresentacoes(input);
                    break;
                case 3:
                    // confirmarTransporte(input);
                    break;
                case 4:
                    // editarApresentacao(input);
                    break;
                case 5:
                    System.out.println("Saindo do menu...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 5);
    }
}
