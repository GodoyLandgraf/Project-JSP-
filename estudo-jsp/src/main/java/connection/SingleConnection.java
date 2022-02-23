package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String user = "postgres";
	private static String senha = "admin";
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}	
	
	public SingleConnection() { /* quando tiver uma instancia vai conectar*/
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, senha);
				connection.setAutoCommit(false); /*Para nao efetuar alterações no banco sem nosso comando*/

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
