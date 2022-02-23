package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnection;


@WebFilter(urlPatterns = {"/principal/*"}) /* Intercepta todas as requisi��es que vierem do projeto ou mapeamento*/
public class FilterAutenticacao implements Filter {
       
private static  Connection connection;
	
	
    public FilterAutenticacao() {
        super();
    }

/* Encerra os processos quando o servidor � parado*/
    /* Mataria os processos de conex�o com o banco */
	public void destroy() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

/* Intercepta as requisicoes e as respostas no sistema */
	/* tudo que fizer no sistema vai fazer por aqui */
	/* valida��o de autentica��o */
	/* Dar commit e rolback de transa��es do banco */
	/*Validar e f azer redirecionamento de p�ginas */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
try {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpSession session = req.getSession();
	
	String usuarioLogado = (String) session.getAttribute("usuario");
	String urlParaAutenticar = req.getServletPath(); /* url que est� sendo acessada*/
	
	/*validar se est� logado sen�o redireciona para a tela de login */
	if(usuarioLogado ==null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
	
		RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
		request.setAttribute("msg", "Por favor realize o login!");
	     redireciona.forward(request, response);
		return;
	}
	else {
		chain.doFilter(request, response);
	} 
	
	connection.commit(); /* deu tudo certo entao commita as altera��es no banco*/
	}catch(Exception e){
		e.printStackTrace();
		RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
		request.setAttribute("msg", e.getMessage());
		redirecionar.forward(request, response);
		try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	}

/*inicia os processos ou recursos quando o servidor sobe o projeto */
	/* inicia a conex�o com o banco */
	public void init(FilterConfig fConfig) throws ServletException {
	connection = SingleConnection.getConnection();
	}

}
