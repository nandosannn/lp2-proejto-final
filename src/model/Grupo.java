package model;

public class Grupo {
    private Integer id;
    private String nome;
    private String nomeCoordenador;
    private String telefoneCoordenador;
    private Integer quantidadeMusicos;
   

    public Grupo(){

    }
    
    public Grupo(String nome, String nomeCoordenador, String telefoneCoordenador,
            Integer quantidadeMusicos) {
        this.nome = nome;
        this.nomeCoordenador = nomeCoordenador;
        this.telefoneCoordenador = telefoneCoordenador;
        this.quantidadeMusicos = quantidadeMusicos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Grupo [id=" + id + ", nome=" + nome + ", nomeCoordenador=" + nomeCoordenador + ", telefoneCoordenador="
                + telefoneCoordenador + ", quantidadeMusicos=" + quantidadeMusicos + "]";
    }
}
