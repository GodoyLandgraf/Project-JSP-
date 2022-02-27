package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.SingleConnection;
import model.ModelLogin;

public class DAOUsuarioRepository {

	
	private Connection connection;
	
	
	
	public DAOUsuarioRepository() {
		connection = SingleConnection.getConnection();
	}
	
	
	
	/* Metodo para gravar usuario no banco de dados */
	public void gravarUsuario(ModelLogin modelLogin) throws Exception{
		
	
		String sql = "INSERT INTO model_login (login, senha, nome, email) VALUES (?, ?, ?, ?);";
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setString(1, modelLogin.getLogin());
		preparedSql.setString(2, modelLogin.getSenha());
		preparedSql.setString(3, modelLogin.getNome());
		preparedSql.setString(4, modelLogin.getEmail());
		preparedSql.execute();
		connection.commit();
	
	}
	
}
