package manufacture.idao.cart;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.entity.user.User;

public interface IDaoCart {

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
	
}
