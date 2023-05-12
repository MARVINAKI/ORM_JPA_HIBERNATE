import DAO.EmployeeDAO;
import DAO.Impl.EmployeeDAOImpl;
import model.Employee;
import model.enums.Gender;

public class Application {
	public static void main(String[] args) {
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		System.out.println(employeeDAO.findById("UvfaAImOR"));
		employeeDAO.updateById("UvfaAImOR", new Employee("IsItWork?", "IsItWork?", Gender.FEMALE, 2));
		employeeDAO.deleteById("PfsNKSwkV");
		System.out.println(employeeDAO.getAllEmployees());
	}
}
