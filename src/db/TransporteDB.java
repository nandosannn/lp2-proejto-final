package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Transporte;

public class TransporteDB {
    public static List<Transporte> listarTransportes() throws Exception {
        List<Transporte> transportes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection(); // Obtém a conexão com o banco de dados

            // SQL para recuperar todos os transportes
            String sql = "SELECT codigo, nome_motorista, telefone, tipo_do_veiculo FROM transporte";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            // Itera sobre o ResultSet e adiciona os transportes à lista
            while (rs.next()) {
                Integer id = rs.getInt("codigo");
                String nomeMotorista = rs.getString("nome_motorista");
                String telefone = rs.getString("telefone");
                String modeloVeiculo = rs.getString("tipo_do_veiculo");

                Transporte transporte = new Transporte(nomeMotorista, telefone, modeloVeiculo);
                transportes.add(transporte);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao listar transportes: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
            DB.closeConnection();
        }

        return transportes;
    }

    public static Transporte procurarPorId(int id) throws Exception {
        Transporte transporte = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();

            // SQL para buscar transporte pelo ID
            String sql = "SELECT codigo, nome_motorista, telefone, tipo_do_veiculo FROM transporte WHERE codigo = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                String nomeMotorista = rs.getString("nome_motorista");
                String telefone = rs.getString("telefone");
                String modeloVeiculo = rs.getString("tipo_do_veiculo");

                transporte = new Transporte(id, nomeMotorista, telefone, modeloVeiculo);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao procurar transporte: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
            DB.closeConnection();
        }

        return transporte;
    }

    public static void inserirTransporte(Transporte transporte) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();

            // SQL para inserir novo transporte
            String sql = "INSERT INTO transporte (nome_motorista, telefone, tipo_do_veiculo) VALUES (?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, transporte.getNomeMotorista());
            pst.setString(2, transporte.getTelefone());
            pst.setString(3, transporte.getModeloVeículo());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao inserir transporte: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }

    public static void atualizarTransporte(Transporte transporte) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();

            // SQL para atualizar transporte
            String sql = "UPDATE transporte SET nome_motorista = ?, telefone = ?, tipo_do_veiculo = ? WHERE codigo = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, transporte.getNomeMotorista());
            pst.setString(2, transporte.getTelefone());
            pst.setString(3, transporte.getModeloVeículo());
            pst.setInt(4, transporte.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar transporte: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }

    public static void deletarTransporte(int id) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();

            // SQL para deletar transporte
            String sql = "DELETE FROM transporte WHERE codigo = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao deletar transporte: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}


