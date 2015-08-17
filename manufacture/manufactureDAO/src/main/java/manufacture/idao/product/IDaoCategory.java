package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.Category;

public interface IDaoCategory {

	//Méthodes
	List<Category> getAllCategory();
	
	//Gestion de base de données
	void addCategory(Category category);
		
	void deleteCategory(Category category);
		
	void updateCategory(Category category);
}
