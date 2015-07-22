package manufacture.business.cart;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.business.util.ClassPathLoader;
import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.ibusiness.cart.IBusinessCart;
import manufacture.idao.cart.IDaoCart;
import manufacture.idao.cart.IDaoPaymentAndDelivery;
import manufacture.idao.cart.IDaoProductCart;
import manufacture.idao.product.IDaoProduct;

@Service
public class BusinessCart implements IBusinessCart {
	
//	BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springBusiness.xml");
	BeanFactory bf = ClassPathLoader.getDaoBeanFactory();
		
	IDaoCart proxyCart = (IDaoCart) bf.getBean(IDaoCart.class);
	IDaoProductCart proxyProductCart = (IDaoProductCart) bf.getBean(IDaoProductCart.class);
	IDaoPaymentAndDelivery proxyPaymentAndDelivery = (IDaoPaymentAndDelivery) bf.getBean(IDaoPaymentAndDelivery.class);
	IDaoProduct proxyProduct = (IDaoProduct) bf.getBean(IDaoProduct.class);

	@Override
	public void addProductToCart(CartProduct cartProduct) {
		proxyProductCart.addProductToCart(cartProduct);
	}

	@Override
	public void deleteProductFromCart(CartProduct cartProduct) {
		proxyProductCart.deleteProductFromCart(cartProduct);
	}

	@Override
	public void cleanCart(int idCart) {
		List<CartProduct> listCartProduct = proxyCart.getAllCartProductByCart(idCart);
		for (CartProduct cartProduct : listCartProduct) {
			proxyProductCart.deleteProductFromCart(cartProduct);
		}
	}

	@Override
	public void updateOptionsProduct(int idCartProduct, Product newProduct) {
		proxyProductCart.updateOptionsProduct(idCartProduct, newProduct);
	}

	@Override
	public void updateQuantityProduct(int idCartProduct, int newQuantity) {
		proxyProductCart.updateQuantityProduct(idCartProduct, newQuantity);
	}

	@Override
	public List<Product> getAllProductByCart(int idCart) {
		return proxyProductCart.getAllProductByCart(idCart);
	}

	@Override
	public double getTotalPrice(int idCart) {
		List<CartProduct> listCartProduct = proxyCart.getAllCartProductByCart(idCart);
		double total = 0;
		for (CartProduct cartProduct : listCartProduct) {
			total += proxyProductCart.getSubTotalPrice(cartProduct.getIdCartProduct());
		}
		return total;
	}

	@Override
	public void orderProfessionalCommande(int idCart) {
		//dateCommande, isValidated
		Cart commande = proxyCart.getCartByIdCart(idCart);
		commande.setDateCommande(new Date());
		commande.setIsValidated((byte) 1);
		proxyCart.updateCart(commande);
	}

	@Override
	public void orderSpecificCommande(int idCart) {
		// valider le payment
		//dateCommande, isValidated
		Cart commande = proxyCart.getCartByIdCart(idCart);
		commande.setDateCommande(new Date());
		commande.setIsValidated((byte) 1);
		proxyCart.updateCart(commande);
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

	@Override
	public List<PaymentType> getAllPaymentType() {
		return proxyPaymentAndDelivery.getAllPaymentType();
	}

	@Override
	public List<Delivery> getAllDeliveryType() {
		return proxyPaymentAndDelivery.getAllDeliveryType();
	}

	@Override
	public void updateProductStock(int idProduct, int quantitySend) {
		proxyProduct.updateProductStock(idProduct, quantitySend);
	}

	@Override
	public void checkProductStock(int idProduct) {
		proxyProduct.checkProductStock(idProduct);
	}

	public IDaoCart getProxyCart() {
		return proxyCart;
	}

	@Autowired
	public void setProxyCart(IDaoCart proxyCart) {
		this.proxyCart = proxyCart;
	}

	public IDaoProductCart getProxyProductCart() {
		return proxyProductCart;
	}
	
	@Autowired
	public void setProxyProductCart(IDaoProductCart proxyProductCart) {
		this.proxyProductCart = proxyProductCart;
	}

	public IDaoPaymentAndDelivery getProxyPaymentAndDelivery() {
		return proxyPaymentAndDelivery;
	}

	@Autowired
	public void setProxyPaymentAndDelivery(
			IDaoPaymentAndDelivery proxyPaymentAndDelivery) {
		this.proxyPaymentAndDelivery = proxyPaymentAndDelivery;
	}

	public IDaoProduct getProxyProduct() {
		return proxyProduct;
	}

	@Autowired
	public void setProxyProduct(IDaoProduct proxyProduct) {
		this.proxyProduct = proxyProduct;
	}
	
}
