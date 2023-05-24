package DAO;

import model.City;

import java.util.List;

public interface CityDAO {

	void addCity(City city);

	City findById(Integer id);

	List<City> getAllCities();

	void updateById(Integer id, City city);

	void deleteById(Integer id);
}
