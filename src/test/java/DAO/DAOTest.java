package DAO;

import DAO.Impl.CityDAOImpl;
import DAO.Impl.EmployeeDAOImpl;
import model.City;
import model.Employee;
import model.enums.Gender;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {

	static CityDAO cityDAO = new CityDAOImpl();
	static EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	static City city = new City("testNameOfCity");
	static City cityForUpdate = new City("updatedCityName");
	static Integer cityId = city.getId();
	static Employee employee = new Employee("testNameOfEmployee", "testSurnameOfEmployee", Gender.MALE, city);
	static Employee employeeForUpdate = new Employee("updatedName", "updatedSurname", Gender.FEMALE, cityForUpdate);
	static String employeeId = employee.getId();

	@BeforeAll
	static void fillDB() {
		employeeDAO.addEmployee(employee);
		assertNotNull(employeeDAO.findById(employeeId));
	}

	@AfterAll
	static void clearDB() {
		employeeDAO.deleteById(employeeId);
		assertNull(employeeDAO.findById(employeeId));
		cityDAO.deleteById(cityId);
		assertNull(cityDAO.findById(cityId));
		employeeDAO.deleteById(employeeForUpdate.getId());
		assertNull(employeeDAO.findById(employeeForUpdate.getId()));
		cityDAO.deleteById(cityForUpdate.getId());
		assertNull(cityDAO.findById(cityForUpdate.getId()));
	}


	@Test
	void checkCityInDB() {
		assertEquals("testNameOfCity", cityDAO.findById(cityId).getCityName());
		assertEquals(city.getCityName(), cityDAO.findById(cityId).getCityName());
	}

	@Test
	void checkEmployeeInDB() {
		assertEquals("testNameOfEmployee", employeeDAO.findById(employeeId).getName());
		assertEquals(employee.getName(), employeeDAO.findById(employeeId).getName());
	}

	@Test
	void checkNullEntity() {
		assertThrows(PersistenceException.class, () -> cityDAO.addCity(new City()));
		assertThrows(PersistenceException.class, () -> employeeDAO.addEmployee(new Employee()));
	}

	@Test
	void checkUpdateCityMethod() {
		assertNotEquals(cityForUpdate.getCityName(), cityDAO.findById(cityId).getCityName());
		assertNotEquals(cityForUpdate.getId(), city.getId());
		cityDAO.updateById(cityId, cityForUpdate);
		assertEquals(cityForUpdate.getCityName(), cityDAO.findById(cityId).getCityName());
	}

	@Test
	void checkUpdateEmployeeMethod() {
		assertNotEquals(employeeForUpdate.getName(), employeeDAO.findById(employeeId).getName());
		assertNotEquals(employeeForUpdate.getId(), employee.getId());
		employeeDAO.updateById(employeeId, employeeForUpdate);
		assertEquals(employeeForUpdate.getName(), employeeDAO.findById(employeeId).getName());
		assertEquals(employeeForUpdate.getSurname(), employeeDAO.findById(employeeId).getSurname());
		assertEquals(employeeForUpdate.getGender(), employeeDAO.findById(employeeId).getGender());
		assertEquals(employeeForUpdate.getCity().getId(), employeeDAO.findById(employeeId).getCity().getId());
		assertEquals(employeeForUpdate.getCity().getCityName(), employeeDAO.findById(employeeId).getCity().getCityName());
	}

	@Test
	void checkGetListOfAllCities() {
		List<City> cityList = cityDAO.getAllCities();
		assertEquals(1, cityList.size());
		assertTrue(cityList.contains(city));
	}

	@Test
	void checkGetListOfAllEmployees() {
		List<Employee> employeeList = employeeDAO.getAllEmployees();
		assertEquals(1, employeeList.size());
		assertTrue(employeeList.contains(employee) && employee.getCity().equals(city));
	}
}
