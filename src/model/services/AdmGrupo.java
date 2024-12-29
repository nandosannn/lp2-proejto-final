package model.services;

import java.util.List;
import java.util.Scanner;

import db.EventoDB;
import db.GrupoDB;
import model.entities.Evento;
import model.entities.Grupo;
import util.Util;

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

        System.out.print("User: ");
        String user = input.nextLine();
        System.out.print("Senha: ");
        String senha = input.nextLine();

        return GrupoDB.verificarUsuarioESenha(user, senha);
    }

    public static void loginGrupo(Scanner input) throws Exception {
        Integer grupo = autenticacaoLogin(input);
        if (grupo == null) {
            System.out.println("Usuário ou senha incorretos!");
        } else {
            System.out.println("Grupo: código " + grupo + " logado com sucesso!");
            Util.menuContaGrupo(GrupoDB.procurarPorId(grupo), input);
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

    /* === Menu conta grupo === */
    public static void listarEventosAbertos() throws Exception {
        List<Evento> eventos = EventoDB.listarEventosAbertos();

        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado.");
        } else {
            for (Evento evento : eventos) {
                System.out.println("ID: " + evento.getId());
                System.out.println("Nome: " + evento.getNome());
                System.out.println("Local: " + evento.getLocal());
                System.out.println("Data e Hora: " + evento.getDataHora());
                System.out.println("Status: " + evento.getStatus());
                System.out.println("Aguardando disponibilidade de grupo!");
                System.out.println("-------------------------------------");
            }
        }
    }

    public static void confirmarDisponibilidade(Grupo grupo, Scanner input) throws Exception {
        System.out.print("Digite o código do evento que deseja cancelar: ");
        int eventoId = input.nextInt();
        input.nextLine();

        Evento evento = EventoDB.procurarPorId(eventoId);

        if (evento == null) {
            System.out.println("Evento não encontrado!");
            return;
        }

        if (evento.getStatus() != Evento.Status.PENDENTE) {
            System.out.println("Evento cancelado ou confirmado!");
        }

        evento.setGrupo(grupo);

        EventoDB.atualizar(evento);

        System.out.println("Evento '" + evento.getNome() + "' foi CONFIRMADO com sucesso!");

    }

    public static void acompanharEventosGrupo(Grupo grupo) throws Exception {
        List<Evento> eventos = EventoDB.listarEventosPorGrupo(grupo.getId());

        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado!");
        } else {
            for (Evento evento : eventos) {
                // Imprime os atributos do evento
                System.out.println("Código do Evento: " + evento.getId());
                System.out.println("Nome do Evento: " + evento.getNome());
                System.out.println("Local do Evento: " + evento.getLocal());
                System.out.println("Data e Hora do Evento: " + evento.getDataHora());
                System.out.println("Status do Evento: " + evento.getStatus());
                System.out.println("Grupo: " + grupo.getNome());

                // Imprime os dados do transporte
                String disponibilidade = "Aguardando confirmação de transporte";
                if (disponibilidade.equalsIgnoreCase(evento.getTransporte().getNomeMotorista())) {
                    System.out.println("Aguardando confirmação de Transporte");
                }
                else{
                    System.out.println("Nome do Motorista: " + evento.getTransporte().getNomeMotorista());
                }

                // Imprime os dados do solicitante
                System.out.println("Solicitante: " + evento.getSolicitante().getNome());

                System.out.println("====================================="); // Linha de separação entre eventos
            }
        }

    }

}
