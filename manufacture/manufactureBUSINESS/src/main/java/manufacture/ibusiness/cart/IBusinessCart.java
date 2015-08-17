package manufacture.ibusiness.cart;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.cart.RelayPoint;
import manufacture.entity.product.Product;

public interface IBusinessCart {

	//Gestion du panier
	List<Product> getAllProductByCart(int idCart);
	
	//Gestion de la commande
	double getTotalPrice(int idCart);
	
	double getSubTotalPrice(int idCartProduct);
	
	void orderProfessionalCommande(Cart cart);
	
	void orderSpecificCommande(Cart cart);
	
	Cart validatePayment(Cart cart);
	
//	void createNewCart(int idUser);
	
	void sendRecall(int idUser);
	
	//Autres
	List<PaymentType> getAllPaymentType();
	
	List<Delivery> getAllDeliveryType();
	
	void checkProductStock(int idProduct);

	List<RelayPoint> getAllRelayPoints();

}
