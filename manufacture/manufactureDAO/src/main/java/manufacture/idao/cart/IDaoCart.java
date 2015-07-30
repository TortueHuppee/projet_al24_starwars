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
//	double getTotalPrice(int idCart);
	
	Cart validatePayment(Cart cart); // change boolean to true
	
	int createNewCart(int idUser);  // if user connected
	
	void sendRecall(int idUser); // increment nb recall
	
	List<CartProduct> getAllCartProductByCart(int idCart);
	
	Cart getCartByIdCart(int idCart);
	
	void updateCart(Cart cart);

    void addCart(Cart cart);
	
}
