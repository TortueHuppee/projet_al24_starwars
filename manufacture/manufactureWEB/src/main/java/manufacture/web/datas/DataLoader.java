package manufacture.web.datas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.ifacade.dataloading.IDataLoading;

@ManagedBean(name="mbDataLoader")
@SessionScoped
public class DataLoader {
    
	@ManagedProperty(value="#{dataLoading}") 
	private IDataLoading dataLoading;
	
	private List<City> listCity;
	
	private List<Country> listCountry;
	
	private List<Planet> listPlanet;
	
	@PostConstruct
	public void init(){
		this.listCity = dataLoading.loadCities();
		this.listCountry = dataLoading.loadCountry();
		this.listPlanet = dataLoading.loadPlanet();
	}
	public List<City> getListCity() {
		return dataLoading.loadCities();
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
		return listCountry;
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
}
