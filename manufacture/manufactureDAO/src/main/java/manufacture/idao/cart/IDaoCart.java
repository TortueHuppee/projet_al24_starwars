package manufacture.idao.cart;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.user.User;

public interface IDaoCart {

	//Gestion de la commande
	
	Cart validatePayment(Cart cart); // change boolean to true
	
	void sendRecall(int idUser); // increment nb recall
	
	List<CartProduct> getAllCartProductByCart(int idCart);
	
	Cart getCartByIdCart(int idCart);
	
	void updateCart(Cart cart);

    void addCart(Cart cart);

    //Profil utilisateur
	List<Cart> getCartByUser(User user);

	
}
