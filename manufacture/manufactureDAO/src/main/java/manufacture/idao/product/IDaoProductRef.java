package manufacture.idao.product;

import java.util.List;

import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;

public interface IDaoProductRef {

	//Methodes
	List<ProductRef> getAllProductRef();
	
	List<ProductRef> getAllConstructorProductRef();
	
	List<ProductRef> getAllUsedProductRef();
	
	List<ProductRef> getAllArtisanProductRef();
	
	List<ProductRef> getProductRefByName(String name);
	
	List<ProductRef> getProductRefBySpaceShip(SpaceshipRef spaceShipRef);
	
	//Gestion de base de donnees
	void addProductRef(ProductRef productRef);
	
	void deleteProductRef(ProductRef productRef);
	
	void updateProductRef(ProductRef productRef);

	void addSpaceShipProduct(SpaceshipProduct spaceShipProduct);

	List<SpaceshipProduct> getSpaceShipProductByProduct(ProductRef productRef);

	ProductRef getProductRefByIdProduct(int idProduct);
	
	ProductRef getProductRefByIdProductRef(int idProductRef);

    List<ProductRef> getProductRefByCategory(int paramIdCategorieSelected);

    List<ProductRef> getProductRefBySpaceShipAndName(SpaceshipRef spaceShipRef, String name);
	
}
