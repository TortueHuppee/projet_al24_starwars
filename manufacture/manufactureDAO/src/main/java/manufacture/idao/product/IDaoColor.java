package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.Color;

public interface IDaoColor {

	//M�thodes
	List<Color> getAllColor();
	
	//Gestion de base de donn�es
	void addColor(Color color);
		
	void deleteColor(Color color);
		
	void updateColor(Color color);
}
