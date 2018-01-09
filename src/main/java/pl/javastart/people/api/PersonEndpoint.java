package pl.javastart.people.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import pl.javastart.people.domain.Person;
import pl.javastart.people.persisatnce.PersonDAO;
import pl.javastart.people.persisatnce.PersonDAOImplDBSimulator;

@Path("/persons")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class PersonEndpoint {

	private PersonDAO dao = new PersonDAOImplDBSimulator();

	@GET
	public List<Person> getAllPersons(@QueryParam("orderBy") @DefaultValue("asc") String order) {
		System.out.println("CHECK getAllPersons");

		List<Person> allPersons = dao.getAllPersons();

		 if ("asc".equals(order)) {
		 allPersons.sort((a, b) -> a.getSurname().compareTo(b.getSurname()));
		 } else if ("desc".equals(order)) {
		 allPersons.sort((a, b) -> b.getSurname().compareTo(a.getSurname()));
		 }

		return allPersons;
	}

	@GET
	@Path("/{id}")
	public Person getPerson(@PathParam("id") long id) {
		System.out.println("CHECK getPerson");

		return dao.getPerson(id);
	}

	@POST
	public Person savePerson(Person person) {
		System.out.println("CHECK savePerson");

		return dao.addPerson(person);
	}
	
	@PUT
	public Person updatePerson(Person person) {
		System.out.println("CHECK savePerson");
		
		return dao.updatePerson(person);
	}
	
	@DELETE
	@Path("/{id}")
	public Person deletePerson (@PathParam("id") long id) {
		Person person = getPerson(id);
		if(person != null) {
			return dao.deletePerson(person);
		}
		return null;
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

}
