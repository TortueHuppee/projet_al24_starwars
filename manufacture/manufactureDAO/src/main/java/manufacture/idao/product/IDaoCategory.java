package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.SpaceshipRef;

public interface IDaoCategory {

	//Méthodes
	List<Category> getAllCategory();
	
	//Gestion de base de données
	void addCategory(Category category);
		
	void deleteCategory(Category category);
		
	void updateCategory(Category category);
}
