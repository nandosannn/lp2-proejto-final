package util;

import java.sql.Connection;
import java.util.Scanner;

import model.entities.Adm;
import model.entities.Grupo;
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
                            menuAdm(input);
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

    public static void menuAdm(Scanner input) throws Exception {
        int opcao;

        do {
            System.out.println("\n=== Menu Adm ===");
            System.out.println("[1] Lista todas apresentações");
            System.out.println("[2] Confirmar apresentação");
            System.out.println("[3] Relatórios");
            System.out.println("[4] Finalizar");

            System.out.print("Digite o número da operação: ");

            if (input.hasNextInt()) {
                opcao = input.nextInt();
                input.nextLine(); // Consome a quebra de linha

                switch (opcao) {
                    case 1:
                        Adm.listaTodasApresentacoes();
                        break;
                    case 2:
                        Adm.confirmarApresentacoes(input);
                        break;
                    case 3:
                        menuAdmRelatorios(input);
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opcão invalida.");
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

    public static void menuContaSolicitante(Solicitante solicitante, Scanner input) throws Exception {
        int opcao;

        do {
            System.out.println("\n=== Menu Conta Solicitante ===");
            System.out.println("[1] Solicitar apresentações");
            System.out.println("[2] Acompanhar apresentações");
            System.out.println("[3] Transporte");
            System.out.println("[4] Cencelar apresentação");
            System.out.println("[5] Sair");
            System.out.print("Escolha uma opção: ");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    AdmSolicitante.cadastrarEvento(solicitante, input);
                    break;
                case 2:
                    AdmSolicitante.acompanharApresentacoes(input);
                    break;
                case 3:
                    AdmSolicitante.menuTransporte(input);
                    break;
                case 4:
                    AdmSolicitante.cancelarApresentacao(input);
                    break;
                case 5:
                    System.out.println("Saindo do menu...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 5);
    }

    public static void menuGrupo(Scanner input) throws Exception {
        int opcao;

        do {
            System.out.println("\n--- Menu Grupo ---");
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

    public static void menuContaGrupo(Grupo grupo, Scanner input) throws Exception {
        int opcao;

        System.out.println("Bem vindo " + grupo.getNome() + "!");
        do {
            System.out.println("\nMenu de Grupo");
            System.out.println("[1] Eventos Abertos");
            System.out.println("[2] Confirmar Disponibilidade");
            System.out.println("[3] Acompanhar Eventos");
            System.out.println("[4] Voltar ao Menu Principal");

            System.out.print("Digite o número da operação: ");
            opcao = input.nextInt();
            input.nextLine(); // Consome o restante da linha

            switch (opcao) {
                case 1:
                    AdmGrupo.listarEventosAbertos();
                    break;
                case 2:
                    AdmGrupo.confirmarDisponibilidade(grupo, input);
                    break;
                case 3:
                    AdmGrupo.acompanharEventosGrupo(grupo);
                    break;
                case 4:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
            }
        } while (opcao != 4);
    }

    public static void menuAdmRelatorios(Scanner input) throws Exception {
        int opcao;

        do {
            System.out.println("\n=== Opcões de Relatórios ===");
            System.out.println("[1] Eventos por mês");
            System.out.println("[2] Eventos sem grupo confirmado");
            System.out.println("[3] Evento com grupo confirmado");
            System.out.println("[4] Eventos abertos");
            System.out.println("[5] Evento por grupo");
            System.out.println("[6] Sair");
            System.out.print("Digite o número da operação: ");

            opcao = input.nextInt();
            input.nextLine(); // Consome o restante da linha

            switch (opcao) {
                case 1:
                    Adm.eventoPorMes(input);
                    break;
                case 2:
                    Adm.eventoGrupoNaoConfirmado();
                    break;
                case 3:
                    Adm.eventoGrupoConfirmado();
                    break;
                case 4:
                    Adm.eventoAbertos();
                    break;
                case 5:
                    Adm.eventoPorGrupo(input);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                System.out.println("Opcao invalida!");
                    break;
            }

        } while (opcao != 6);
    }
}
