package manufacture.web.datas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

import manufacture.entity.user.City;
import manufacture.ifacade.dataloading.IDataLoading;

@ManagedBean(name="mbDataLoader")
@SessionScoped
public class DataLoader {
	@ManagedProperty(value="#{dataLoading}") 
	private IDataLoading dataLoading;
	
	private List<City> listCity;
	@PostConstruct
	public void init(){
		this.listCity = dataLoading.loadCities();
	}
	public List<City> getListCity() {
		return listCity;
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
}
