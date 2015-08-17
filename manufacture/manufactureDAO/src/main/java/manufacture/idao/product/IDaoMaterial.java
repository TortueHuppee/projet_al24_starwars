package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.Material;

public interface IDaoMaterial {

	//M�thodes
	List<Material> getAllMaterial();
	
	//Gestion de base de donn�es
	void addMaterial(Material material);
		
	void deleteMaterial(Material material);
		
	void updateMaterial(Material material);
}
