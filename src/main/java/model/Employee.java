package model;

import lombok.*;
import model.enums.Gender;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Employee {
	private String id;
	private String name;
	private String surname;
	private String gender;
	private String city;

	public Employee(String name, String surname, Gender gender, City city) {
		this.id = RandomStringUtils.randomAlphabetic(9);
		this.name = name;
		this.surname = surname;
		this.gender = gender.getGender();
		this.city = city.getCityName();
	}
}
