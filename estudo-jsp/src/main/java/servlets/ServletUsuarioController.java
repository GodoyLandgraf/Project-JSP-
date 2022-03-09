package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOUsuarioRepository;
import model.ModelLogin;


@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
   
	public ServletUsuarioController() {

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
		
			String msg = "Operação realizada com sucesso!";
			
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ModelLogin modelLogin = new ModelLogin();
		modelLogin.setId(id !=null && !id.isEmpty() ? Long.parseLong(id): null);
		modelLogin.setNome(nome);
		modelLogin.setEmail(email);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		
		if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
			msg = "Já existe usuário com o mesmo login, informe outro login!";
		}else {
			if(modelLogin.isNew()) {
				msg = "Gravado com Sucesso!";
			}else {
				msg= "Atualizado com Sucesso!";
			}
			modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);

		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("modelLogin", modelLogin);   /* mantem os dados na tela - edição de conteudo também */
		/* na pagina usuario.jsp utilizar ${modolLogin.nome} para recuperar o nome que estava na tela */
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response); /*redireciona para a mesma tela dpois de atualizar*/
		
	
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}


	}

}
