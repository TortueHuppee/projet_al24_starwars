package manufacture.web.cart;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.entity.user.User;
import manufacture.ifacade.cart.IGestionCart;
import manufacture.web.catalogBean.ProductManagedBean;
import manufacture.web.user.UserBean;

import org.apache.log4j.Logger;

@ManagedBean(name = "mbCart")
@SessionScoped
public class ManagedBeanCart {

	private Logger log = Logger.getLogger(ManagedBeanCart.class);

	@ManagedProperty(value = "#{mbProduct}")
    private ProductManagedBean mbProduct;
	
	@ManagedProperty(value = "#{gestionCart}")
    private IGestionCart proxyCart;
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	private Cart cart;

	private int idSelectedProduct;
	private Product selectedProduct;
	private int quantity = 1;
	private double total = 0;

	private int productStock;

	private List<CartProduct> panier;
	private Delivery moyenTransport;
	private PaymentType moyenPaiement;
	
	@PostConstruct
	public void init() {

		cart = new Cart();
		panier = new ArrayList<CartProduct>();
		cart.setCartProducts(panier);

		moyenPaiement = new PaymentType();
		moyenPaiement.setIdPayment(1);

		moyenTransport = new Delivery();
		moyenTransport.setIdDelivery(1);
		
		cart.setDelivery(moyenTransport);
		cart.setPaymentType(moyenPaiement);
	}

	public void addProductToCart(Product productToAdd) {
		
	    FacesContext context = FacesContext.getCurrentInstance();
	    boolean ajoutPanier = true;
	    
	    //Si le panier n'est pas vide on rentre dans la m�thode.
	    if (panier.size() > 0)
	    {
	        //Sinon on v�rifie si le produit est d�j� pr�sent dans le panier.
	        for (CartProduct cp : panier)
	        {
	            //Si le produit est d�j� pr�sent dans le panier on met � jour la quantit� (qui ne doit pas d�passer la quantit� en stock du produit).
	            if (cp.getProduct().getIdProduct().equals(productToAdd.getIdProduct()))
	            {
	                ajoutPanier = false;
	                
	                int nouvelleQuantite = cp.getQuantity() + quantity;
	                if (nouvelleQuantite >= mbProduct.getQuantiteDispo())
	                {
	                    nouvelleQuantite = mbProduct.getQuantiteDispo();
	                }
	                cp.setQuantity(nouvelleQuantite);
	                
	                context.addMessage(null, new FacesMessage("Produit(s) ajout�(s)", nouvelleQuantite+" "+mbProduct.getProductRef().getProductName()+" ajout�(s) au panier" ) );
	            }
	        }
	    }
	    //Sinon on ajoute directement le produit au panier.
	    if (ajoutPanier)
	    {
	        CartProduct cartProduct = new CartProduct();
	        cartProduct.setQuantity(quantity);
	        cartProduct.setProduct(productToAdd);
	        panier.add(cartProduct);
	        
	        context.addMessage(null, new FacesMessage("Produit(s) ajout�(s)", quantity+" "+mbProduct.getProductRef().getProductName()+" ajout�(s) au panier" ) );
	    }
	
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
	}

	public void upadateProductQuantityWithSpinner(int idProduct, int newQuantity){
		CartProduct cartProduct = getProductFromCartListeById(idProduct);
		cartProduct.setQuantity(newQuantity);
	}

	public void refreshQuantity(CartProduct cartProduct){
		quantity = cartProduct.getQuantity();
		log.info("Quantit� = " + quantity);
	}

	public double getSubTotalPrice(int idProduct) {
		double subTotalPrice = 0 ;
		CartProduct cartProduct = getProductFromCartListeById(idProduct);
		subTotalPrice = cartProduct.getProduct().getPrice() * cartProduct.getQuantity();
		return subTotalPrice;
	}

	public double getTotalPrice() {
		double totalPrice = 0 ;
		for (CartProduct cp : panier) {
			totalPrice += getSubTotalPrice(cp.getProduct().getIdProduct());
		}
		total = totalPrice;
		return totalPrice;
	}

	public void deleteProductFromCart(CartProduct productToDelete){
	    panier.remove(productToDelete);
	}

	public void cleanCart (){
		panier = new ArrayList<>();
	}

	public void deleteCart (){
		panier.removeAll(panier);
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

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public Cart generateCart(User user) {
		Cart cart = new Cart();
		cart.setCartProducts(new ArrayList<CartProduct>());
		cart.setUser(user);
		return cart;
	}

    public Product getSelectedProduct() {
        return selectedProduct;
    }
    
    public void setSelectedProduct(Product paramSelectedProduct) {
        selectedProduct = paramSelectedProduct;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double paramTotal) {
        total = paramTotal;
    }

    public IGestionCart getProxyCart() {
        return proxyCart;
    }

    public void setProxyCart(IGestionCart paramProxyCart) {
        proxyCart = paramProxyCart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart paramCart) {
        cart = paramCart;
    }

    public Delivery getMoyenTransport() {
        return moyenTransport;
    }

    public void setMoyenTransport(Delivery paramMoyenTransport) {
        moyenTransport = paramMoyenTransport;
    }

    public PaymentType getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(PaymentType paramMoyenPaiement) {
        moyenPaiement = paramMoyenPaiement;
    }

    public ProductManagedBean getMbProduct() {
        return mbProduct;
    }

    public void setMbProduct(ProductManagedBean paramMbProduct) {
        mbProduct = paramMbProduct;
    }

}
