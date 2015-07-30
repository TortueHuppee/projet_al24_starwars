package manufacture.facade.cart;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.facade.util.ClassPathLoader;
import manufacture.ibusiness.cart.IBusinessCart;
import manufacture.ifacade.cart.ICartSpecificCustomer;

@Service
public class CartSpecificCustomer implements ICartSpecificCustomer {

	IBusinessCart proxyCart;

	@Override
	public void updateOptionsProduct(int idCartProduct, Product newProduct) {
		proxyCart.updateOptionsProduct(idCartProduct, newProduct);
	}

	@Override
	public void updateQuantityProduct(int idCartProduct, int newQuantity) {
		proxyCart.updateQuantityProduct(idCartProduct, newQuantity);
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
	public double getSubTotalPrice(int idCartProduct) {
		return proxyCart.getSubTotalPrice(idCartProduct);
	}

	@Override
	public List<PaymentType> getAllPaymentType() {
		return proxyCart.getAllPaymentType();
	}

	@Override
	public List<Delivery> getAllDeliveryType() {
		return proxyCart.getAllDeliveryType();
	}

	@Autowired
	public void setProxyCart(IBusinessCart proxyCart) {
		this.proxyCart = proxyCart;
	}

}
