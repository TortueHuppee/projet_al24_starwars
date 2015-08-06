package manufacture.business.dataloading;

import java.util.List;

import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.ibusiness.dataloading.IBusinessDataLoading;
import manufacture.idao.dataloading.IDaoCity;
import manufacture.idao.dataloading.IDaoCountry;
import manufacture.idao.dataloading.IDaoPlanet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BusinessDataLoading implements IBusinessDataLoading {

    @Autowired
    private IDaoCity daoCity;
    
    @Autowired
    private IDaoCountry daoCountry;
    
    @Autowired
    private IDaoPlanet daoPlanet;
    
    @Override
    public List<City> loadCity(){
        return daoCity.getAllCities();
    }

	@Override
	public List<Country> loadCountry() {
		return daoCountry.getAllCountries();
	}

	@Override
	public List<Planet> loadPlanet() {
		return daoPlanet.getAllPlanet();
	}

	@Override
	public List<City> loadCityByCountry(int idCountry) {
		return daoCity.getAllCityByCountry(idCountry);
	}

	@Override
	public List<Country> loadCountryByPlanet(int idPlanet) {
		return daoCountry.getAllCountryByPlanet(idPlanet);
	}
}
