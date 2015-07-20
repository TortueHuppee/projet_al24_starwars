package manufacture.idao.user;

import java.util.List;

import manufacture.entity.user.Administrator;
import manufacture.entity.user.Address;
import manufacture.entity.user.Artisan;
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.entity.user.ProfessionnalCustomer;
import manufacture.entity.user.SpecificCustomer;
import manufacture.entity.user.User;


public interface IDaoAdress {

	void createAdress(Address adress);
	
	List<Planet> getAllPlanet();
	
	List<Country> getAllCountryByPlanet(int idPlanet);
	
	List<City> getAllCityByCountry(int idCountry);
}
