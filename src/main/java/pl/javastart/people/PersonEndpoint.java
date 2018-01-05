package pl.javastart.people;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

@Path("/persons")
public class PersonEndpoint {

	private static List<Person> persons;
	private static List<String> numbers;

	public PersonEndpoint() {
		persons = new ArrayList<>();

		numbers = Arrays.asList(new String[] { "123", "456", "789" });
		persons.add(new Person(1, "Mateusz", "Rodoch", numbers));
		persons.add(new Person(2, "Jan", "Kowalski", null));
		persons.add(new Person(3, "Piotr", "Malinowski", null));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAllPersons(@QueryParam("orderBy") @DefaultValue("asc") String order) {

		if ("asc".equals(order)) {
			persons.sort((a, b) -> a.getSurname().compareTo(b.getSurname()));
		} else if ("desc".equals(order)) {
			persons.sort((a, b) -> b.getSurname().compareTo(a.getSurname()));
		}

		return persons;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("id") int id) {
		return persons.get(id - 1);
	}

//	@GET
//	@Path("/{id}/numbers")
//	@Produces(MediaType.APPLICATION_JSON)
//	public GenericEntity<List<String>> getAllNumbers(@PathParam("id") int id){
//		List <String> numbers = persons.get(id-1).getNumbers();
//		return new GenericEntity<List<String>>(numbers) {};
//	}
	
	@GET
	@Path("/{id}/numbers/{numberId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNumber(@PathParam("id") int id, @PathParam("numberId") int numberId) {
		if (persons.size() < id) {
			return null;
		} else if (null == persons.get(id - 1).getNumbers() || persons.get(id - 1).getNumbers().size() < numberId) {
			return null;
		} else {
			return persons.get(id - 1).getNumbers().get(numberId - 1);
		}
	}

}
