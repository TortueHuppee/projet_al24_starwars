package manufacture.ifacade.cart;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.PaymentType;

public interface IPaiement {
	
    List<PaymentType> getAllPaymentType();
    
    Cart processPaiement(Cart cart);
}
