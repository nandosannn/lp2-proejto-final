package model;

public class Transporte {
    private Integer id;
    private String nomeMotorista;
    private String telefone;
    private String modeloVeículo;
   
    public Transporte(String nomeMotorista, String telefone, String modeloVeículo) {
        this.nomeMotorista = nomeMotorista;
        this.telefone = telefone;
        this.modeloVeículo = modeloVeículo;
    }

    public Integer getId() {
        return id;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getModeloVeículo() {
        return modeloVeículo;
    }

    public void setModeloVeículo(String modeloVeículo) {
        this.modeloVeículo = modeloVeículo;
    }

    @Override
    public String toString() {
        return "Transporte [id=" + id + ", nomeMotorista=" + nomeMotorista + ", telefone=" + telefone
                + ", modeloVeículo=" + modeloVeículo + "]";
    }

    
    
}
