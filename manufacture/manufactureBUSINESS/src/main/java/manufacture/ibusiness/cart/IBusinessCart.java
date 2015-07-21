package manufacture.ibusiness.cart;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.entity.user.User;

public interface IBusinessCart {

	//Gestion du panier
	void addProductToCart(CartProduct cartProduct);
	
	void deleteProductFromCart(int idCartProduct);
	
	void cleanCart(int idCart);
	
	void updateOptionsProduct(int idCartProduct, Product newProduct);
	
	void updateQuantityProduct(int idCartProduct, int newQuantity);
	
	List<Product> getAllProductByCart(int idCart);
	
	//Gestion de la commande
	double getTotalPrice(int idCart);
	
	void orderProfessionalCommande(int idCart);
	
	void orderSpecificCommande(int idCart);
	
	void validatePayment(int idCart);
	
	void createNewCart(int idUser);
	
	void sendRecall(int idUser);
	
	//Autres
	List<PaymentType> getAllPaymentType();
	
	List<Delivery> getAllDeliveryType();
	
	void updateProductStock(int idProduct, int quantitySend);
	
	void checkProductStock(int idProduct);
	
}
