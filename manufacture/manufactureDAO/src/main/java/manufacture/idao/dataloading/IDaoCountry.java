package manufacture.idao.dataloading;

import java.util.List;

import manufacture.entity.user.Country;


public interface IDaoCountry {
	
	void addCountry(Country country);

	List<Country> getAllCountries();

	List<Country> getAllCountryByPlanet(int idPlanet);

}
