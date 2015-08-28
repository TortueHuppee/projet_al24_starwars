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

import org.apache.log4j.Logger;

@ManagedBean(name = "mbCart")
@SessionScoped
public class ManagedBeanCart {

	private Logger log = Logger.getLogger(ManagedBeanCart.class);

	@ManagedProperty(value = "#{mbProduct}")
    private ProductManagedBean mbProduct;
	
	@ManagedProperty(value = "#{gestionCart}")
    private IGestionCart proxyCart;
	
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
	    
	    //Si le panier n'est pas vide on rentre dans la methode.
	    if (panier.size() > 0)
	    {
	        //Sinon on verifie si le produit est deja present dans le panier.
	        for (CartProduct cp : panier)
	        {
	            //Si le produit est deja present dans le panier on met a jour la quantite (qui ne doit pas depasser la quantite en stock du produit).
	            if (cp.getProduct().getIdProduct().equals(productToAdd.getIdProduct()))
	            {
	                ajoutPanier = false;
	                
	                int nouvelleQuantite = cp.getQuantity() + quantity;
	                if (nouvelleQuantite >= mbProduct.getQuantiteDispo())
	                {
	                    nouvelleQuantite = mbProduct.getQuantiteDispo();
	                }
	                cp.setQuantity(nouvelleQuantite);
	                
	                context.addMessage(null, new FacesMessage("Produit(s) ajouté(s)", nouvelleQuantite+" "+mbProduct.getProductRef().getProductName()+" ajouté(s) au panier" ) );
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
	        
	        context.addMessage(null, new FacesMessage("Produit(s) ajouté(s)", quantity+" "+mbProduct.getProductRef().getProductName()+" ajouté(s) au panier" ) );
	    }
	    quantity = 1;
	}

	public void incrementProductQuantity(CartProduct cartProduct) {
		int cartProductQuantity = cartProduct.getQuantity();
		if (cartProductQuantity < cartProduct.getProduct().getStock()) {
			cartProductQuantity++;
		} else {
			cartProductQuantity = cartProduct.getProduct().getStock();
		}
		cartProduct.setQuantity(cartProductQuantity);
	}

	public void decrementProductQuantity(CartProduct cartProduct) {
		int cartProductQuantity = cartProduct.getQuantity();
		if (cartProductQuantity > 1) {
			cartProductQuantity--;
		} else {
			cartProductQuantity = 1;
		}
		cartProduct.setQuantity(cartProductQuantity);
	}

	public void upadateProductQuantityWithSpinner(CartProduct cartProduct, int newQuantity){
		cartProduct.setQuantity(newQuantity);
	}

	public void refreshQuantity(CartProduct cartProduct){
		quantity = cartProduct.getQuantity();
		log.info("Quantit� = " + quantity);
	}

	public double getSubTotalPrice(CartProduct cartProduct) {
		double subTotalPrice = 0 ;
		subTotalPrice = cartProduct.getProduct().getPrice() * cartProduct.getQuantity();
		return subTotalPrice;
	}

	public double getTotalPrice(Cart cart) {
		double totalPrice = 0 ;
		for (CartProduct cp : cart.getCartProducts()) {
			totalPrice += getSubTotalPrice(cp);
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

	//Getters et Setters
	
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
