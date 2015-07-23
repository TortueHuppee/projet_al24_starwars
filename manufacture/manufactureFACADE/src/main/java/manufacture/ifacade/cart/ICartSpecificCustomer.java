package manufacture.ifacade.cart;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.entity.user.User;

public interface ICartSpecificCustomer {

	//Gestion du panier
	void addProductToCart(CartProduct cartProduct);
	
	void deleteProductFromCart(CartProduct cartProduct);
	
	void cleanCart(int idCart);
	
	void updateOptionsProduct(int idCartProduct, Product newProduct);
	
	void updateQuantityProduct(int idCartProduct, int newQuantity);
	
	List<Product> getAllProductByCart(int idCart);
	
	//Gestion de la commande
	double getTotalPrice(int idCart);
	
	List<PaymentType> getAllPaymentType();
	
	List<Delivery> getAllDeliveryType();
	
	//Validation du panier = passer la commande
	void orderCommande(int idCart);
	
	void createNewCart(int idUser);

	void deleteProductFromCart(int idCartProduct);
	
}
