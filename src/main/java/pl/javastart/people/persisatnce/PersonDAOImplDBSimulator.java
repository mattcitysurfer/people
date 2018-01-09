package pl.javastart.people.persisatnce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.javastart.people.domain.Person;

public class PersonDAOImplDBSimulator implements PersonDAO {

	private static Map<Long, Person> persons = DBSimulator.getInstance().getPersons();

	private static long nextId = persons.size() + 1;

	@Override
	public Person addPerson(Person person) {
		person.setId(nextId++);
		return persons.put(person.getId(), person);
	}

	@Override
	public Person getPerson(long id) {
		return persons.get(id);
	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> personList = new ArrayList<Person>();
		Set<Long> keys = persons.keySet();
		for (Long key : keys) {
			personList.add(persons.get(key));
		}
		return personList;
	}

	@Override
	public Person updatePerson(Person person) {
		long id = person.getId();
		if (persons.containsKey(id)) {
			return persons.put(id, person);
		}
		return null;
	}

	@Override
	public Person deletePerson(Person person) {
		long id = person.getId();
		return persons.remove(id);
	}

}
