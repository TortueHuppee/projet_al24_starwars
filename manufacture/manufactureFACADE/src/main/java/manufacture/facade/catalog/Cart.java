package manufacture.facade.catalog;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.ibusiness.cart.IBusinessCart;
import manufacture.ibusiness.catalog.IBusinessCatalog;
import manufacture.ifacade.cart.ICartSpecificCustomer;

public class Cart implements ICartSpecificCustomer {

	BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springBusiness.xml");
	IBusinessCart proxyCart = (IBusinessCart) bf.getBean(IBusinessCart.class);
	
	@Override
	public void addProductToCart(CartProduct cartProduct) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProductFromCart(int idCartProduct) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanCart(int idCart) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOptionsProduct(int idCartProduct, Product newProduct) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateQuantityProduct(int idCartProduct, int newQuantity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> getAllProductByCart(int idCart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotalPrice(int idCart) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PaymentType> getAllPaymentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Delivery> getAllDeliveryType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void orderCommande(int idCart) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createNewCart(int idUser) {
		// TODO Auto-generated method stub

	}

	@Autowired
	public void setProxyCart(IBusinessCart proxyCart) {
		this.proxyCart = proxyCart;
	}

	@Override
	public void deleteProductFromCart(CartProduct cartProduct) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
