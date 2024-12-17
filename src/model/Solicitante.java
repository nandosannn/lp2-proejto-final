package model;

public class Solicitante {
    private Integer id;
    private String nome;
    private String cargo;
    private String telefone;
    private String email;
   
   public Solicitante(){

   }
   
    public Solicitante(String nome, String cargo, String telefone, String email) {
        this.nome = nome;
        this.cargo = cargo;
        this.telefone = telefone;
        this.email = email;
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


    public String getCargo() {
        return cargo;
    }


    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    public String getTelefone() {
        return telefone;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Solicitante [id=" + id + ", nome=" + nome + ", cargo=" + cargo + ", telefone=" + telefone + ", email="
                + email + "]";
    }
    
}
