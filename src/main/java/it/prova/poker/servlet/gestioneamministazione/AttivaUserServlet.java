package it.prova.poker.servlet.gestioneamministazione;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.poker.model.User;
import it.prova.poker.model.User.Stato;
import it.prova.poker.service.user.UserService;

/**
 * Servlet implementation class AttivaUserServlet
 */
@WebServlet("/AttivaUserServlet")
public class AttivaUserServlet extends HttpServlet {
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
    public AttivaUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id=0;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/ServletLogOut");
			return;
		}
		
		User u= userService.caricaSingoloUserConRuoli(id);
		if(u.getRuoli().size()==0) {
			request.setAttribute("errorMessage","L'utente non ha ruoli, quindi non può essere attivato");
			request.getRequestDispatcher("/gestione_amministrazione/search.jsp").forward(request, response);
			return;
		}
		u.setStato(Stato.ATTIVO);
		userService.aggiorna(u);
		request.setAttribute("messaggioConferma","User attivato");
		request.getRequestDispatcher("/gestione_amministrazione/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
