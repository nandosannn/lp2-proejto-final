package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.sql.Statement;
import java.sql.ResultSet;

import db.DB;
import model.entities.Grupo;

public class GrupoDB {
    
    public static List<Grupo> listarGrupos() throws Exception {
        List<Grupo> grupos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();  // Obtém a conexão com o banco de dados
            
            // SQL para recuperar todos os grupos
            String sql = "SELECT codigo, nome, coordenador, telefone_coordenador, quantidade_de_musicos, usuario, senha FROM grupo";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            // Itera sobre o ResultSet e adiciona os nomes dos grupos à lista
            while (rs.next()) {
                Integer id = rs.getInt("codigo");
                String nomeGrupo = rs.getString("nome");
                String nomeCoordenador = rs.getString("coordenador");
                String telefoneCoordenador = rs.getString("telefone_coordenador");
                Integer quantidadeMusicos = rs.getInt("quantidade_de_musicos");
                String user = rs.getString("usuario");
                String senha = rs.getString("senha");

                Grupo grupo = new Grupo(id, nomeGrupo, nomeCoordenador, telefoneCoordenador, quantidadeMusicos, user, senha);

                grupos.add(grupo);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao listar grupos: " + e.getMessage());
        } finally {
            // Fecha recursos para evitar vazamento de memória
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
            DB.closeConnection();
        }
        
        return grupos;  // Retorna a lista com os nomes dos grupos
    }

    // Método para procurar um grupo por ID
    public static Grupo procurarPorId(Integer id) throws Exception {
        Grupo grupo = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            String sql = "SELECT codigo, nome, coordenador, telefone_coordenador, quantidade_de_musicos, usuario, senha FROM grupo WHERE codigo = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);  // Setando o ID para a consulta
            rs = pst.executeQuery();

            if (rs.next()) {
                String nomeGrupo = rs.getString("nome");
                String nomeCoordenador = rs.getString("coordenador");
                String telefoneCoordenador = rs.getString("telefone_coordenador");
                Integer quantidadeMusicos = rs.getInt("quantidade_de_musicos");
                String user = rs.getString("usuario");
                String senha = rs.getString("senha");

                grupo = new Grupo(id, nomeGrupo, nomeCoordenador, telefoneCoordenador, quantidadeMusicos, user, senha);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao procurar grupo por ID: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
            DB.closeConnection();
        }

        return grupo;
    }

    // Método para inserir um novo grupo
    public static void inserirGrupo(Grupo grupo) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            String sql = "INSERT INTO grupo (nome, coordenador, telefone_coordenador, quantidade_de_musicos, usuario, senha) VALUES (?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, grupo.getNome());
            pst.setString(2, grupo.getNomeCoordenador());
            pst.setString(3, grupo.getTelefoneCoordenador());
            pst.setInt(4, grupo.getQuantidadeMusicos());
            pst.setString(5, grupo.getUser());
            pst.setString(6, grupo.getSenha());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao inserir grupo: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }

    // Método para atualizar um grupo existente
    public static void atualizarGrupo(Grupo grupo) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            String sql = "UPDATE grupo SET nome = ?, coordenador = ?, telefone_coordenador = ?, quantidade_de_musicos = ?, usuario = ?, senha = ? WHERE codigo = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, grupo.getNome());
            pst.setString(2, grupo.getNomeCoordenador());
            pst.setString(3, grupo.getTelefoneCoordenador());
            pst.setInt(4, grupo.getQuantidadeMusicos());
            pst.setString(5, grupo.getUser());
            pst.setString(6, grupo.getSenha());
            pst.setInt(7, grupo.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar grupo: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }

    // Método para deletar um grupo por ID
    public static void deletarGrupo(Integer id) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            String sql = "DELETE FROM grupo WHERE codigo = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);  // Setando o ID para a consulta

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao deletar grupo: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}
