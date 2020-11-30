package it.prova.poker.servlet.gestionetavolo;

import java.io.IOException;
import java.time.LocalDate;

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

/**
 * Servlet implementation class CercaTavoloServlet
 */
@WebServlet("/CercaTavoloServlet")
public class CercaTavoloServlet extends HttpServlet {
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
    public CercaTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/gestione_tavolo/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute("user");
		
		String denominazione=request.getParameter("denominazione");
		String data=request.getParameter("data");
		String puntata=request.getParameter("puntata");
		
		if(denominazione==null || data==null || puntata==null) {
			response.sendRedirect("/ServletLogOut");
			return;
		}
		Tavolo t=new Tavolo(null, puntata.isEmpty()?null:Integer.parseInt(puntata), denominazione, u);
		try {
			t.setDataCreazione(data.isEmpty()?null:LocalDate.parse(data));
		} catch (Exception e) {
			response.sendRedirect("/ServletLogOut");
			return;
		}
		
		request.setAttribute("listaTavoli",tavoloService.findByExample2(t));

		request.getRequestDispatcher("/gestione_tavolo/results.jsp").forward(request, response);
	}

}
