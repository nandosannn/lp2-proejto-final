package model.entities;

public class Adm {
    private static String user = "adm";
    private static String senha = "123";

    public Adm() {
    }

    public static boolean validarLogin(String userInput, String senhaInput) {
        return user.equals(userInput) && senha.equals(senhaInput);
    }
}
