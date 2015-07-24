package manufacture.ifacade.cart;

import manufacture.entity.cart.Cart;

public interface IPaiement {
	public Cart processPaiement(Cart cart);
}
