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
import manufacture.ifacade.user.IProfil;
import manufacture.web.catalogBean.CatalogManagedBean;
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

	private List<CartProduct> panier = new ArrayList<CartProduct>();
	private Delivery moyenTransport;
	private PaymentType moyenPaiement;
	
	@PostConstruct
	void init() {

		cart = new Cart();
		cart.setCartProducts(new ArrayList<CartProduct>());

		moyenPaiement = new PaymentType();
		moyenPaiement.setIdPayment(1);

		moyenTransport = new Delivery();
		moyenTransport.setIdDelivery(1);
		
		cart.setDelivery(moyenTransport);
		cart.setPaymentType(moyenPaiement);
	}

	public void addProductToCart(Product productToAdd) {
		
	    FacesContext context = FacesContext.getCurrentInstance();
	    
	    //Si le panier n'est pas vide on rentre dans la méthode.
	    if (panier.size() > 0)
	    {
	        //Sinon on vérifie si le produit est déjà présent dans le panier.
	        for (CartProduct cp : panier)
	        {
	            //Si le produit est déjà présent dans le panier on met à jour la quantité (qui ne doit pas dépasser la quantité en stock du produit).
	            if (cp.getProduct().getIdProduct() == productToAdd.getIdProduct())
	            {
	                int nouvelleQuantite = cp.getQuantity() + quantity;
	                if (nouvelleQuantite >= mbProduct.getQuantiteDispo())
	                {
	                    nouvelleQuantite = mbProduct.getQuantiteDispo();
	                    cp.setQuantity(nouvelleQuantite);
	                }
	                else
	                {
	                    cp.setQuantity(nouvelleQuantite);
	                }
	                
	                context.addMessage(null, new FacesMessage("Produit(s) ajouté(s)", nouvelleQuantite+" "+mbProduct.getProductRef().getProductName()+" ajouté(s) au panier" ) );
	            }
	            //Sinon on l'ajoute au panier.
	            else
	            {
	                CartProduct cartProduct = new CartProduct();
	                cartProduct.setQuantity(quantity);
	                cartProduct.setProduct(productToAdd);
	                panier.add(cartProduct);
	                
	                context.addMessage(null, new FacesMessage("Produit(s) ajouté(s)", quantity+" "+mbProduct.getProductRef().getProductName()+" ajouté(s) au panier" ) );
	            }
	        }
	    }
	  //Sinon on ajoute directement le produit au panier.
	    else
	    {
	        CartProduct cartProduct = new CartProduct();
	        cartProduct.setQuantity(quantity);
	        cartProduct.setProduct(productToAdd);
	        panier.add(cartProduct);
	        
	        context.addMessage(null, new FacesMessage("Produit(s) ajouté(s)", quantity+" "+mbProduct.getProductRef().getProductName()+" ajouté(s) au panier" ) );
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
		log.info("Quantité = " + quantity);
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

	public void deleteProductFromCart(int idProduct){
		CartProduct cartProduct = new CartProduct();
		for (CartProduct cp : panier) {
			if (cp.getProduct().getIdProduct() == idProduct) {
				panier.remove(cp);
			}
		}
	}

	public void cleanCart (){
		panier = new ArrayList<>();
	}

	public void deleteCart (){
		panier.removeAll(panier);
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
