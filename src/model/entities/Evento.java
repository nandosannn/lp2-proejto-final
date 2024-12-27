package model.entities;

import java.time.LocalDateTime;

public class Evento {

    public enum Status {
        PENDENTE,
        CONFIRMADO,
        CANCELADO;
    }
    
    private Integer id;
    private String nome;
    private String local;
    private LocalDateTime dataHora;
    private Grupo grupo;
    private Status status;
    private Transporte transporte;
    private Solicitante solicitante;
    
    public Evento(){
        
    }
    
    public Evento(String nome, String local, LocalDateTime dataHora, Grupo grupo, Status status, Transporte transporte,
            Solicitante solicitante) {
        this.nome = nome;
        this.local = local;
        this.dataHora = dataHora;
        this.grupo = grupo;
        this.status = status;
        this.transporte = transporte;
        this.solicitante = solicitante;
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


    public String getLocal() {
        return local;
    }


    public void setLocal(String local) {
        this.local = local;
    }


    public LocalDateTime getDataHora() {
        return dataHora;
    }


    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }


    public Grupo getGrupo() {
        return grupo;
    }


    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }


    public Transporte getTransporte() {
        return transporte;
    }


    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }


    public Solicitante getSolicitante() {
        return solicitante;
    }


    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }


    @Override
    public String toString() {
        return "Evento [id=" + id + ", nome=" + nome + ", local=" + local + ", dataHora=" + dataHora + ", grupo="
                + grupo + ", status=" + status + ", transporte=" + transporte + ", solicitante=" + solicitante + "]";
    }
}
