package pl.javastart.people.persisatnce;

import java.util.List;

import pl.javastart.people.domain.Person;

public interface PersonDAO {

	public abstract Person addPerson(Person person);

	public abstract Person getPerson(long id);

	public abstract List<Person> getAllPersons();

	public abstract boolean updatePerson(Person person);

	public abstract boolean deletePerson(long id);

}
