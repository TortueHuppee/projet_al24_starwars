package manufacture.dao.product;

import java.util.List;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.SpaceshipRef;

public interface IDaoConstructor {

	//Méthodes
	List<Constructor> getAllConstructor();
	
	//Gestion de base de données
	void addConstructor(Constructor constructor);
		
	void deleteConstructor(Constructor constructor);
		
	void updateConstructor(Constructor constructor);
}
