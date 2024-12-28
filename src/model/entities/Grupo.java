package model.entities;

import java.util.List;

import db.GrupoDB;

public class Grupo {
    private Integer id;
    private String nome;
    private String nomeCoordenador;
    private String telefoneCoordenador;
    private Integer quantidadeMusicos;
    private String user;
    private String senha;
   

    public Grupo(){

    }

    public Grupo(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Grupo(Integer id, String nome, String nomeCoordenador, String telefoneCoordenador, Integer quantidadeMusicos,
            String user, String senha) {
        this.id = id;
        this.nome = nome;
        this.nomeCoordenador = nomeCoordenador;
        this.telefoneCoordenador = telefoneCoordenador;
        this.quantidadeMusicos = quantidadeMusicos;
        this.user = user;
        this.senha = senha;
    }

    public Grupo(String nome, String nomeCoordenador, String telefoneCoordenador, Integer quantidadeMusicos,
            String user, String senha) {
        this.nome = nome;
        this.nomeCoordenador = nomeCoordenador;
        this.telefoneCoordenador = telefoneCoordenador;
        this.quantidadeMusicos = quantidadeMusicos;
        this.user = user;
        this.senha = senha;
    }


    public Integer getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getNomeCoordenador() {
        return nomeCoordenador;
    }


    public void setNomeCoordenador(String nomeCoordenador) {
        this.nomeCoordenador = nomeCoordenador;
    }


    public String getTelefoneCoordenador() {
        return telefoneCoordenador;
    }


    public void setTelefoneCoordenador(String telefoneCoordenador) {
        this.telefoneCoordenador = telefoneCoordenador;
    }


    public Integer getQuantidadeMusicos() {
        return quantidadeMusicos;
    }


    public void setQuantidadeMusicos(Integer quantidadeMusicos) {
        this.quantidadeMusicos = quantidadeMusicos;
    }


    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }


    @Override
    public String toString() {
        return "Grupo [id=" + id + ", nome=" + nome + ", nomeCoordenador=" + nomeCoordenador + ", telefoneCoordenador="
                + telefoneCoordenador + ", quantidadeMusicos=" + quantidadeMusicos + ", user=" + user + ", senha="
                + senha + "]";
    }
}


