package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.SpaceshipRef;

public interface IDaoMaterial {

	//M�thodes
	List<Material> getAllMaterial();
	
	//Gestion de base de donn�es
	void addMaterial(Material material);
		
	void deleteMaterial(Material material);
		
	void updateMaterial(Material material);
}
