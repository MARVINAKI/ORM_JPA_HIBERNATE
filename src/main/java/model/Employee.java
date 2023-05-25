package model;

import lombok.*;
import model.enums.Gender;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "surname", nullable = false)
	private String surname;
	@Column(name = "gender", nullable = false)
	private String gender;

	public Employee(String name, String surname, Gender gender, City city) {
		this.id = generator();
		this.name = name;
		this.surname = surname;
		this.gender = gender.getGender();
		this.city = city;
	}

	private String generator() {
		return RandomStringUtils.randomAlphabetic(9);
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "city_id")
	private City city;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(gender, employee.gender);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, surname, gender, city);
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", gender='" + gender + '\'' +
				'}';
	}
}