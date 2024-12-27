package model.services;

import java.util.List;

import db.SolicitanteDB;
import model.entities.Solicitante;

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

}
