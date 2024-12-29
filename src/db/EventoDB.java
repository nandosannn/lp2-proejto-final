package db;

import java.io.ObjectInputFilter.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.entities.Evento;
import model.entities.Grupo;
import model.entities.Solicitante;
import model.entities.Transporte;

public class EventoDB {
    public static List<Evento> listarEventos() throws Exception {
        List<Evento> eventos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection(); // Obtém a conexão com o banco de dados

            // SQL para listar os eventos com os dados relacionados
            String sql = """
                    SELECT e.codigo, e.nome, e.local, e.horario, e.status,
                           g.codigo AS grupo_id, g.nome AS grupo_nome,
                           t.codigo AS transporte_id, t.nome_motorista, t.telefone AS transporte_telefone, t.tipo_do_veiculo,
                           s.codigo AS solicitante_id, s.nome AS solicitante_nome, s.cargo AS solicitante_cargo,
                           s.telefone AS solicitante_telefone, s.email AS solicitante_email
                    FROM evento e
                    LEFT JOIN grupo g ON e.grupo = g.codigo
                    LEFT JOIN transporte t ON e.transporte = t.codigo
                    LEFT JOIN solicitante s ON e.solicitante = s.codigo
                    """;

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            // Itera sobre o ResultSet e adiciona os eventos à lista
            while (rs.next()) {
                // Dados do evento
                Integer id = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String local = rs.getString("local");
                LocalDateTime dataHora = rs.getTimestamp("horario").toLocalDateTime();

                // Conversão do status de String para Evento.Status
                Evento.Status status = null;
                String statusStr = rs.getString("status");
                if (statusStr != null && !statusStr.isEmpty()) {
                    try {
                        status = Evento.Status.valueOf(statusStr.trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new Exception("Status inválido encontrado no banco: " + statusStr);
                    }
                }

                // Dados do grupo
                Grupo grupo = null;
                if (rs.getInt("grupo_id") != 0) {
                    grupo = new Grupo(rs.getInt("grupo_id"), rs.getString("grupo_nome"));
                }

                // Dados do transporte
                Transporte transporte = null;
                if (rs.getInt("transporte_id") != 0) {
                    transporte = new Transporte(
                            rs.getInt("transporte_id"),
                            rs.getString("nome_motorista"),
                            rs.getString("transporte_telefone"),
                            rs.getString("tipo_do_veiculo"));
                }

                // Dados do solicitante
                Solicitante solicitante = null;
                if (rs.getInt("solicitante_id") != 0) {
                    solicitante = new Solicitante(
                            rs.getInt("solicitante_id"),
                            rs.getString("solicitante_nome"),
                            rs.getString("solicitante_cargo"),
                            rs.getString("solicitante_telefone"),
                            rs.getString("solicitante_email"),
                            null, null);
                }

                // Criação do evento
                Evento evento = new Evento(id, nome, local, dataHora, grupo, status, transporte, solicitante);
                eventos.add(evento);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao listar eventos: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
            DB.closeConnection();
        }

        return eventos;
    }

    public static List<Evento> listarEventosComGrupoPadrao() throws Exception {
        List<Evento> eventos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection(); // Obtém a conexão com o banco de dados

            // SQL para listar os eventos com grupo padrão (código 1)
            String sql = """
                    SELECT e.codigo, e.nome, e.local, e.horario, e.status,
                           g.codigo AS grupo_id, g.nome AS grupo_nome,
                           t.codigo AS transporte_id, t.nome_motorista, t.telefone AS transporte_telefone, t.tipo_do_veiculo,
                           s.codigo AS solicitante_id, s.nome AS solicitante_nome, s.cargo AS solicitante_cargo,
                           s.telefone AS solicitante_telefone, s.email AS solicitante_email
                    FROM evento e
                    LEFT JOIN grupo g ON e.grupo = g.codigo
                    LEFT JOIN transporte t ON e.transporte = t.codigo
                    LEFT JOIN solicitante s ON e.solicitante = s.codigo
                    WHERE g.codigo = 1
                    """;

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            // Itera sobre o ResultSet e adiciona os eventos à lista
            while (rs.next()) {
                // Dados do evento
                Integer id = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String local = rs.getString("local");
                LocalDateTime dataHora = rs.getTimestamp("horario").toLocalDateTime();

                // Conversão do status de String para Evento.Status
                Evento.Status status = null;
                String statusStr = rs.getString("status");
                if (statusStr != null && !statusStr.isEmpty()) {
                    try {
                        status = Evento.Status.valueOf(statusStr.trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new Exception("Status inválido encontrado no banco: " + statusStr);
                    }
                }

                // Dados do grupo
                Grupo grupo = null;
                if (rs.getInt("grupo_id") != 0) {
                    grupo = new Grupo(rs.getInt("grupo_id"), rs.getString("grupo_nome"));
                }

                // Dados do transporte
                Transporte transporte = null;
                if (rs.getInt("transporte_id") != 0) {
                    transporte = new Transporte(
                            rs.getInt("transporte_id"),
                            rs.getString("nome_motorista"),
                            rs.getString("transporte_telefone"),
                            rs.getString("tipo_do_veiculo"));
                }

                // Dados do solicitante
                Solicitante solicitante = null;
                if (rs.getInt("solicitante_id") != 0) {
                    solicitante = new Solicitante(
                            rs.getInt("solicitante_id"),
                            rs.getString("solicitante_nome"),
                            rs.getString("solicitante_cargo"),
                            rs.getString("solicitante_telefone"),
                            rs.getString("solicitante_email"),
                            null, null);
                }

                // Criação do evento
                Evento evento = new Evento(id, nome, local, dataHora, grupo, status, transporte, solicitante);
                eventos.add(evento);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao listar eventos com grupo padrão: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
            DB.closeConnection();
        }

        return eventos;
    }

    public static List<Evento> listarEventosComGrupoDiferenteDoPadrao() throws Exception {
        List<Evento> eventos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection(); // Obtém a conexão com o banco de dados

            // SQL para listar os eventos com grupo diferente do padrão (código diferente de
            // 1)
            String sql = """
                    SELECT e.codigo, e.nome, e.local, e.horario, e.status,
                           g.codigo AS grupo_id, g.nome AS grupo_nome,
                           t.codigo AS transporte_id, t.nome_motorista, t.telefone AS transporte_telefone, t.tipo_do_veiculo,
                           s.codigo AS solicitante_id, s.nome AS solicitante_nome, s.cargo AS solicitante_cargo,
                           s.telefone AS solicitante_telefone, s.email AS solicitante_email
                    FROM evento e
                    LEFT JOIN grupo g ON e.grupo = g.codigo
                    LEFT JOIN transporte t ON e.transporte = t.codigo
                    LEFT JOIN solicitante s ON e.solicitante = s.codigo
                    WHERE g.codigo != 1
                    """;

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            // Itera sobre o ResultSet e adiciona os eventos à lista
            while (rs.next()) {
                // Dados do evento
                Integer id = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String local = rs.getString("local");
                LocalDateTime dataHora = rs.getTimestamp("horario").toLocalDateTime();

                // Conversão do status de String para Evento.Status
                Evento.Status status = null;
                String statusStr = rs.getString("status");
                if (statusStr != null && !statusStr.isEmpty()) {
                    try {
                        status = Evento.Status.valueOf(statusStr.trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new Exception("Status inválido encontrado no banco: " + statusStr);
                    }
                }

                // Dados do grupo
                Grupo grupo = null;
                if (rs.getInt("grupo_id") != 0) {
                    grupo = new Grupo(rs.getInt("grupo_id"), rs.getString("grupo_nome"));
                }

                // Dados do transporte
                Transporte transporte = null;
                if (rs.getInt("transporte_id") != 0) {
                    transporte = new Transporte(
                            rs.getInt("transporte_id"),
                            rs.getString("nome_motorista"),
                            rs.getString("transporte_telefone"),
                            rs.getString("tipo_do_veiculo"));
                }

                // Dados do solicitante
                Solicitante solicitante = null;
                if (rs.getInt("solicitante_id") != 0) {
                    solicitante = new Solicitante(
                            rs.getInt("solicitante_id"),
                            rs.getString("solicitante_nome"),
                            rs.getString("solicitante_cargo"),
                            rs.getString("solicitante_telefone"),
                            rs.getString("solicitante_email"),
                            null, null);
                }

                // Criação do evento
                Evento evento = new Evento(id, nome, local, dataHora, grupo, status, transporte, solicitante);
                eventos.add(evento);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao listar eventos com grupo diferente do padrão: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
            DB.closeConnection();
        }

        return eventos;
    }

    public static Evento procurarPorId(Integer id) throws Exception {
        Evento evento = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            String sql = """
                    SELECT e.codigo, e.nome, e.local, e.horario, e.status,
                           g.codigo AS grupo_id, g.nome AS grupo_nome,
                           t.codigo AS transporte_id, t.nome_motorista, t.telefone AS transporte_telefone, t.tipo_do_veiculo,
                           s.codigo AS solicitante_id, s.nome AS solicitante_nome, s.cargo AS solicitante_cargo,
                           s.telefone AS solicitante_telefone, s.email AS solicitante_email
                    FROM evento e
                    LEFT JOIN grupo g ON e.grupo = g.codigo
                    LEFT JOIN transporte t ON e.transporte = t.codigo
                    LEFT JOIN solicitante s ON e.solicitante = s.codigo
                    WHERE e.codigo = ?
                    """;
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Dados do evento
                String nome = rs.getString("nome");
                String local = rs.getString("local");
                LocalDateTime dataHora = rs.getTimestamp("horario").toLocalDateTime();
                Evento.Status status = Evento.Status.valueOf(rs.getString("status"));

                // Dados do grupo
                Grupo grupo = null;
                if (rs.getInt("grupo_id") != 0) {
                    grupo = new Grupo(rs.getInt("grupo_id"), rs.getString("grupo_nome"));
                }

                // Dados do transporte
                Transporte transporte = null;
                if (rs.getInt("transporte_id") != 0) {
                    transporte = new Transporte(
                            rs.getInt("transporte_id"),
                            rs.getString("nome_motorista"),
                            rs.getString("transporte_telefone"),
                            rs.getString("tipo_do_veiculo"));
                }

                // Dados do solicitante
                Solicitante solicitante = null;
                if (rs.getInt("solicitante_id") != 0) {
                    solicitante = new Solicitante(
                            rs.getInt("solicitante_id"),
                            rs.getString("solicitante_nome"),
                            rs.getString("solicitante_cargo"),
                            rs.getString("solicitante_telefone"),
                            rs.getString("solicitante_email"),
                            null, null);
                }

                // Criação do evento
                evento = new Evento(id, nome, local, dataHora, grupo, status, transporte, solicitante);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao procurar evento: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
            DB.closeConnection();
        }

        return evento;
    }

    public static void inserir(Evento evento) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false); // Desabilita o commit automático

            String sql = """
                    INSERT INTO evento (nome, local, horario, grupo, status, transporte, solicitante)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                    """;
            pst = conn.prepareStatement(sql);
            pst.setString(1, evento.getNome());
            pst.setString(2, evento.getLocal());
            pst.setObject(3, evento.getDataHora());
            pst.setInt(4, 1);
            pst.setString(5, Evento.Status.PENDENTE.name());
            pst.setInt(6, 1);
            pst.setInt(7, evento.getSolicitante().getId());

            pst.executeUpdate();
            conn.commit(); // Confirma a transação
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Reverte a transação em caso de erro
            }
            throw new Exception("Erro ao inserir evento: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }

    public static void atualizar(Evento evento) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            String sql = """
                    UPDATE evento
                    SET nome = ?, local = ?, horario = ?, grupo = ?, status = ?, transporte = ?, solicitante = ?
                    WHERE codigo = ?
                    """;
            pst = conn.prepareStatement(sql);
            pst.setString(1, evento.getNome());
            pst.setString(2, evento.getLocal());
            pst.setObject(3, evento.getDataHora());
            pst.setInt(4, evento.getGrupo() != null ? evento.getGrupo().getId() : null);
            pst.setString(5, evento.getStatus().name());
            pst.setInt(6, evento.getTransporte() != null ? evento.getTransporte().getId() : null);
            pst.setInt(7, evento.getSolicitante() != null ? evento.getSolicitante().getId() : null);
            pst.setInt(8, evento.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar evento: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }

    public static void deletar(Integer id) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            String sql = "DELETE FROM evento WHERE codigo = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao deletar evento: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}
