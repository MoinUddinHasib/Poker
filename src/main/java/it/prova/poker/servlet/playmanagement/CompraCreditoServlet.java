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

import it.prova.poker.model.User;
import it.prova.poker.service.user.UserService;

/**
 * Servlet implementation class CompraCreditoServlet
 */
@WebServlet("/CompraCreditoServlet")
public class CompraCreditoServlet extends HttpServlet {
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
    public CompraCreditoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/play_management/compra_credito.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String importo= request.getParameter("importo");
		User u=(User) session.getAttribute("user");
		
		try {
			u.setCreditoAccumulato(u.getCreditoAccumulato()+Integer.parseInt(importo));
		} catch (NumberFormatException e) {
			response.sendRedirect("ServletLogOut");
			return;
		}
		userService.aggiorna(u);
		request.setAttribute("messaggioConferma", "Credito acquistato");
		request.getRequestDispatcher("/play_management/compra_credito.jsp").forward(request, response);;
	}

}
