package model;

import lombok.*;
import org.apache.commons.lang3.RandomUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City {
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "name", nullable = false)
	private String cityName;

	public City(String cityName) {
		this.id = generator();
		this.cityName = cityName;
	}

	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Employee> employees;

	private Integer generator() {
		return RandomUtils.nextInt(1, 10000);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		City city = (City) o;
		return Objects.equals(id, city.id) && Objects.equals(cityName, city.cityName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cityName, employees);
	}

	@Override
	public String toString() {
		return "City{" +
				"id=" + id +
				", cityName='" + cityName + '\'' +
				'}';
	}
}
