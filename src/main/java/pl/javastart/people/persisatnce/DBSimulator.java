package pl.javastart.people.persisatnce;

import java.util.HashMap;
import java.util.Map;

import pl.javastart.people.domain.Person;

public class DBSimulator {

	private static DBSimulator dbSimuatorInstance;

	private static Map<Long, Person> persons;

	private DBSimulator() {
		persons = new HashMap<Long, Person>();
		persons.put(1L, new Person(1L, "Tadeusz", "Bodzioch", null));
	}

	public static DBSimulator getInstance() {
		if (dbSimuatorInstance == null) {
			dbSimuatorInstance = new DBSimulator();
		}

		return dbSimuatorInstance;
	}

	public Map<Long, Person> getPersons() {
		return persons;
	}

}
