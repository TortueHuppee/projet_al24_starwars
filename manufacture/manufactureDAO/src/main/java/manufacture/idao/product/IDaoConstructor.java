package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.Constructor;

public interface IDaoConstructor {

	//Méthodes
	List<Constructor> getAllConstructor();
	
	//Gestion de base de données
	void addConstructor(Constructor constructor);
		
	void deleteConstructor(Constructor constructor);
		
	void updateConstructor(Constructor constructor);
}
