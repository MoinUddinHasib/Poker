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
import it.prova.poker.model.User;
import it.prova.poker.service.tavolo.TavoloService;

/**
 * Servlet implementation class InserisciTavoloServlet
 */
@WebServlet("/InserisciTavoloServlet")
public class InserisciTavoloServlet extends HttpServlet {
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
    public InserisciTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/gestione_tavolo/form_tavolo.jsp").forward(request, response);
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
		
		TavoloDTO tavoloDTO=new TavoloDTO(esperienza,puntata,denominazione,u);
		
		List<String> tavoloErrors = tavoloDTO.errors();
		if (!tavoloErrors.isEmpty()) {
			request.setAttribute("tavoloCampi", tavoloDTO);
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/gestione_tavolo/form_tavolo.jsp").forward(request, response);
			return;
		}
		
		tavoloService.inserisciNuovo(TavoloDTO.buildModelFromDto(tavoloDTO));
		request.setAttribute("messaggioConferma","Tavolo creato");
		request.getRequestDispatcher("/gestione_tavolo/search.jsp").forward(request, response);
	}

}
