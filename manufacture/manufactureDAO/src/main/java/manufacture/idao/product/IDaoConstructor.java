package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.Constructor;

public interface IDaoConstructor {

	//M�thodes
	List<Constructor> getAllConstructor();
	
	//Gestion de base de donn�es
	void addConstructor(Constructor constructor);
		
	void deleteConstructor(Constructor constructor);
		
	void updateConstructor(Constructor constructor);
}
