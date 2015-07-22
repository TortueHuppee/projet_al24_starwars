package manufacture.facade.cart;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.Product;
import manufacture.facade.util.ClassPathLoader;
import manufacture.ibusiness.cart.IBusinessCart;
import manufacture.ifacade.cart.ICartProfessionalCustomer;

@Service
public class CartProfessionalCustomer implements ICartProfessionalCustomer {
	
	BeanFactory bf = ClassPathLoader.getBusinessBeanFactory();
	IBusinessCart proxyCart = (IBusinessCart) bf.getBean(IBusinessCart.class);

	@Override
	public void addProductToCart(CartProduct cartProduct) {
		proxyCart.addProductToCart(cartProduct);
	}

	@Override
	public void deleteProductFromCart(CartProduct cartProduct) {
		proxyCart.deleteProductFromCart(cartProduct);
	}

	@Override
	public List<Product> getAllProductByCart(int idCart) {
		return proxyCart.getAllProductByCart(idCart);
	}

	@Override
	public double getTotalPrice(int idCart) {
		return proxyCart.getTotalPrice(idCart);
	}

	@Override
	public void orderProfessionalCommande(int idCart) {
		proxyCart.orderProfessionalCommande(idCart);
	}

	@Override
	public void validatePayment(int idCart) {
		proxyCart.validatePayment(idCart);
	}

	@Override
	public void createNewCart(int idUser) {
		proxyCart.createNewCart(idUser);
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
