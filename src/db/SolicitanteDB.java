package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Solicitante;

public class SolicitanteDB {

    // Listar todos os solicitantes
    public static List<Solicitante> listarSolicitantes() throws Exception {
        List<Solicitante> solicitantes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection(); // Obtém a conexão com o banco de dados

            String sql = "SELECT codigo, nome, cargo, telefone, email, usuario, senha FROM solicitante";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String cargo = rs.getString("cargo");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String user = rs.getString("usuario");
                String senha = rs.getString("senha");

                Solicitante solicitante = new Solicitante(id, nome, cargo, telefone, email, user, senha);
                solicitantes.add(solicitante);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao listar solicitantes: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
            DB.closeConnection();
        }

        return solicitantes;
    }

    // Procurar solicitante por ID
    public static Solicitante procurarSolicitantePorId(int id) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            String sql = "SELECT codigo, nome, cargo, telefone, email, usuario, senha FROM solicitante WHERE codigo = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String cargo = rs.getString("cargo");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String user = rs.getString("usuario");
                String senha = rs.getString("senha");

                return new Solicitante(id, nome, cargo, telefone, email, user, senha);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao procurar solicitante: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
            DB.closeConnection();
        }

        return null; // Retorna null se o solicitante não for encontrado
    }

    // Inserir solicitante
    public static void inserirSolicitante(Solicitante solicitante) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            String sql = "INSERT INTO solicitante (nome, cargo, telefone, email, usuario, senha) VALUES (?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, solicitante.getNome());
            pst.setString(2, solicitante.getCargo());
            pst.setString(3, solicitante.getTelefone());
            pst.setString(4, solicitante.getEmail());
            pst.setString(5, solicitante.getUser());
            pst.setString(6, solicitante.getSenha());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao inserir solicitante: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }

    // Atualizar solicitante
    public static void atualizarSolicitante(Solicitante solicitante) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            String sql = "UPDATE solicitante SET nome = ?, cargo = ?, telefone = ?, email = ?, usuario = ?, senha = ? WHERE codigo = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, solicitante.getNome());
            pst.setString(2, solicitante.getCargo());
            pst.setString(3, solicitante.getTelefone());
            pst.setString(4, solicitante.getEmail());
            pst.setString(5, solicitante.getUser());
            pst.setString(6, solicitante.getSenha());
            pst.setInt(7, solicitante.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar solicitante: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }

    // Deletar solicitante
    public static void deletarSolicitante(int id) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            String sql = "DELETE FROM solicitante WHERE codigo = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao deletar solicitante: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}
