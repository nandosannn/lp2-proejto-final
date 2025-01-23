package model.entities;

import java.util.List;
import java.util.Scanner;

import db.EventoDB;

public class Adm {
    private static String user = "adm";
    private static String senha = "123";

    public Adm() {
    }

    public static boolean validarLogin(String userInput, String senhaInput) {
        return user.equals(userInput) && senha.equals(senhaInput);
    }

    public static void listaTodasApresentacoes() throws Exception{
        List<Evento> eventos = EventoDB.listarEventos();

        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado!");
            return;
        }
        System.out.println("\n=== Todos os eventos ===\n");
        for(Evento list : eventos){
            // Imprime os atributos do evento
            System.out.println("Código do Evento: " + list.getId());
            System.out.println("Nome do Evento: " + list.getNome());
            System.out.println("Local do Evento: " + list.getLocal());
            System.out.println("Data e Hora do Evento: " + list.getDataHora());
            System.out.println("Status do Evento: " + list.getStatus());

            String grupo = "Aguardando disponibilidade de grupo";
            if (grupo.equalsIgnoreCase(list.getGrupo().getNome())) {
                System.out.println(grupo);
            }
            else{
                System.out.println("Grupo: " + list.getGrupo().getNome());
            }

            String transporte = "Aguardando confirmação de transporte";
            if (transporte.equalsIgnoreCase(list.getTransporte().getNomeMotorista())) {
                System.out.println(transporte);
            }
            else{
                System.out.println("Nome do motorista: " + list.getTransporte().getNomeMotorista());
            }
            System.out.println("======================================\n");
        }
    }

    public static void confirmarApresentacoes(Scanner input) throws Exception{
        System.out.print("Digite o código do evento que deseja confirmar: ");
        int eventoId = input.nextInt();
        input.nextLine();

        Evento evento = EventoDB.procurarPorId(eventoId);

        if (evento == null) {
            System.out.println("Evento não encontrado!");
        }

        evento.setStatus(Evento.Status.CONFIRMADO);

        EventoDB.atualizar(evento);

        System.out.println("Evento '" + evento.getNome() + "' foi atualizado com sucesso!");

    }

    /*=== Relatorios ===*/
    public static void imprimirList(List<Evento> eventos){
        for(Evento list : eventos){
            // Imprime os atributos do evento
            System.out.println("Código do Evento: " + list.getId());
            System.out.println("Nome do Evento: " + list.getNome());
            System.out.println("Local do Evento: " + list.getLocal());
            System.out.println("Data e Hora do Evento: " + list.getDataHora());
            System.out.println("Status do Evento: " + list.getStatus());
            System.out.println("Grupo: " + list.getGrupo().getNome());

            // Imprime os dados do transporte
            String disponibilidade = "Aguardando confirmação de transporte";
            if (disponibilidade.equalsIgnoreCase(list.getTransporte().getNomeMotorista())) {
                System.out.println("Aguardando confirmação de Transporte");
            }
            else{
                System.out.println("Nome do Motorista: " + list.getTransporte().getNomeMotorista());
            }

            // Imprime os dados do solicitante
            System.out.println("Solicitante: " + list.getSolicitante().getNome());

            System.out.println("====================================="); // Linha de separação entre eventos
        }
    }
    public static void eventoPorMes(Scanner input) throws Exception{

        System.out.print("Digite o mês que deseja fazer a pesquisa: ");
        int mes = input.nextInt();
        input.nextLine();

        List<Evento> eventos = EventoDB.listarEventosPorMes(mes);

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado!");
        }
        else{
            imprimirList(eventos);
        }
        
    }

    public static void eventoGrupoNaoConfirmado() throws Exception{
        List<Evento> eventos = EventoDB.listarEventosComGrupoPadrao();

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado!");
        }
        else{
            imprimirList(eventos);
        }
    }

    public static void eventoGrupoConfirmado() throws Exception{
        List<Evento> eventos = EventoDB.listarEventosComGrupoDiferenteDoPadrao();

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado!");
        }
        else{
            imprimirList(eventos);
        }
    }

    public static void eventoAbertos() throws Exception{
        List<Evento> eventos = EventoDB.listarEventosAbertos();

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado!");
        }
        else{
            imprimirList(eventos);
        }
    }

    public static void eventoPorGrupo(Scanner input) throws Exception{
        System.out.print("Digite o codigo do grupo que deseja fazer a pesquisa: ");
        int codigo = input.nextInt();
        input.nextLine();

        List<Evento> eventos = EventoDB.listarEventosPorGrupo(codigo);

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado!");
        }
        else{
            imprimirList(eventos);
        }
    }


}
