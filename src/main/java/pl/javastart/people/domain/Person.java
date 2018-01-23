package pl.javastart.people.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @XmlTransient
	private long id;

	@XmlElement(name = "imie")
	private String name;

	@XmlElement(name = "nazwisko")
	private String surname;

	@XmlElement(name = "numery-telefonu")
	private List<String> numbers;

	public Person() {

	}

	public Person(long id, String name, String surname, List<String> numbers) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.numbers = numbers;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		String numbersToDisplay = (numbers!=null)?", tel: " + numbers.toString():", brak telefonu";
		return id + ". " + name + " " + surname + numbersToDisplay;
	}
	
}
