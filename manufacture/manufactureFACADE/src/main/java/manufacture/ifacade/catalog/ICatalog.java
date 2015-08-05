package manufacture.ifacade.catalog;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.ArtisanProduct;
import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.product.UsedProduct;
import manufacture.entity.user.User;

public interface ICatalog {

	//Consultation du catalogue
	List<ProductRef> getAllProductRef();
	
	List<ProductRef> getAllConstructorProductRef();
	
	List<ProductRef> getAllUsedProductRef();
	
	List<ProductRef> getAllArtisanProductRef();
	
	List<ProductRef> getProductRefByName(String name);
	
	List<ProductRef> getConstructorProductRefBySpaceShip(SpaceshipRef spaceShipRef);
	
	List<Product> getAllProduct();
	
	List<Category> getAllCategory();
	
	List<Color> getAllColor();
	
	List<SpaceshipRef> getAllSpaceShipRef();
	
	List<Material> getAllMaterial();
	
	List<Constructor> getAllConstructor();

	List<SpaceshipProduct> getAllSpaceShipProduct();
	
	//Consultation du détail d'un produit
	List<ConstructorProduct> getAllProductByProductRef(int idProducRef);

	List<ConstructorProduct> getAllConstructorProduct();
	
	List<ArtisanProduct> getAllArtisanProduct();
	
	List<UsedProduct> getAllUsedProduct();

	List<SpaceshipProduct> getSpaceShipProductByProduct(ProductRef productRef);

	ProductRef getProductRefById(int idProductRef);

}
