package manufacture.ifacade.cart;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.RelayPoint;
import manufacture.entity.product.Product;

public interface IGestionCart {
    
    List<Product> getAllProductByCart(int idCart);
    
    List<Delivery> getAllDelivery();

    double getTotalPrice(int idCart);
    
    void validateCommande(Cart cart);
    
    void validatePayment(Cart cart);

	List<RelayPoint> getAllRelayPoints();
}
