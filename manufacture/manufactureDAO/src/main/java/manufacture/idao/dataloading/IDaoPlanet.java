package manufacture.idao.dataloading;

import java.util.List;

import manufacture.entity.user.Planet;


public interface IDaoPlanet {

	void addPlanet(Planet planet);
	
	List<Planet> getAllPlanet();

}
