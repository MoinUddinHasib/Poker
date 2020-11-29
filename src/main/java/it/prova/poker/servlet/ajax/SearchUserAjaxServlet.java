package it.prova.poker.servlet.ajax;

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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.poker.model.User;
import it.prova.poker.service.user.UserService;

@WebServlet("/SearchUserAjaxServlet")
public class SearchUserAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public SearchUserAjaxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String term = request.getParameter("term");

		List<User> listaUsersByTerm = userService.cercaByUsernameILike(term);
		String json = buildJsonResponse(listaUsersByTerm);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private String buildJsonResponse(List<User> listaUsers) {
		JsonArray ja = new JsonArray();

		for (User userElement : listaUsers) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", userElement.getId());
			jo.addProperty("label", userElement.getUsername());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}

}
