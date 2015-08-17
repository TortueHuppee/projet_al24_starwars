package manufacture.web.datas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

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
		listCity = dataLoading.loadCityByCountry(idCountrySelected);
		listCountry = dataLoading.loadCountryByPlanet(idPlanetSelected);
		listPlanet = dataLoading.loadPlanet();
		
		listeCatégories = dataLoading.getAllCategory();
		listeCouleurs = dataLoading.getAllColor();
		listeMateriaux = dataLoading.getAllMaterial();
		listeConstructeurs = dataLoading.getAllConstructor();
		listeArtisans = dataLoading.getAllArtisan();
		listeVaisseaux = dataLoading.getAllSpaceShipRef();
	}

    public IDataLoading getDataLoading() {
        return dataLoading;
    }

    public void setDataLoading(IDataLoading paramDataLoading) {
        dataLoading = paramDataLoading;
    }

    public List<City> getListCity() {
        return listCity;
    }

    public void setListCity(List<City> paramListCity) {
        listCity = paramListCity;
    }

    public List<Country> getListCountry() {
        return listCountry;
    }

    public void setListCountry(List<Country> paramListCountry) {
        listCountry = paramListCountry;
    }

    public int getIdCountrySelected() {
        return idCountrySelected;
    }

    public void setIdCountrySelected(int paramIdCountrySelected) {
        idCountrySelected = paramIdCountrySelected;
    }

    public List<Planet> getListPlanet() {
        return listPlanet;
    }

    public void setListPlanet(List<Planet> paramListPlanet) {
        listPlanet = paramListPlanet;
    }

    public int getIdPlanetSelected() {
        return idPlanetSelected;
    }

    public void setIdPlanetSelected(int paramIdPlanetSelected) {
        idPlanetSelected = paramIdPlanetSelected;
    }

    public List<Category> getListeCatégories() {
        return listeCatégories;
    }

    public void setListeCatégories(List<Category> paramListeCatégories) {
        listeCatégories = paramListeCatégories;
    }

    public List<Color> getListeCouleurs() {
        return listeCouleurs;
    }

    public void setListeCouleurs(List<Color> paramListeCouleurs) {
        listeCouleurs = paramListeCouleurs;
    }

    public List<Material> getListeMateriaux() {
        return listeMateriaux;
    }

    public void setListeMateriaux(List<Material> paramListeMateriaux) {
        listeMateriaux = paramListeMateriaux;
    }

    public List<Constructor> getListeConstructeurs() {
        return listeConstructeurs;
    }

    public void setListeConstructeurs(List<Constructor> paramListeConstructeurs) {
        listeConstructeurs = paramListeConstructeurs;
    }

    public List<User> getListeArtisans() {
        return listeArtisans;
    }

    public void setListeArtisans(List<User> paramListeArtisans) {
        listeArtisans = paramListeArtisans;
    }

    public List<SpaceshipRef> getListeVaisseaux() {
        return listeVaisseaux;
    }

    public void setListeVaisseaux(List<SpaceshipRef> paramListeVaisseaux) {
        listeVaisseaux = paramListeVaisseaux;
    }
}
