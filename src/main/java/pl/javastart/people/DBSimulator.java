package pl.javastart.people;

import java.util.ArrayList;
import java.util.List;

public class DBSimulator {

	private static DBSimulator dbSimuatorInstance;

	private static List<Person> persons;

	private DBSimulator() {
		persons = new ArrayList<Person>();
		persons.add(new Person(1, "Mateusz", "Bodzioch", null));
	}

	public static DBSimulator getInstance() {
		if (dbSimuatorInstance == null) {
			dbSimuatorInstance = new DBSimulator();
		}

		return dbSimuatorInstance;
	}

	public List<Person> getPersons() {
		return persons;
	}

}
