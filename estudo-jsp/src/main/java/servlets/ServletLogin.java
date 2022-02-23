package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import model.ModelLogin;

/* Controller s�o as servlets - servletsLoginController*/
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"}) /*Mapeamento de URL que vem da tela */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
  
    public ServletLogin() {
        super();
    }

	
    /* recebe os dados pela url em parametros */
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	String acao = request.getParameter("acao");
    	if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
    		request.getSession().invalidate(); //invalida a sess�o
    		RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
    		redirecionar.forward(request, response);
    	}else {
       	doPost(request, response);

    	}
    	
	
	
    }

	/*recebe os dados enviados por um formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String login = request.getParameter("login");
	String senha = request.getParameter("senha");
	String url = request.getParameter("url");
	
	try {
	
	//pega os parametros do formulario e passa para o objeto ModelLogin com a autentica��o
	if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
		ModelLogin modelLogin = new ModelLogin();
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		
		//simulando login
		if(daoLoginRepository.validarAutenticacao(modelLogin)) {
						
			request.getSession().setAttribute("usuario", modelLogin.getLogin());  //atributo de sess�o (mantem o usuario logado no sistema)
			
			if(url == null || url.equals("null")) {
				url = "principal/principal.jsp";
			}
			
			RequestDispatcher redirecionar = request.getRequestDispatcher(url);
			redirecionar.forward(request, response);
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente!");
			redirecionar.forward(request, response);
		}
		
	}else { /*redireciona pro index.html se n�o pa ssar pera autentifica��o com a mensagem!! */
		RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
		request.setAttribute("msg", "Informe o login e senha corretamente!");
		redirecionar.forward(request, response);
	}
	
	}catch (Exception e) {
		e.printStackTrace();
		RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
		request.setAttribute("msg", e.getMessage());
		redirecionar.forward(request, response);
	}
		
	}

}
