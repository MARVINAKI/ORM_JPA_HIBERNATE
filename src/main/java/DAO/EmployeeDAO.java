package DAO;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {

	void addEmployee(Employee employee);

	Employee findById(String id);

	List<Employee> getAllEmployees();

	void updateById(String id, Employee employee);

	void deleteById(String id);
}
