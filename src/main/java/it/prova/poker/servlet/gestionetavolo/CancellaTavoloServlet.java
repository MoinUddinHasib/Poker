package it.prova.poker.servlet.gestionetavolo;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.poker.model.Tavolo;
import it.prova.poker.service.tavolo.TavoloService;

/**
 * Servlet implementation class CancellaTavoloServlet
 */
@WebServlet("/CancellaTavoloServlet")
public class CancellaTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaTavoloServlet() {
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
		Tavolo tavolo= tavoloService.caricaSingoloTavoloConPartecipanti(id);
		if(tavolo.getUsers().size()>0) {
			request.setAttribute("errorMessage", "Impossibile eliminare: ci sono giocatori a quel tavolo");
			request.getRequestDispatcher("/CercaTavoloServlet").forward(request, response);
			return;
		}
		tavoloService.rimuovi(tavolo);
		request.setAttribute("messaggioConferma", "Eliminazione avvenuta con successo");
		request.getRequestDispatcher("/gestione_tavolo/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
