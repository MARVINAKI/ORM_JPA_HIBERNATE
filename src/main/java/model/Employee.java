package model;

import lombok.*;
import model.enums.Gender;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@Column(name = "id", nullable = false)
	private String id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "surname", nullable = false)
	private String surname;
	@Column(name = "gender", nullable = false)
	private String gender;
	@Column(name = "city", nullable = false)
	private int city;

	public Employee(String name, String surname, Gender gender, int city) {
		this.id = generator();
		this.name = name;
		this.surname = surname;
		this.gender = gender.getGender();
		this.city = city;
	}

	private String generator() {
		return RandomStringUtils.randomAlphabetic(9);
	}
}
