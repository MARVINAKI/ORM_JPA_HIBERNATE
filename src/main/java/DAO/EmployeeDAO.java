package DAO;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {

	void addEmployee(Employee employee);

	Employee findById(String id);

	List<Employee> getAllEmployees();

	boolean updateById(String id, Employee employee);

	boolean deleteById(String id);
}
