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
import it.prova.poker.service.user.UserService;

/**
 * Servlet implementation class EsecuzionePartitaServlet
 */
@WebServlet("/EsecuzionePartitaServlet")
public class EsecuzionePartitaServlet extends HttpServlet {
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
    public EsecuzionePartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute("user");
		u.setEsperienzaAccumulata(u.getEsperienzaAccumulata()+1);
		u.setTavolo_gioco(null);
		userService.aggiorna(u);
		request.getRequestDispatcher("/play_management/PlayManagement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute("user");
		Tavolo t=u.getTavolo_gioco();
		double segno=Math.random();
		if(segno>=0.5) {
			segno=1;
		}else {
			segno=-1;
		}
		int somma=(int) (Math.random()*1000);
		int tot=(int) (somma*segno);
		
		if(u.getCreditoAccumulato()<t.getCifraMin()) {
			request.setAttribute("errorMessage", "Non hai abbastanza credito");
			request.getRequestDispatcher("/play_management/gioca.jsp").forward(request, response);
			return;
		}
		
		if(u.getCreditoAccumulato()+tot<0) {
			u.setCreditoAccumulato(0);
			request.setAttribute("errorMessage", "Hai finito il credito");
		}else {
			u.setCreditoAccumulato(u.getCreditoAccumulato()+tot);
			if(tot>0) {
				request.setAttribute("messaggioConferma", "Hai vinto: "+tot);
			}else {
				request.setAttribute("errorMessage", "Hai perso: "+tot);
			}
			
		}
		userService.aggiorna(u);
		request.getRequestDispatcher("/play_management/gioca.jsp").forward(request, response);
	}

}
