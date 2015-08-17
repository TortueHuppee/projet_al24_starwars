package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.Category;

public interface IDaoCategory {

	//M�thodes
	List<Category> getAllCategory();
	
	//Gestion de base de donn�es
	void addCategory(Category category);
		
	void deleteCategory(Category category);
		
	void updateCategory(Category category);
}
