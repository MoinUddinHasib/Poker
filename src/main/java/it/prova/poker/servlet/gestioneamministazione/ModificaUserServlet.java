package it.prova.poker.servlet.gestioneamministazione;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.poker.dto.UserDTO;
import it.prova.poker.model.Ruolo;
import it.prova.poker.model.Ruolo.Tipo;
import it.prova.poker.model.User;
import it.prova.poker.model.User.Stato;
import it.prova.poker.service.ruolo.RuoloService;
import it.prova.poker.service.user.UserService;

/**
 * Servlet implementation class ModificaUserServlet
 */
@WebServlet("/ModificaUserServlet")
public class ModificaUserServlet extends HttpServlet {
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
    public ModificaUserServlet() {
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
		
		for(Ruolo r: u.getRuoli()) {
			if(r.getTipo().equals(Tipo.ADMIN_ROLE)) {
				request.setAttribute("cond_admin", true);
			}
			if(r.getTipo().equals(Tipo.SPECIAL_PLAYER_ROLE)) {
				request.setAttribute("cond_special", true);
			}
			if(r.getTipo().equals(Tipo.PLAYER_ROLE)) {
				request.setAttribute("cond_player", true);
			}
		}
		
		
		
		request.setAttribute("id", id);
		
		request.setAttribute("admin", "ADMIN_ROLE");
		request.setAttribute("special", "SPECIAL_PLAYER_ROLE");
		request.setAttribute("player", "PLAYER");
		
		request.setAttribute("user", u);
		request.setAttribute("stato", u.getStato());
		request.setAttribute("ruoli",ruoloService.listAllRuoli());
		request.getRequestDispatcher("/gestione_amministrazione/update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String username=request.getParameter("username");
		
		String admin=request.getParameter("admin");
		String special=request.getParameter("special");
		String player=request.getParameter("player");

		long id=0;
		Ruolo ra=null;
		Ruolo rs=null;
		Ruolo rp=null;
		try {
			id = Long.parseLong(request.getParameter("id"));
			ra = ruoloService.caricaSingoloRuolo(1l);
			rs = ruoloService.caricaSingoloRuolo(2l);
			rp = ruoloService.caricaSingoloRuolo(3l);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/ServletLogOut");
			return;
		}
		User us=userService.caricaSingoloUserConRuoli(id);
		//La password non può essere modificata
		UserDTO userDTO=new UserDTO(nome,cognome,username,"vuoto");
		
		List<String> userErrors = userDTO.errors();
		if (!userErrors.isEmpty()) {
			request.setAttribute("id", id);
			
			request.setAttribute("admin", "ADMIN_ROLE");
			request.setAttribute("special", "SPECIAL_PLAYER_ROLE");
			request.setAttribute("player", "PLAYER");
			
			request.setAttribute("user", userDTO);
			request.setAttribute("stato", us.getStato());
			request.setAttribute("ruoli",ruoloService.listAllRuoli());
			request.setAttribute("userErrors", userErrors);
			request.getRequestDispatcher("/gestione_amministrazione/update.jsp").forward(request, response);
			return;
		}
		User Udto=UserDTO.buildModelFromDto(userDTO);
	
		us.setNome(Udto.getNome());
		us.setCognome(Udto.getCognome());
		us.setUsername(Udto.getUsername());
		us.getRuoli().clear();
		if(us.getStato().equals(Stato.valueOf("CREATO"))) {
			if(admin !=null)
				us.getRuoli().add(ra);
			if(special !=null)
				us.getRuoli().add(rs);
			if(player !=null)
				us.getRuoli().add(rp);
		}
		userService.aggiorna(us);
		request.setAttribute("messaggioConferma","User modificato");
		request.getRequestDispatcher("/gestione_amministrazione/search.jsp").forward(request, response);
	}

}
