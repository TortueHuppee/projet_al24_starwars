package manufacture.facade.cart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.cart.Cart;
import manufacture.ibusiness.cart.IBusinessCart;
import manufacture.ifacade.cart.IPaiement;

@Service
public class Paiement implements IPaiement {
   
    private Logger log = Logger.getLogger(Paiement.class);
    
	IBusinessCart buCart;
	
	public Cart processPaiement(Cart cart) {
	    return buCart.validatePayment(cart);
	}

    public IBusinessCart getBuCart() {
        return buCart;
    }
    
    @Autowired
    public void setBuCart(IBusinessCart paramBuCart) {
        buCart = paramBuCart;
    }

}
