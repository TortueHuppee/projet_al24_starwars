package manufacture.ifacade.dataloading;

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

public interface IDataLoading {

    //Adresse
    List<City> loadCities();

    List<City> loadCityByCountry(int idCountry);

	List<Country> loadCountry();
	
	List<Country> loadCountryByPlanet(int idPlanet);
	
	List<Planet> loadPlanet();
	
	//Produits - Annonces
	List<ProductRef> getAllProductRef();

	List<Category> getAllCategory();
	
	List<Color> getAllColor();
	
	List<SpaceshipRef> getAllSpaceShipRef();
	
	List<Material> getAllMaterial();
	
	List<Constructor> getAllConstructor();
	
	List<User> getAllArtisan();

	List<SpaceshipProduct> getAllSpaceShipProduct();
}
