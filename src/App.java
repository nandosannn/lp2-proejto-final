import java.sql.Connection;
import java.util.Scanner;

import db.DB;
import model.entities.Adm;
import model.services.AdmGrupo;
import util.Util;

public class App {
    public static void main(String[] args) throws Exception {

        Connection conn = DB.getConnection();
        Scanner input = new Scanner(System.in);

        try {
            Util.menuMain(conn, input);
        } finally {
            DB.closeConnection();
            input.close();
        }
    }
}
