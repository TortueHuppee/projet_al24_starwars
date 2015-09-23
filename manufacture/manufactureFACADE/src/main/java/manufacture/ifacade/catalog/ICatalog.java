package manufacture.ifacade.catalog;

import java.util.List;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.user.User;

public interface ICatalog {

	//Consultation du catalogue
	List<ProductRef> getAllProductRef();
	
	List<ProductRef> getAllConstructorProductRef();
	
	List<ProductRef> getAllUsedProductRef();
	
	List<ProductRef> getAllArtisanProductRef();
	
	List<ProductRef> getProductRefByName(String name);
	
	List<ProductRef> getProductRefBySpaceShip(SpaceshipRef spaceShipRef);
	
	List<Product> getAllProduct();
	
	List<Category> getAllCategory();
	
	List<Color> getAllColor();
	
	List<SpaceshipRef> getAllSpaceShipRef();
	
	List<Material> getAllMaterial();
	
	List<Constructor> getAllConstructor();
	
	List<User> getAllArtisan();

	List<SpaceshipProduct> getAllSpaceShipProduct();
	
	List<Product> getAllProductBySpaceShipRefAndName(int idSpaceShipSelected, String name);
	
	List<ProductRef> getProductRefBySpaceShipAndName(SpaceshipRef spaceShipRef, String name);
	
	List<Product> getAllProductByName(String name);
	
	//Consultation du detail d'un produit
	List<Product> getAllProductByProductRef(int idProducRef);

	List<SpaceshipProduct> getSpaceShipProductByProduct(ProductRef productRef);

	ProductRef getProductRefByIdProduct(int idProduct);
	
	ProductRef getProductRefByIdProductRef(int idProductRef);

    List<ProductRef> getProductRefByCategory(int paramIdCategorieSelected);
    
    List<Product> getAllProductConstructorByProductRef(Integer idProductRef);
}
