package it.prova.poker.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
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
import it.prova.poker.model.User;
import it.prova.poker.model.User.Stato;
import it.prova.poker.service.user.UserService;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
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
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("form_registrazione.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome= request.getParameter("nome");
		String cognome= request.getParameter("cognome");
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		UserDTO userDTO= new UserDTO(nome,cognome,username,password);
		
		List<String> userErrors = userDTO.errors();
		if (!userErrors.isEmpty()) {
			request.setAttribute("userCampi", userDTO);
			request.setAttribute("userErrors", userErrors);
			request.getRequestDispatcher("form_registrazione.jsp").forward(request, response);
			return;
		}
		if(userService.caricaPerUsername(username)!=null) {
			request.setAttribute("userCampi", userDTO);
			request.setAttribute("userErrors", Arrays.asList("Username già esistente"));
			request.getRequestDispatcher("form_registrazione.jsp").forward(request, response);
			return;
		}
		User u=UserDTO.buildModelFromDto(userDTO);
		u.setDataRegistrazione(LocalDate.now());
		u.setEsperienzaAccumulata(0);
		u.setCreditoAccumulato(0);
		u.setStato(Stato.CREATO);
		userService.inserisciNuovo(u);
		request.setAttribute("messaggioConferma","Registrazione avvenuta con successo");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
