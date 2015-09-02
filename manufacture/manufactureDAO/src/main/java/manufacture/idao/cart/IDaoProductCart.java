package manufacture.idao.cart;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.Product;
import manufacture.entity.user.User;

public interface IDaoProductCart {

	//Gestion du panier
	void addProductToCart(CartProduct cartProduct);
	
	void deleteProductFromCart(CartProduct cartProduct);
	
	List<Product> getAllProductByCart(int idCart);
	
	List<Product> getAllArtisanProductByCart(int idCart);
	
	List<Product> getAllUsedProductByCart(int idCart);
	
	List<Product> getAllConstructorProductByCart(int idCart);
	
	double getSubTotalPrice(int idCartProduct);
	
	//Profil utilisateur
	List<CartProduct> getCartSendByUser(User user);

    List<CartProduct> getCartProductByCart(Cart paramCart);
}
