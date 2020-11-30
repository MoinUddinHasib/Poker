package it.prova.poker.servlet.playmanagement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
 * Servlet implementation class CercaPartitaServlet
 */
@WebServlet("/CercaPartitaServlet")
public class CercaPartitaServlet extends HttpServlet {
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
    public CercaPartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/play_management/form_cerca.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute("user");
		
		String denominazione=request.getParameter("denominazione");
		String data=request.getParameter("data");
		String puntataMinima=request.getParameter("puntata");
		String creatoreId=request.getParameter("creatoreId");
		String partecipanteId=request.getParameter("partecipanteId");

		User u_creatore=null;
		User u_partecipante=null;
		LocalDate d=null;
		Integer puntata=null;
		try {
			u_creatore=userService.caricaSingoloUser((creatoreId==null||creatoreId.isEmpty())?-1:Long.parseLong(creatoreId));
			u_partecipante=userService.caricaSingoloUser((partecipanteId==null||partecipanteId.isEmpty())?-1:Long.parseLong(partecipanteId));
			d=(data==null||data.isEmpty())?null:LocalDate.parse(data);
			puntata=(puntataMinima==null||puntataMinima.isEmpty())?0:Integer.parseInt(puntataMinima);
		} catch (NumberFormatException|DateTimeParseException  e) {
			response.sendRedirect("ServletLogOut");
			return;
		}
		Tavolo t=new Tavolo(u.getEsperienzaAccumulata(),puntata,denominazione==null?"":denominazione,u_creatore);
		t.setDataCreazione(d);
		if(u_partecipante!=null) {
			t.getUsers().add(u_partecipante);
		}
		
		request.setAttribute("listaTavoli",tavoloService.findByExample(t));
		System.out.println(d);
		request.getRequestDispatcher("/play_management/results.jsp").forward(request, response);

	}

}
