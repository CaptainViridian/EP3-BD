package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
	private Conexao() {
	}
	
	public static Connection getConnection() {
		String url = "jdbc:postgresql:hospital";
		Properties props = new Properties();
		props.setProperty("user", "postgres");
		props.setProperty("password", "abc");

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
