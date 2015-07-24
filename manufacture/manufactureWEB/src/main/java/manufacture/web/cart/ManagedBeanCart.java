package manufacture.web.cart;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.Product;
import manufacture.entity.user.SpecificCustomer;
import manufacture.entity.user.User;
import manufacture.facade.cart.CartSpecificCustomer;
import manufacture.ifacade.cart.ICartSpecificCustomer;
import manufacture.ifacade.catalog.ICatalog;
//import manufacture.web.catalogBean.ManagedBeanCatalog.Produit;
import manufacture.web.user.UserBean;
import manufacture.web.util.ClassPathLoader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope(value="session")

@ManagedBean(name = "mbCart")
@SessionScoped
public class ManagedBeanCart {

	private Logger log = Logger.getLogger(ManagedBeanCart.class);

	@ManagedProperty(value = "#{cartSpecificCustomer}")
	private ICartSpecificCustomer proxyCart;

	@ManagedProperty(value = "#{catalog}")
	private ICatalog proxyCatalog;
	
//	@ManagedProperty(value = "#{specificUserCart}")
	Cart specificUserCart = new Cart();

	private int idSelectedProduct;
	private Product selectedProduct;
	private int quantity;

	private List<ConstructorProduct> listeProductBrute;

	private int productStock;

	private List<CartProduct> panier = new ArrayList<CartProduct>();

	@PostConstruct
	void init() {
		listeProductBrute = proxyCatalog.getAllConstructorProduct();
	}

	public void addProductToCartBeta() {
		CartProduct cartProduct = new CartProduct();
		Product product = getProductFromLocalListeById(idSelectedProduct);
		int cartProductQuantity = cartProduct.getQuantity();
		boolean isNewProductInCart = true;
		if (quantity > 0) {
			for (CartProduct cp : panier) {
				if (cp.getProduct().getIdProduct() == idSelectedProduct) {
					isNewProductInCart = false;
					cartProductQuantity = cp.getQuantity();
					cartProductQuantity += quantity ;
					if (cartProductQuantity < cp.getProduct().getStock()) {
						cp.setQuantity(cartProductQuantity);
//						proxyCart.updateQuantityProduct(cp.getIdCartProduct(), cartProductQuantity + quantity);
					} else {
						cp.setQuantity(cp.getProduct().getStock());
					}
					break;
				}
			}
			if (isNewProductInCart) {
				cartProduct.setProduct(product);
				cartProduct.setQuantity(quantity);
				panier.add(cartProduct);
				// proxyCart.addProductToCart(cartProduct);
			}
		}
	}

	public void addProductToCart(int idProductToAdd) {
		CartProduct cartProduct = new CartProduct();
		Product productToAdd = getProductFromLocalListeById(idProductToAdd);
		int cartProductQuantity = cartProduct.getQuantity();
		boolean isNewProductInCart = true;
		log.info(" ===<<< ID produit ajouté au panier = "+idProductToAdd+">>>=== ");
		log.info(" ===<<< Nom produit ajouté au panier = "+productToAdd.getProductRef().getProductName()+">>>=== ");
		log.info(" ===<<< Quantité produit ajouté au panier = "+productToAdd.getProductRef().getProductName()+">>>=== ");
		if (quantity > 0) {
			for (CartProduct cp : panier) {
				if (cp.getProduct().getIdProduct() == productToAdd.getIdProduct()) {
					isNewProductInCart = false;
					cartProductQuantity = cp.getQuantity();
					cartProductQuantity += quantity ;
					if (cartProductQuantity < cp.getProduct().getStock()) {
						cp.setQuantity(cartProductQuantity);
//						proxyCart.updateQuantityProduct(cp.getIdCartProduct(), cartProductQuantity + quantity);
					} else {
						cp.setQuantity(cp.getProduct().getStock());
					}
					break;
				}
			}
			if (isNewProductInCart) {
				cartProduct.setProduct(productToAdd);
				cartProduct.setQuantity(quantity);
				panier.add(cartProduct);
				// proxyCart.addProductToCart(cartProduct);
			}
		}
	}

	// public List<CartProduct> getAllCartProducts() {
	// return panier;
	// }

	public int getStockByProductId(int idProduct) {
		int result = 0;
		for (Product product : listeProductBrute) {
			if (product.getIdProduct() == idProduct) {
				result = product.getStock();
				break;
			}
		}
		return result;
	}

	public Product getProductFromLocalListeById(int idProduct) {
		Product result = new Product();
		for (Product product : listeProductBrute) {
			if (product.getIdProduct() == idProduct) {
				result = product;
				break;
			}
		}
		return result;
	}

	public CartProduct getProductFromCartListeById(int idProduct) {
		CartProduct result = new CartProduct();
		for (CartProduct cartProduct : panier) {
			if (cartProduct.getProduct().getIdProduct() == idProduct) {
				result = cartProduct;
				break;
			}
		}
		return result;
	}

	// verifier que l'id retourne est bien celui du produit en question
	// eventuellement ajouter l'id en parametre
	public void incrementProductQuantity(int idProduct) {
		CartProduct cartProduct = getProductFromCartListeById(idProduct);
		int cartProductQuantity = cartProduct.getQuantity();
		if (cartProductQuantity < cartProduct.getProduct().getStock()) {
			cartProductQuantity++;
		} else {
			cartProductQuantity = cartProduct.getProduct().getStock();
		}
		cartProduct.setQuantity(cartProductQuantity);
		log.info("Product Quantity : " + cartProduct.getQuantity());
	}

	public void decrementProductQuantity(int idProduct) {
		CartProduct cartProduct = getProductFromCartListeById(idProduct);
		int cartProductQuantity = cartProduct.getQuantity();
		if (cartProductQuantity > 1) {
			cartProductQuantity--;
		} else {
			cartProductQuantity = 1;
			// plus besoin de le supprimer de la liste car le user met 
			//au minimum la qtt 1, sinon il est assez intelligent pour 
			//le supprimer s'il en veut '0' article
//			deleteProductFromCart(idProduct);
//			proxyCart.deleteProductFromCart(cartProduct);
		}
		cartProduct.setQuantity(cartProductQuantity);
		log.info("Product Quantity : " + cartProduct.getQuantity());
	}
	
	public void upadateProductQuantityWithSpinner(int idProduct, int newQuantity){
		CartProduct cartProduct = getProductFromCartListeById(idProduct);
		cartProduct.setQuantity(newQuantity);
	}
	
	public void refreshQuantity(CartProduct cartProduct){
		quantity = cartProduct.getQuantity();
		log.info("Quantité = " + quantity);
	}
	
	public double getSubTotalPrice(int idProduct) {
		log.info(" ===<<< je calcule le subTotal>>>=== ");
		double subTotalPrice = 0 ;
		CartProduct cartProduct = getProductFromCartListeById(idProduct);
		subTotalPrice = cartProduct.getProduct().getPrice() * cartProduct.getQuantity();
		return subTotalPrice;
	}
	
	public double getTotalPrice() {
		log.info(" ===<<< je calcule le Total>>>=== ");
		double totalPrice = 0 ;
		for (CartProduct cp : panier) {
			totalPrice += getSubTotalPrice(cp.getProduct().getIdProduct());
		}
		return totalPrice;
	}
	
	public void deleteProductFromCart(int idProduct){
		CartProduct cartProduct = new CartProduct();
		Product product = getProductFromLocalListeById(idProduct);
		for (CartProduct cp : panier) {
			if (cp.getProduct().getIdProduct() == idProduct) {
				log.info("===== Produit trouve =====");
				panier.remove(cp);
				log.info("===== Produit id="+ cp.getProduct().getIdProduct() +" supprimé =====");
//				proxyCart.deleteProductFromCart(cp);
				break;
				}
			}
	}
	
	//TODO this method
	//Verifier si c'est ce qu'il faut faire car je ne vois pas la difference entre 
	public void cleanCart (){
		panier = new ArrayList<>();
//		panier.removeAll(panier);
//		proxyCart.cleanCart(idCart);	//il faut récuperer l'idCart du panier
	}
	
	public void deleteCart (){
		panier.removeAll(panier);
//		for (CartProduct cp : panier) {
//			proxyCart.deleteProductFromCart(cp);
//		}	
	}
	
	// enregistre le panier pour qu'il soit visible ds les autres managerd bean
	public void storeCart (){
		SpecificCustomer specificCustomer = new SpecificCustomer();
		specificCustomer.setIdUser(1);
		// Les autres parametres pour le panier seront ajoutes apres la validation du paiement
		for (CartProduct cp : panier) {
			specificUserCart.addCartProduct(cp);
		}
		log.info("============>>>>> JUSQUE LA, CA MARCHE 1 <<<<<============");
		specificUserCart.setUser(specificCustomer);
//		specificUserCart.setUser(userBean.getUser());
		log.info("============>>>>> JUSQUE LA, CA MARCHE 2 <<<<<============");
		
	}
	
	
//	================================================================
//	==============>>>>>> MOVE TO AN OTHER MBEAN <<<<<<==============
//	================================================================
	
//	public void validateCart (){
//		proxyCart.createNewCart(userBean.getUser().getIdUser());
//		for (CartProduct cp : panier) {
//			proxyCart.addProductToCart(cp);
//		}
//	}

	public List<ConstructorProduct> getListeProductBrute() {
		return listeProductBrute;
	}

	public void setListeProductBrute(List<ConstructorProduct> listeProductBrute) {
		this.listeProductBrute = listeProductBrute;
	}

	public int getIdSelectedProduct() {
		return idSelectedProduct;
	}

	public void setIdSelectedProduct(int idSelectedProduct) {
		this.idSelectedProduct = idSelectedProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public List<CartProduct> getPanier() {
		return panier;
	}

	public void setPanier(List<CartProduct> panier) {
		this.panier = panier;
	}

	public void setProxyCart(ICartSpecificCustomer proxyCart) {
		this.proxyCart = proxyCart;
	}

	public void setProxyCatalog(ICatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}

	public void setSpecificUserCart(Cart specificUserCart) {
		this.specificUserCart = specificUserCart;
	}



	@Autowired
	private UserBean userBean;

}