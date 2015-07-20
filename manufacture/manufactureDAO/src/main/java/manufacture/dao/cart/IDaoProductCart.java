package manufacture.dao.cart;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.entity.user.User;

public interface IDaoProductCart {

	//Gestion du panier
	void addProductToCart(CartProduct cartProduct);
	
	void deleteProductFromCart(int idCartProduct);
	
	void cleanCart(int idCart);
	
	void updateOptionsProduct(int idCartProduct, Product newProduct);
	
	void updateQuantityProduct(int idCartProduct, int newQuantity);
	
	List<Product> getAllProductByCart(int idCart);
	
	List<Product> getAllArtisanProductByCart(int idCart);
	
	List<Product> getAllUsedProductByCart(int idCart);
	
	List<Product> getAllConstructorProductByCart(int idCart);
	
}
