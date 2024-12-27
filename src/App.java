import java.sql.Connection;
import java.util.Scanner;

import db.DB;
import model.entities.Adm;
import model.entities.Evento;
import model.entities.Grupo;
import model.entities.Solicitante;
import model.services.AdmGrupo;
import model.services.AdmSolicitante;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Connection conn = DB.getConnection();

        Scanner input = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("[1] Solicitante");
            System.out.println("[2] Grupo");
            System.out.println("[3] Adm");
            System.out.println("[4] Finalizar");

            System.out.print("Digite o número da operação: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    AdmSolicitante.imprimirSolicitantes();
                    break;
                case 2:
                    AdmGrupo.imprimirGrupos();
                    break;
                case 3:
                    System.out.print("Digite o login: ");
                    String login = input.nextLine();
                    System.out.print("Digite a senha: ");
                    String senha = input.nextLine();
                    
                    if (Adm.validarLogin(login, senha)) {
                        System.out.println("Login feito com sucesso");
                    }
                    else{
                        System.out.println("Acesso negado!");
                    }
                   
                    break;
                case 4:
                    System.out.println("A Escola de Música da UFRN agradece a confiança!");
                default:

                    break;
            }
        } while (opcao != 4);

        input.close();

		DB.closeConnection();
    }
}
