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
		
	    if (objeto.isNew()) {
		String sql = "INSERT INTO model_login (login, senha, nome, email) VALUES (?, ?, ?, ?);";
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setString(1, objeto.getLogin());
		preparedSql.setString(2, objeto.getSenha());
		preparedSql.setString(3, objeto.getNome());
		preparedSql.setString(4, objeto.getEmail());
		preparedSql.execute();	
		connection.commit();
		}else {
			String sql = "UPDATE model_login set login=?, senha=?, nome=?, email=? where id ="+objeto.getId()+";";
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			
			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			preparedSql.executeUpdate();	
			connection.commit();
		}
	    
		return this.consultaUsuario(objeto.getLogin());
	
	}
	
	public ModelLogin consultaUsuario(String login) throws Exception  {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where login = '"+login+"' ;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resutlado =  statement.executeQuery();
		
		while (resutlado.next()) /*Se tem resultado*/ {
			
			modelLogin.setId(resutlado.getLong("id"));
			modelLogin.setEmail(resutlado.getString("email"));
			modelLogin.setLogin(resutlado.getString("login"));
			modelLogin.setSenha(resutlado.getString("senha"));
			modelLogin.setNome(resutlado.getString("nome"));
		}
		
		
		return modelLogin;
		
	}
	
	public boolean validarLogin (String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where login = '"+login+"' ;";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		resultado.next(); /*para ele entrar nos resultados do sql*/
		return resultado.getBoolean("existe");

	}

}
