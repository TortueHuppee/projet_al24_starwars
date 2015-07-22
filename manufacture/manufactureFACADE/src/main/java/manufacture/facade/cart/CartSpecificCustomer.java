package manufacture.facade.cart;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.facade.util.ClassPathLoader;
import manufacture.ibusiness.cart.IBusinessCart;
import manufacture.ifacade.cart.ICartSpecificCustomer;

@Service
public class CartSpecificCustomer implements ICartSpecificCustomer {

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
	public void cleanCart(int idCart) {
		proxyCart.cleanCart(idCart);
	}

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
	public List<PaymentType> getAllPaymentType() {
		return proxyCart.getAllPaymentType();
	}

	@Override
	public List<Delivery> getAllDeliveryType() {
		return proxyCart.getAllDeliveryType();
	}

	@Override
	public void orderCommande(int idCart) {
		proxyCart.orderSpecificCommande(idCart);
	}

	@Override
	public void createNewCart(int idUser) {
		proxyCart.createNewCart(idUser);
	}

	@Autowired
	public void setProxyCart(IBusinessCart proxyCart) {
		this.proxyCart = proxyCart;
	}

}
