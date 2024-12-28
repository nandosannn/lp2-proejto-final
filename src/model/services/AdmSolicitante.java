package model.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import db.EventoDB;
import db.GrupoDB;
import db.SolicitanteDB;
import db.TransporteDB;
import model.entities.Evento;
import model.entities.Solicitante;
import model.entities.Transporte;
import util.Util;

public class AdmSolicitante {

    public static void imprimirSolicitantes() throws Exception {
        List<Solicitante> solicitantes = SolicitanteDB.listarSolicitantes();

        for (Solicitante solicitante : solicitantes) {
            System.out.println("Código: " + solicitante.getId());
            System.out.println("Nome: " + solicitante.getNome());
            System.out.println("Cargo: " + solicitante.getCargo());
            System.out.println("Telefone: " + solicitante.getTelefone());
            System.out.println("Email: " + solicitante.getEmail());
            System.out.println("Usuario: " + solicitante.getUser());
            System.out.println("Senha: " + solicitante.getSenha());
            System.out.println("-------------------------------");
        }
    }

    public static Integer autenticacaoLoginSolicitante(Scanner input) throws Exception {

        System.out.println("User: ");
        String user = input.nextLine();
        System.out.println("User: ");
        String senha = input.nextLine();

        return SolicitanteDB.verificarLoginESenha(user, senha);
    }

    public static void loginSolicitante(Scanner input) throws Exception {
        Integer solicitante = autenticacaoLoginSolicitante(input);
        if (solicitante == null) {
            System.out.println("Usuário ou senha incorretos!");
        } else {
            System.out.println("Grupo: código " + solicitante + " logado com sucesso!");
            Util.menuContaSolicitante(SolicitanteDB.procurarSolicitantePorId(solicitante), input);
        }
    }

    public static void cadastrarSolicitante(Scanner input) {
        try {
            System.out.println("=== Cadastro de Solicitante ===");

            System.out.print("Digite o nome: ");
            String nome = input.nextLine();

            System.out.print("Digite o cargo: ");
            String cargo = input.nextLine();

            System.out.print("Digite o telefone: ");
            String telefone = input.nextLine();

            System.out.print("Digite o email: ");
            String email = input.nextLine();

            System.out.print("Digite o usuário: ");
            String usuario = input.nextLine();

            System.out.print("Digite a senha: ");
            String senha = input.nextLine();

            // Cria um objeto Solicitante com os dados fornecidos
            Solicitante solicitante = new Solicitante(nome, cargo, telefone, email, usuario, senha);

            // Chama o método para inserir o solicitante no banco de dados
            SolicitanteDB.inserirSolicitante(solicitante);

            System.out.println("Solicitante cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar solicitante: " + e.getMessage());
        }
    }

    public static void cadastrarEvento(Solicitante solicitante, Scanner input) {
        try {
            System.out.println("\n=== Cadastrar Evento ===");

            System.out.print("Informe o nome do evento: ");
            String nome = input.nextLine();

            System.out.print("Informe o local do evento: ");
            String local = input.nextLine();

            System.out.print("Informe a data e hora do evento (formato yyyy-MM-dd HH:mm): ");
            String dataHoraStr = input.nextLine();
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            // Cria o evento com os dados informados e padrão
            Evento evento = new Evento(nome, local, dataHora, null, Evento.Status.PENDENTE, null, solicitante);

            // Insere o evento no banco de dados
            EventoDB.inserir(evento);

            System.out.println("Evento cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar evento: " + e.getMessage());
        }
    }

    public static void acompanharApresentacoes(Scanner input) throws Exception {
        int opcao;

        System.out.println("=== Acompanhar apresentações ===");
        System.out.println("[1] Lista de todos os Eventos");
        System.out.println("[2] Lista de eventos com grupos confirmados");
        System.out.println("[3] Lista de eventos aguardando disponibilidade de grupo");

        System.out.println("Escolha uma opção: ");

        opcao = input.nextInt();

        switch (opcao) {
            case 1:
                List<Evento> eventos = EventoDB.listarEventos();
                exibirEventos(eventos); // Método que exibe os eventos
                break;
            case 2:
                List<Evento> eventosComGrupoConfirmado = EventoDB.listarEventosComGrupoDiferenteDoPadrao();
                exibirEventos(eventosComGrupoConfirmado); // Método que exibe os eventos
                break;
            case 3:
                List<Evento> eventosComGrupoAguardando = EventoDB.listarEventosComGrupoPadrao();
                exibirEventos(eventosComGrupoAguardando); // Método que exibe os eventos
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }

    public static void exibirEventos(List<Evento> eventos) {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado.");
        } else {
            for (Evento evento : eventos) {
                System.out.println("ID: " + evento.getId());
                System.out.println("Nome: " + evento.getNome());
                System.out.println("Local: " + evento.getLocal());
                System.out.println("Data e Hora: " + evento.getDataHora());
                System.out.println("Status: " + evento.getStatus());
                if (evento.getGrupo() != null) {
                    System.out.println("Grupo: " + evento.getGrupo().getNome());
                }
                if (evento.getTransporte() != null) {
                    System.out.println("Transporte: " + evento.getTransporte().getNomeMotorista());
                }
                if (evento.getSolicitante() != null) {
                    System.out.println("Solicitante: " + evento.getSolicitante().getNome());
                }
                System.out.println("------------------------------");
            }
        }

    }
//----------------------------------------------- TRANSPORTE --------------------------------------------------//
    public static void menuTransporte(Scanner input) throws Exception {
        int opcao;

        System.out.println("=== Transporte ===");
        System.out.println("[1] Cadastrar transporte");
        System.out.println("[2] Confirmar transporte");
        System.out.println("[3] Sair");

        System.out.println("Digite a opcao: ");
        opcao = input.nextInt();

        switch (opcao) {
            case 1:
                try {
                    cadastrarTransporte(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                confirmarTransporte(input);
                break;
            case 3:

                break;
            default:
                break;
        }
    }

    public static void cadastrarTransporte(Scanner input) throws Exception {
        // Solicita as informações do transporte ao usuário
        System.out.println("=== Cadastro de Transporte ===");
        System.out.print("Nome do motorista: ");
        String nomeMotorista = input.nextLine();

        System.out.print("Telefone: ");
        String telefone = input.nextLine();

        System.out.print("Modelo do veículo: ");
        String modeloVeiculo = input.nextLine();

        // Cria um objeto Transporte com as informações fornecidas
        Transporte transporte = new Transporte(nomeMotorista, telefone, modeloVeiculo);

        // Chama o método para inserir o transporte no banco de dados
        TransporteDB.inserirTransporte(transporte);

        System.out.println("Transporte cadastrado com sucesso!");
    }

    public static void confirmarTransporte(Scanner input) throws Exception {
        List<Transporte> transportes = TransporteDB.listarTransportes();
        System.out.println("=== Lista de todos os transportes ===");
        if (transportes.isEmpty()) {
            System.out.println("Nenhum transporte cadastrado");
        } 
        else {
            for (Transporte list : transportes) {
                System.out.println("Codigo: " + list.getId());
                System.out.println("Nome do Motorista: " + list.getNomeMotorista());
                System.out.println("------------------------");
            }
        }

        try {
            // Solicita o código do evento ao usuário
            System.out.print("Informe o código do evento: ");
            int eventoId = input.nextInt();
            input.nextLine(); // Limpar o buffer do scanner

            // Solicita o código do transporte ao usuário
            System.out.print("Informe o código do transporte para confirmar: ");
            int transporteId = input.nextInt();
            input.nextLine(); // Limpar o buffer do scanner

            // Verifica se o evento e o transporte existem
            Evento evento = EventoDB.procurarPorId(eventoId); // Método que busca evento pelo ID
            Transporte transporte = TransporteDB.procurarPorId(transporteId); // Método que busca transporte pelo ID

            if (evento == null) {
                System.out.println("Evento não encontrado!");
                return;
            }

            if (transporte == null) {
                System.out.println("Transporte não encontrado!");
                return;
            }

            // Confirma o transporte no evento, associando o transporte ao evento
            evento.setTransporte(transporte);
            evento.setStatus(Evento.Status.CONFIRMADO);

            // Atualiza o evento no banco de dados
            EventoDB.atualizar(evento);

            System.out.println("Transporte confirmado com sucesso para o evento!");
        } catch (Exception e) {
            System.out.println("Erro ao confirmar transporte: " + e.getMessage());
        }
    }

    //------------------------------------------------- TRANSPORTE ---------------------------------------------------//

    //----------------------------------------------- CANCELAR EVENTO --------------------------------------------------//

    public static void cancelarApresentacao(Scanner input) throws Exception {
        // Solicita o código do evento a ser cancelado
        System.out.print("Digite o código do evento que deseja cancelar: ");
        int eventoId = input.nextInt();

        // Busca o evento no banco de dados
        Evento evento = EventoDB.procurarPorId(eventoId);

        // Verifica se o evento existe
        if (evento == null) {
            System.out.println("Evento não encontrado!");
            return;
        }

        // Confirmação de cancelamento
        System.out.print("Tem certeza que deseja cancelar o evento '" + evento.getNome() + "'? (s/n): ");
        input.nextLine(); // Limpa o buffer do scanner
        String confirmacao = input.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            // Atualiza o status do evento para CANCELADO
            evento.setStatus(Evento.Status.CANCELADO);

            // Atualiza o evento no banco de dados
            EventoDB.atualizar(evento);

            // Confirmação de cancelamento
            System.out.println("Evento '" + evento.getNome() + "' foi cancelado com sucesso!");
        } else {
            System.out.println("Cancelamento do evento abortado.");
        }
    }


}
