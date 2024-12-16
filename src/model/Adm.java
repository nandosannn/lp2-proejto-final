package model;

public class Adm {
    private String user = "adm";
    private String senha = "123";

    public Adm(){

    }

    public boolean validarLogin(String userInput, String senhaInput) {
        return this.user.equals(userInput) && this.senha.equals(senhaInput);
    }
}
