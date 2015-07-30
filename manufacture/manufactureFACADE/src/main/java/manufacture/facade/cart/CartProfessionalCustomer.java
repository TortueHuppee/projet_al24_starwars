package manufacture.facade.cart;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.Product;
import manufacture.facade.util.ClassPathLoader;
import manufacture.ibusiness.cart.IBusinessCart;
import manufacture.ifacade.cart.ICartProfessionalCustomer;

@Service
public class CartProfessionalCustomer implements ICartProfessionalCustomer {
	
	IBusinessCart proxyCart;

	@Override
	public List<Product> getAllProductByCart(int idCart) {
		return proxyCart.getAllProductByCart(idCart);
	}

	@Override
	public double getTotalPrice(int idCart) {
		return proxyCart.getTotalPrice(idCart);
	}

	@Override
	public void validatePayment(Cart cart) {
		proxyCart.validatePayment(cart);
	}

	@Override
	public void sendRecall(int idUser) {
		proxyCart.sendRecall(idUser);
	}

	@Autowired
	public void setProxyCart(IBusinessCart proxyCart) {
		this.proxyCart = proxyCart;
	}
}
