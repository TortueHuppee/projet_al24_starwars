package manufacture.facade.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.cart.Cart;
import manufacture.ibusiness.cart.IBusinessCart;
import manufacture.ifacade.cart.IPaiement;

@Service
public class Paiement implements IPaiement {
	@Autowired
	private IBusinessCart buCart;
	
	public Cart processPaiement(Cart cart) {
		return buCart.validatePayment(cart);
	}
}
