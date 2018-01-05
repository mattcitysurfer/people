package pl.javastart.people;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/persons")
public class PersonEndpoint {

	private static List<Person> persons;
	private static List<String> phones;

	public PersonEndpoint() {
		persons = new ArrayList<>();

		phones = Arrays.asList(new String[] { "123", "456", "789" });
		persons.add(new Person(1, "Mateusz", "Rodoch", phones));
		persons.add(new Person(2, "Jan", "Kowalski", null));
		persons.add(new Person(3, "Piotr", "Malinowski", null));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAll() {
		return persons;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("id") int id) {
		return persons.get(id-1);
	}

	@GET
	@Path("/{id}/phones/{phoneId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPhone(@PathParam("id") int id, @PathParam("phoneId") int phoneId) {
		if(null != persons.get(id-1).getNumbers()) {
			return persons.get(id-1).getNumbers().get(phoneId-1);
		}else {
			return "No numbers";
		}
	}

}
