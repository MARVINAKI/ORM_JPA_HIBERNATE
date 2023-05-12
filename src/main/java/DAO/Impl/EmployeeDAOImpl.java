package DAO.Impl;

import DAO.EmployeeDAO;
import MyException.DatabaseConnectionErrorException;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

	final String user = "myUser";
	final String password = "123";
	final String url = "jdbc:postgresql://localhost:5432/myDB";

	@Override
	public void addEmployee(Employee employee) {
		try (Connection connection = openConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee(id,name,surname,gender,city) VALUES (?,?,?,?,?)");
			preparedStatement.setString(1, employee.getId());
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setString(3, employee.getSurname());
			preparedStatement.setString(4, employee.getGender());
			preparedStatement.setString(5, employee.getCity());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee findById(String id) {
		Employee employee = new Employee();
		try (Connection connection = openConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id=?");
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee.setId(id);
				employee.setName(resultSet.getString("name"));
				employee.setSurname(resultSet.getString("surname"));
				employee.setGender(resultSet.getString("gender"));
				employee.setCity(resultSet.getString("city"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> listOfEmployees = new ArrayList<>();
		Employee employee = new Employee();
		try (Connection connection = openConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee.setId(resultSet.getString(1));
				employee.setName(resultSet.getString(2));
				employee.setSurname(resultSet.getString(3));
				employee.setGender(resultSet.getString(4));
				employee.setCity(resultSet.getString(5));
				listOfEmployees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfEmployees;
	}

	@Override
	public boolean updateById(String id, Employee employee) {
		boolean check = false;
		try (Connection connection = openConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET name=?, surname=?, gender=?, city=? WHERE id=?");
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getSurname());
			preparedStatement.setString(3, employee.getGender());
			preparedStatement.setString(4, employee.getCity());
			preparedStatement.setString(5, id);
			check = preparedStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean deleteById(String id) {
		boolean check = false;
		try (Connection connection = openConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id=?");
			preparedStatement.setString(1, id);
			check = preparedStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	private Connection openConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseConnectionErrorException();
		}
	}
}
