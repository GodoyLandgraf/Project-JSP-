package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;
import model.ModelLogin;

public class DAOUsuarioRepository {

	
	private Connection connection;
	
	
	
	public DAOUsuarioRepository() {
		connection = SingleConnection.getConnection();
	}
	
	
	
	/* Metodo para gravar usuario no banco de dados */
	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception{
		
	
		String sql = "INSERT INTO model_login (login, senha, nome, email) VALUES (?, ?, ?, ?);";
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setString(1, objeto.getLogin());
		preparedSql.setString(2, objeto.getSenha());
		preparedSql.setString(3, objeto.getNome());
		preparedSql.setString(4, objeto.getEmail());
		preparedSql.execute();	
		connection.commit();
		
		return this.consultaUsuario(objeto.getLogin());
	
	}
	
	public ModelLogin consultaUsuario(String login) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from model_login where login = ' "+login+"';";
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultado = statement.executeQuery();
				while(resultado.next()) { /* se tem resultado*/
					modelLogin.setId(resultado.getLong("id"));
					modelLogin.setEmail(resultado.getString("email"));
					modelLogin.setLogin(resultado.getString("login"));
					modelLogin.setSenha(resultado.getString("senha"));
					modelLogin.setNome(resultado.getString("nome"));
				}
				return modelLogin;
	}
	
}
