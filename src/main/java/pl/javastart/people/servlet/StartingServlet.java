package pl.javastart.people.servlet;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.javastart.people.domain.Person;
import pl.javastart.people.persisatnce.PersonDAO;
import pl.javastart.people.persisatnce.PersonDAOImplDBSimulator;

@WebServlet("/")
public class StartingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PersonDAO dao = new PersonDAOImplDBSimulator();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StartingServlet run");
		determineMessage(request);
		List<Person> persons = dao.getAllPersons();
		request.getSession().setAttribute("persons", persons);
		request.getRequestDispatcher("/people.jsp").forward(request, response);
	}
	
	private void determineMessage(HttpServletRequest request) {
		String message = (String) request.getSession().getAttribute("message");
		LocalTime messageTimeout = (LocalTime) request.getSession().getAttribute("messageTimeout");
		
		if (message == null || Duration.between(LocalTime.now(), messageTimeout).isNegative()) {
			message = "Witaj w aplikacji!";
			request.getSession().setAttribute("messageTimeout", LocalTime.now());
			request.getSession().setAttribute("message", message);
		}
	}

}
