package it.prova.poker.servlet.gestionetavolo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.poker.dto.TavoloDTO;
import it.prova.poker.model.Tavolo;
import it.prova.poker.model.User;
import it.prova.poker.service.tavolo.TavoloService;

/**
 * Servlet implementation class ModificaTavoloServlet
 */
@WebServlet("/ModificaTavoloServlet")
public class ModificaTavoloServlet extends HttpServlet {
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
    public ModificaTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		User u= (User) session.getAttribute("user");
		long id=0;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ServletLogOut");
			return;
		}
		request.setAttribute("id", id);
		Tavolo tavolo= tavoloService.caricaSingoloTavoloConPartecipanti(id);
		if(tavolo.getUsers().size()>0) {
			request.setAttribute("errorMessage", "Impossibile modificare: ci sono giocatori a quel tavolo");
			request.getRequestDispatcher("/CercaTavoloServlet").forward(request, response);
			return;
		}
		request.setAttribute("tavoloCampi", new TavoloDTO(tavolo.getEsperienzaMin().toString(),
				tavolo.getCifraMin().toString(),
				tavolo.getDenominazione(),
				u));
		request.getRequestDispatcher("/gestione_tavolo/update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		User u= (User) session.getAttribute("user");
		String esperienza=request.getParameter("esperienza");
		String denominazione=request.getParameter("denominazione");
		String puntata=request.getParameter("puntata");
		long id=0;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/ServletLogOut");
			return;
		}
		TavoloDTO tavoloDTO=new TavoloDTO(esperienza,puntata,denominazione,u);
		
		List<String> tavoloErrors = tavoloDTO.errors();
		if (!tavoloErrors.isEmpty()) {
			request.setAttribute("id", id);
			request.setAttribute("tavoloCampi", tavoloDTO);
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/gestione_tavolo/update.jsp").forward(request, response);
			return;
		}
		Tavolo Tdto=TavoloDTO.buildModelFromDto(tavoloDTO);
		Tavolo t=tavoloService.caricaSingoloTavolo(id);
		t.setEsperienzaMin(Tdto.getEsperienzaMin());
		t.setCifraMin(Tdto.getCifraMin());
		t.setDenominazione(Tdto.getDenominazione());
		tavoloService.aggiorna(t);
		request.setAttribute("messaggioConferma","Tavolo modificato");
		request.getRequestDispatcher("/gestione_tavolo/search.jsp").forward(request, response);
	}

}
