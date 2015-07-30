package manufacture.ifacade.cart;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.product.Product;

public interface IGestionCart {
    
    List<Product> getAllProductByCart(int idCart);

    double getTotalPrice(int idCart);
    
    void validateCommande(Cart cart);
    
    void validatePayment(Cart cart);
}
