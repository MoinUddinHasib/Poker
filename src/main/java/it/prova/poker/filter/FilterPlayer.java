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

import it.prova.poker.model.User;

/**
 * Servlet Filter implementation class FilterPlayer
 */
@WebFilter(urlPatterns = {"/CercaPartitaServlet", "/CompraCreditoServlet" , "/EsecuzionePartitaServlet",
		"/GiocaPartitaServlet", "/LastGameServlet", "/play_management/*", "/home.jsp", "/SearchUserAjaxServlet"})
public class FilterPlayer implements Filter {

    /**
     * Default constructor. 
     */
    public FilterPlayer() {
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
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
