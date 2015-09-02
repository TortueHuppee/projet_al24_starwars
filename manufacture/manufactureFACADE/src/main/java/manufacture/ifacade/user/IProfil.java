package manufacture.ifacade.user;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.Product;
import manufacture.entity.user.Address;
import manufacture.entity.user.User;

public interface IProfil {

	//Identit�
	User editUser(User user);
	
	//Adresses
	List<Address> getAllAdressByUser(User user);

	void editAddress(Address addresse);

	void saveAddress(Address nouvelleAdresse);
	
	List<Address> getBillingAddressByUser(User user);

	List<Address> getDeliveryAddressByUser(User user);
	
	//Ventes
	List<Product> getProductSendByUser(User user);
	
	List<CartProduct> getCartSendByUser(User user);
	
	//Commandes passees
	List<Cart> getCartByUser(User user);

	List<Product> getProductNotSendByUser(User user);

    List<CartProduct> getCartProductByCart(Cart paramCart);
}
