package manufacture.web.datas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.entity.user.User;
import manufacture.ifacade.dataloading.IDataLoading;

@ManagedBean(name="mbDataLoader")
@ApplicationScoped
public class DataLoader {
    
	@ManagedProperty(value="#{dataLoading}") 
	private IDataLoading dataLoading;
	
	private List<City> listCity;
	
	private List<Country> listCountry;
	private int idCountrySelected;
	
	private List<Planet> listPlanet;
	private int idPlanetSelected;
	
    private List<Category> listeCatégories;
    private List<Color> listeCouleurs;
    private List<Material> listeMateriaux;
    private List<Constructor> listeConstructeurs;
    private List<User> listeArtisans;
    private List<SpaceshipRef> listeVaisseaux;
	
	@PostConstruct
	public void init(){
		idCountrySelected = 1;
		idPlanetSelected = 1;
		this.listCity = dataLoading.loadCityByCountry(idCountrySelected);
		this.listCountry = dataLoading.loadCountryByPlanet(idPlanetSelected);
		this.listPlanet = dataLoading.loadPlanet();
		
		this.listeCatégories = dataLoading.getAllCategory();
		this.listeCouleurs = dataLoading.getAllColor();
		this.listeMateriaux = dataLoading.getAllMaterial();
		this.listeConstructeurs = dataLoading.getAllConstructor();
		this.listeArtisans = dataLoading.getAllArtisan();
		this.listeVaisseaux = dataLoading.getAllSpaceShipRef();
	}
	
	public List<City> getListCity() {
		return dataLoading.loadCityByCountry(idCountrySelected);
	}

	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}

	public IDataLoading getDataLoading() {
		return dataLoading;
	}
	
	public void setDataLoading(IDataLoading dataLoading) {
		this.dataLoading = dataLoading;
	}
	
	public List<Country> getListCountry() {
		return dataLoading.loadCountryByPlanet(idPlanetSelected);
	}
	
	public void setListCountry(List<Country> listCountry) {
		this.listCountry = listCountry;
	}
	
	public List<Planet> getListPlanet() {
		return listPlanet;
	}
	
	public void setListPlanet(List<Planet> listPlanet) {
		this.listPlanet = listPlanet;
	}

	public int getIdCountrySelected() {
		return idCountrySelected;
	}

	public void setIdCountrySelected(int idCountrySelected) {
		this.idCountrySelected = idCountrySelected;
	}

	public int getIdPlanetSelected() {
		return idPlanetSelected;
	}

	public void setIdPlanetSelected(int idPlanetSelected) {
		this.idPlanetSelected = idPlanetSelected;
	}

	public List<Category> getListeCatégories() {
		return dataLoading.getAllCategory();
	}

	public void setListeCatégories(List<Category> listeCatégories) {
		this.listeCatégories = listeCatégories;
	}

	public List<Color> getListeCouleurs() {
		return dataLoading.getAllColor();
	}

	public void setListeCouleurs(List<Color> listeCouleurs) {
		this.listeCouleurs = listeCouleurs;
	}

	public List<Material> getListeMateriaux() {
		return dataLoading.getAllMaterial();
	}

	public void setListeMateriaux(List<Material> listeMateriaux) {
		this.listeMateriaux = listeMateriaux;
	}

	public List<Constructor> getListeConstructeurs() {
		return dataLoading.getAllConstructor();
	}

	public void setListeConstructeurs(List<Constructor> listeConstructeurs) {
		this.listeConstructeurs = listeConstructeurs;
	}

	public List<User> getListeArtisans() {
		return dataLoading.getAllArtisan();
	}

	public void setListeArtisans(List<User> listeArtisans) {
		this.listeArtisans = listeArtisans;
	}

	public List<SpaceshipRef> getListeVaisseaux() {
		return dataLoading.getAllSpaceShipRef();
	}

	public void setListeVaisseaux(List<SpaceshipRef> listeVaisseaux) {
		this.listeVaisseaux = listeVaisseaux;
	}
}
