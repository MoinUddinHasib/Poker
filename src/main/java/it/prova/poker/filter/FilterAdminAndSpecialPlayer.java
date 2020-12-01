package it.prova.poker.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.prova.poker.model.Ruolo;
import it.prova.poker.model.Ruolo.Tipo;
import it.prova.poker.model.User;

/**
 * Servlet Filter implementation class FilterAdminAndSpecialPlayer
 */
@WebFilter(urlPatterns = {"/CancellaTavoloServlet", "/CercaTavoloServlet" , "/DettaglioTavoloServlet",
		"/InserisciTavoloServlet", "/ModificaTavoloServlet", "/gestione_tavolo/*"})
public class FilterAdminAndSpecialPlayer implements Filter {

    /**
     * Default constructor. 
     */
    public FilterAdminAndSpecialPlayer() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session = req.getSession();	
		User u=(User) session.getAttribute("user");
		
		if(u==null) {
			res.sendRedirect(req.getContextPath());
			return;
		}
		
		for(Ruolo r: u.getRuoli()) {
			if(r.getTipo().equals(Tipo.ADMIN_ROLE) || r.getTipo().equals(Tipo.SPECIAL_PLAYER_ROLE)) {
				chain.doFilter(request, response);		
				return;
			}
		}
		req.setAttribute("errorMessage", "Non hai i permessi");
		req.getRequestDispatcher("/home.jsp").forward(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
