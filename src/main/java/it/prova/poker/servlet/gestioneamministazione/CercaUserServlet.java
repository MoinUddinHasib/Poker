package it.prova.poker.servlet.gestioneamministazione;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.poker.model.Ruolo;
import it.prova.poker.model.User;
import it.prova.poker.model.User.Stato;
import it.prova.poker.service.ruolo.RuoloService;
import it.prova.poker.service.user.UserService;

/**
 * Servlet implementation class CercaUserServlet
 */
@WebServlet("/CercaUserServlet")
public class CercaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RuoloService ruoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CercaUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("stati",Arrays.asList("CREATO","ATTIVO","INATTIVO"));
		request.setAttribute("ruoli",ruoloService.listAllRuoli());
		request.getRequestDispatcher("/gestione_amministrazione/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String username=request.getParameter("username");
		String data=request.getParameter("data");
		String stato=request.getParameter("stato");
		String ruoloid=request.getParameter("ruolo");
		
		if(nome==null || cognome==null || username==null || data==null
				|| stato==null || ruoloid==null) {
			response.sendRedirect(request.getContextPath()+"/ServletLogOut");
			return;
		}
		//Qui non si può utilizzano i DTO perchè i campi possono essere vuoti
		User u= new User(nome,cognome,username,null);
		try {
			u.setDataRegistrazione(data.isEmpty()?null:LocalDate.parse(data));
			u.setStato(stato.isEmpty()?null:Stato.valueOf(stato));
			if(!ruoloid.isEmpty()) {
				Ruolo r=ruoloService.caricaSingoloRuolo(Long.parseLong(ruoloid));
				if(r==null) {
					throw new Exception("CercaUserServlet");
				}
				u.getRuoli().add(r);
			}			
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/ServletLogOut");
			return;
		}
		
		request.setAttribute("listaUsers",userService.findByExample(u));

		request.getRequestDispatcher("/gestione_amministrazione/results.jsp").forward(request, response);
	}

}
