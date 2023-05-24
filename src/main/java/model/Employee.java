package model;

import lombok.*;
import model.enums.Gender;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
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
}