package it.prova.poker.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.poker.model.User;
import it.prova.poker.service.user.UserService;

/**
 * Servlet implementation class ServletLoggin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
			response.sendRedirect(request.getContextPath());
			session.invalidate();
			return;
		} 
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();

		User uten=userService.caricaPerUsername(username);		
		if(uten==null || !uten.getUsername().equals(username) || !uten.getPassword().equals(password) || uten.getStato().equals(User.Stato.INATTIVO)
				|| uten.getStato().equals(User.Stato.CREATO)) {
			request.setAttribute("errorMessage", "Accesso negato");
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		session.setAttribute("user", uten);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
