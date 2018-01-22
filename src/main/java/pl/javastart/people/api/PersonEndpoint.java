package pl.javastart.people.api;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import pl.javastart.people.domain.Person;
import pl.javastart.people.persisatnce.PersonDAO;
import pl.javastart.people.persisatnce.PersonDAOImplDBSimulator;

@Path("/persons")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class PersonEndpoint {

	private static final int TIMEOUT_LIMIT_IN_SECONDS = 1;
	
	private PersonDAO dao = new PersonDAOImplDBSimulator();
	private String message = null;
	
	@GET
	public Response getAllPersons(@QueryParam("orderBy") @DefaultValue("asc") String order) {
		System.out.println("CHECK getAllPersons");

		List<Person> allPersons = dao.getAllPersons();

		if ("asc".equals(order)) {
			allPersons.sort((a, b) -> a.getSurname().compareTo(b.getSurname()));
		} else if ("desc".equals(order)) {
			allPersons.sort((a, b) -> b.getSurname().compareTo(a.getSurname()));
		}

		Person[] allPersonsArray = allPersons.toArray(new Person[] {});

		return setDetails(Response.ok().entity(allPersonsArray));
	}

	@GET
	@Path("/{id}")
	public Response getPerson(@PathParam("id") long id) {
		System.out.println("CHECK getPerson");

		Person person = dao.getPerson(id);

		if (person != null) {
			return setDetails(Response.ok().entity(person));
		} else {
			return setDetails(Response.noContent());
		}
	}

	@POST
	public Response savePerson(Person person) {
		System.out.println("CHECK savePerson");

		dao.addPerson(person);
		return setDetails(Response.ok().entity(person));
	}

	@PUT
	public Response updatePerson(Person person) {
		System.out.println("CHECK updatePerson");
		Person updatedPerson = dao.updatePerson(person);

		if (updatedPerson != null) {
			return setDetails(Response.ok().entity(updatedPerson));
		} else {
			return setDetails(Response.noContent());
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deletePerson(@PathParam("id") long id) {
		System.out.println("CHECK deletePerson");
		Person person = (Person) getPerson(id).getEntity();
		if (person != null) {
			Person deletedPerson = dao.deletePerson(person);
			return setDetails(Response.ok().entity(deletedPerson));
		} else {
			return setDetails(Response.noContent());
		}
	}

	// @GET
	// @Path("/{id}/numbers")
	// public List<String> getAllNumbers(@PathParam("id") int id) {
	// return persons.get(id - 1).getNumbers();
	// }
	//
	// @GET
	// @Path("/{id}/numbers/{numberId}")
	// public String getNumber(@PathParam("id") int id, @PathParam("numberId") int
	// numberId) {
	// if (persons.size() < id) {
	// return null;
	// } else if (null == persons.get(id - 1).getNumbers() || persons.get(id -
	// 1).getNumbers().size() < numberId) {
	// return null;
	// } else {
	// return persons.get(id - 1).getNumbers().get(numberId - 1);
	// }
	// }

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void savePersonFromForm(@FormParam("name") String name,
										@FormParam("surname") String surname,
										@FormParam("number") String number,
										@Context HttpServletRequest request,
										@Context HttpServletResponse response) throws IOException, ServletException
	{
		System.out.println("CHECK savePersonFromForm");
		if(!"".equals(name) && !"".equals(surname)) {
			Person person = new Person();
			person.setName(name);
			person.setSurname(surname);
			if (!"".equals(number)) {
				person.setNumbers(Arrays.asList(number));
			}
			dao.addPerson(person);
			
			message = "User " + person.getName() + " " + person.getSurname() + " sucesfully added.";
		}else {
			message = "TRY AGAIN! Fields name and surname are obligatory.";
		}
		
		request.getSession().setAttribute("message", message);
		request.getSession().setAttribute("messageTimeout", LocalTime.now().plusSeconds(TIMEOUT_LIMIT_IN_SECONDS));

		
		response.sendRedirect(request.getContextPath());
	}
	
	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void deletePersonFromForm(@PathParam ("id") long id,
										@Context HttpServletRequest request,
										@Context HttpServletResponse response) throws IOException, ServletException 
	{
		System.out.println("CHECK deletePersonFromForm");
		Person person = dao.getPerson(id);
		if (person != null) {
			Person deletedPerson = dao.deletePerson(person);
			message = "User " + deletedPerson.getName() + " " + deletedPerson.getSurname() + " sucesfully deleted.";
		}
		
		request.getSession().setAttribute("message", message);
		request.getSession().setAttribute("messageTimeout", LocalTime.now().plusSeconds(TIMEOUT_LIMIT_IN_SECONDS));
		
		response.sendRedirect(request.getContextPath());
	}
	
	private Response setDetails(ResponseBuilder responseBuilder) {
		return responseBuilder.cookie(new NewCookie("auth-token", Long.toString(System.currentTimeMillis())))
				.header("test-header", "example value").encoding("UTF-8").build();
		}
}
