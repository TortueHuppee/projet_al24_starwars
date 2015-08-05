package manufacture.ibusiness.user;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.product.Product;
import manufacture.entity.user.Address;
import manufacture.entity.user.User;


public interface IBusinessProfil {

	//Identité
	User editUser(User user);

	//Adresses
	List<Address> getAllAdressByUser(User user);

	void editAddress(Address addresse);

	void saveAddress(Address nouvelleAdresse);

	//Ventes
	List<Product> getProductSendByUser(User user);
	
	//Commandes passées
	List<Cart> getCartByUser(User user);
}	
