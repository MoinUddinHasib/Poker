package it.prova.poker.servlet.playmanagement;

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

import it.prova.poker.model.Tavolo;
import it.prova.poker.model.User;
import it.prova.poker.service.tavolo.TavoloService;
import it.prova.poker.service.user.UserService;

/**
 * Servlet implementation class GiocaPartitaServlet
 */
@WebServlet("/GiocaPartitaServlet")
public class GiocaPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;
	
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
    public GiocaPartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute("user");
		String idTavolo=request.getParameter("id");
		Tavolo t=null;
		try {
			t=tavoloService.caricaSingoloTavolo(Long.parseLong(idTavolo));
		} catch (NumberFormatException e) {
			response.sendRedirect("ServletLogOut");
			return;
		}
		if(u.getCreditoAccumulato()<t.getCifraMin()) {
			request.setAttribute("errorMessage", "Credito minimo non raggiunto");
			request.getRequestDispatcher("/play_management/results.jsp").forward(request, response);
			return;
		}
		u.setTavolo_gioco(t);
		userService.aggiorna(u);
		request.getRequestDispatcher("/play_management/gioca.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
