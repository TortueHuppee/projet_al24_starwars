package manufacture.dao.product;

import java.util.List;

import javax.transaction.Transactional;

import manufacture.entity.product.Color;

import org.springframework.stereotype.Service;

public interface IDaoColor {

	//Méthodes
	List<Color> getAllColor();
	
	//Gestion de base de données
	void addColor(Color color);
		
	void deleteColor(Color color);
		
	void updateColor(Color color);
}
