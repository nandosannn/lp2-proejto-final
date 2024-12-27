package model.services;

import java.util.List;

import db.GrupoDB;
import model.entities.Grupo;

public class AdmGrupo {
    
    public static void imprimirGrupos() throws Exception{
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
}
