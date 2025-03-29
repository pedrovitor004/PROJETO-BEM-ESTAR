package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDbc {
	public static Connection conexao() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/db_senhas", "root", "@Srmack12345");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}
		
	}
}
