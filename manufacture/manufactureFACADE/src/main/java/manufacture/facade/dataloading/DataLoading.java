package manufacture.facade.dataloading;

import java.util.List;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.entity.user.User;
import manufacture.ibusiness.catalog.IBusinessCatalog;
import manufacture.ibusiness.dataloading.IBusinessDataLoading;
import manufacture.ifacade.dataloading.IDataLoading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DataLoading implements IDataLoading {
	
    @Autowired
	private IBusinessDataLoading businessDataLoading;
    
    @Autowired
	private IBusinessCatalog proxyCatalog;

    @Override
    public List<City> loadCities(){
		return businessDataLoading.loadCity();
	}
    
    @Override
	public List<Category> getAllCategory() {
		return proxyCatalog.getAllCategory();
	}

	@Override
	public List<Color> getAllColor() {
		return proxyCatalog.getAllColor();
	}

	@Override
	public List<SpaceshipRef> getAllSpaceShipRef() {
		return proxyCatalog.getAllSpaceShipRef();
	}

	@Override
	public List<Material> getAllMaterial() {
		return proxyCatalog.getAllMaterial();
	}

	@Override
	public List<Constructor> getAllConstructor() {
		return proxyCatalog.getAllConstructor();
	}
	
	@Override
	public List<User> getAllArtisan() {
		return proxyCatalog.getAllArtisan();
	}
    
	@Override
	public List<ProductRef> getAllProductRef() {
		return proxyCatalog.getAllProductRef();
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
	public List<SpaceshipProduct> getAllSpaceShipProduct() {
		return proxyCatalog.getAllSpaceShipProduct();
	}

	@Override
	public List<Planet> loadPlanet() {
		return businessDataLoading.loadPlanet();
	}
	
	public void setProxyCatalog(IBusinessCatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}
}
