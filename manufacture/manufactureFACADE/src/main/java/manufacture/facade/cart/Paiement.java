package manufacture.facade.cart;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.PaymentType;
import manufacture.ibusiness.cart.IBusinessCart;
import manufacture.ifacade.cart.IPaiement;

@Service
public class Paiement implements IPaiement {
   
    private Logger log = Logger.getLogger(Paiement.class);
    
    private IBusinessCart businessCart;
	
	public Cart processPaiement(Cart cart) {
	    return businessCart.validatePayment(cart);
	}

    public IBusinessCart getBuCart() {
        return businessCart;
    }
    
    @Override
    public List<PaymentType> getAllPaymentType() {
        return businessCart.getAllPaymentType();
    }
    
    @Autowired
    public void setBuCart(IBusinessCart paramBuCart) {
        businessCart = paramBuCart;
    }
}
