package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;

public interface IDaoProductRef {

	//Méthodes
	List<ProductRef> getAllProductRef();
	
	List<ProductRef> getAllConstructorProductRef();
	
	List<ProductRef> getAllUsedProductRef();
	
	List<ProductRef> getAllArtisanProductRef();
	
	List<ProductRef> getProductRefByName(String name);
	
	List<ProductRef> getConstructorProductRefBySpaceShip(SpaceshipRef spaceShipRef);
	
	//Gestion de base de données
	void addProductRef(ProductRef productRef);
	
	void deleteProductRef(ProductRef productRef);
	
	void updateProductRef(ProductRef productRef);

	void addSpaceShipProduct(SpaceshipProduct spaceShipProduct);

	List<SpaceshipProduct> getSpaceShipProductByProduct(ProductRef productRef);

	ProductRef getProductRefById(int idProductRef);
	
}
