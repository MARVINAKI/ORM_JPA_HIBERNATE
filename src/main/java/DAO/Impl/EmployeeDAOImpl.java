package DAO.Impl;

import DAO.EmployeeDAO;
import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void addEmployee(Employee employee) {
		EntityManager em = open();
		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Employee findById(String id) {
		EntityManager em = open();
		em.getTransaction().begin();
		Employee employee = em.find(Employee.class, id);
		em.getTransaction().commit();
		em.close();
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		EntityManager em = open();
		em.getTransaction().begin();
		TypedQuery<Employee> employeeTypedQuery = em.createQuery("SELECT e FROM Employee e", Employee.class);
		List<Employee> employeeList = employeeTypedQuery.getResultList();
		em.getTransaction().commit();
		em.close();
		return employeeList;
	}

	@Override
	public void updateById(String id, Employee employee) {
		EntityManager em = open();
		em.getTransaction().begin();
		employee.setId(id);
		em.merge(employee);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(String id) {
		EntityManager em = open();
		em.getTransaction().begin();
		Employee employee = em.find(Employee.class, id);
		if (employee != null) {
			em.remove(employee);
		}
		em.getTransaction().commit();
		em.close();
	}

	private EntityManager open() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
		return entityManagerFactory.createEntityManager();
	}
}
