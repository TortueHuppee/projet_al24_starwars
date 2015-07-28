package manufacture.idao.dataloading;

import java.util.List;

import manufacture.entity.user.City;

public interface IDaoCity {
	
	List<City> getAllCities();
	
	void addCity(City city);
	
	List<City> getAllCityByCountry(int idCountry);
}
