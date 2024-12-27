package model.entities;

public class Solicitante {
    private Integer id;
    private String nome;
    private String cargo;
    private String telefone;
    private String email;
    private String user;
    private String senha;

    public Solicitante() {

    }

    public Solicitante(String nome, String cargo, String telefone, String email, String user, String senha) {
        this.nome = nome;
        this.cargo = cargo;
        this.telefone = telefone;
        this.email = email;
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
        return "Solicitante [id=" + id + ", nome=" + nome + ", cargo=" + cargo + ", telefone=" + telefone + ", email="
                + email + ", user=" + user + ", senha=" + senha + "]";
    }

    

    

}
