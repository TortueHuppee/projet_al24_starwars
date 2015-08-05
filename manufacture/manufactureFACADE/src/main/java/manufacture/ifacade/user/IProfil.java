package manufacture.ifacade.user;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.Product;
import manufacture.entity.user.Address;
import manufacture.entity.user.User;

public interface IProfil {

	//Identité
	User editUser(User user);
	
	//Adresses
	List<Address> getAllAdressByUser(User user);

	void editAddress(Address addresse);

	void saveAddress(Address nouvelleAdresse);
	
	//Ventes
	List<Product> getProductSendByUser(User user);
	
	List<CartProduct> getCartSendByUser(User user);
	
	//Commandes passées
	List<Cart> getCartByUser(User user);

	
}
