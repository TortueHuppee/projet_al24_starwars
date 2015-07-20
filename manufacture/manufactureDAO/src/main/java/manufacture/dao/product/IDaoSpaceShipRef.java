package manufacture.dao.product;

import java.util.List;

import manufacture.entity.product.Category;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipRef;

public interface IDaoSpaceShipRef {

	//Méthodes
	List<SpaceshipRef> getAllSpaceshipRef();
		
	//Gestion de base de données
	void addSpaceShipRef(SpaceshipRef spaceShipRef);
	
	void deleteSpaceShipRef(SpaceshipRef spaceShipRef);
	
	void updateSpaceShipRef(SpaceshipRef spaceShipRef);
	
}
