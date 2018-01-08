package pl.javastart.people.persisatnce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.javastart.people.domain.Person;

public class PersonDAOImplDBSimulator implements PersonDAO {

	Map<Long, Person> persons = DBSimulator.getInstance().getPersons();

	@Override
	public Person addPerson(Person person) {
		long size = persons.size();
		person.setId(size + 1);
		return persons.put(size + 1, person);
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
	public boolean updatePerson(Person person) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePerson(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
