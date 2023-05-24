package DAO.Impl;

import DAO.CityDAO;
import model.City;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;


public class CityDAOImpl implements CityDAO {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");

	@Override
	public void addCity(City city) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(city);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public City findById(Integer id) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		City city = em.find(City.class, id);
		em.getTransaction().commit();
		return city;
	}

	@Override
	public List<City> getAllCities() {
		EntityManager em = getEntityManager();
		List<City> cities;
		em.getTransaction().begin();
		TypedQuery<City> cityTypedQuery = em.createQuery("select c from City c", City.class);
		cities = cityTypedQuery.getResultList();
		em.getTransaction().commit();
		return cities;
	}

	@Override
	public void updateById(Integer id, City city) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		city.setId(id);
		em.merge(city);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		City city = em.find(City.class, id);
		if (city != null) {
			em.remove(city);
		}
		em.getTransaction().commit();
	}

	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
