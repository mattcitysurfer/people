package pl.javastart.people;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/persons")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class PersonEndpoint {

	public List<Person> persons = getPersons();

	@GET
	public List<Person> getAllPersons(@QueryParam("orderBy") @DefaultValue("asc") String order) {
		System.out.println("Check GET");
		if ("asc".equals(order)) {
			persons.sort((a, b) -> a.getSurname().compareTo(b.getSurname()));
		} else if ("desc".equals(order)) {
			persons.sort((a, b) -> b.getSurname().compareTo(a.getSurname()));
		}

		return persons;
	}

	@GET
	@Path("/{id}")
	public Person getPerson(@PathParam("id") int id) {
		if (persons.size() < id) {
			return null;
		}
		return persons.get(id - 1);
	}

	@POST
	public Person savePerson(Person person) {
		person.setId(persons.size() + 1);
		persons.add(person);
		return person;
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

	private List<Person> getPersons() {
		return DBSimulator.getInstance().getPersons();
	}

}
