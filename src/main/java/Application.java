import DAO.CityDAO;
import DAO.EmployeeDAO;
import DAO.Impl.CityDAOImpl;
import DAO.Impl.EmployeeDAOImpl;
import model.City;
import model.Employee;
import model.enums.Gender;

public class Application {
	public static void main(String[] args) {
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		CityDAO cityDAO = new CityDAOImpl();

		employeeDAO.addEmployee(new Employee("TEST", "TEST", Gender.MALE, new City("UFA")));
	}
}
