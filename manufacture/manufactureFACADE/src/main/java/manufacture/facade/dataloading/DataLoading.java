package manufacture.facade.dataloading;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.ProductRef;
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.ibusiness.dataloading.IBusinessDataLoading;
import manufacture.ifacade.dataloading.IDataLoading;
@Service
public class DataLoading implements IDataLoading {
	
    @Autowired
	private IBusinessDataLoading businessDataLoading;
	
    public List<City> loadCities(){
		return businessDataLoading.loadCity();
	}
    
    public List<Country> loadCountries(){
		return businessDataLoading.loadCountry();
	}
    
    public List<Planet> loadPlanetes(){
		return businessDataLoading.loadPlanet();
	}

	@Override
	public List<City> loadCityByCountry(int idCountry) {
		return businessDataLoading.loadCityByCountry(idCountry);
	}

	@Override
	public List<Country> loadCountry() {
		return businessDataLoading.loadCountry();
	}

	@Override
	public List<Country> loadCountryByPlanet(int idPlanet) {
		return businessDataLoading.loadCountryByPlanet(idPlanet);
	}

	@Override
	public List<Planet> loadPlanet() {
		return businessDataLoading.loadPlanet();
	}
}
