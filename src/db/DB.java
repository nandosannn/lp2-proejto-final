package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Statement;
import java.sql.ResultSet;

public class DB {
    
    private static Connection conn = null;

    public static Connection getConnection() throws Exception {
		try {
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			return DriverManager.getConnection(url, props); // Retorna sempre uma nova conexão
		} catch (SQLException e) {
			throw new Exception("Erro ao obter conexão: " + e.getMessage(), e);
		}
	}

    public static void closeConnection() throws Exception {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
	}

    private static Properties loadProperties() throws Exception {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void closeStatement(Statement st) throws Exception{
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) throws Exception {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
	}
	
}
