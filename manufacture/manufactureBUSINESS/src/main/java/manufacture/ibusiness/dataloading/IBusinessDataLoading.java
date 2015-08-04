package manufacture.ibusiness.dataloading;

import java.util.List;

import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.ProductRef;
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;

public interface IBusinessDataLoading {
	
    //Adresse
    List<City> loadCity();
    
    List<City> loadCityByCountry(int idCountry);

	List<Country> loadCountry();
	
	List<Country> loadCountryByPlanet(int idPlanet);

	List<Planet> loadPlanet();
}
