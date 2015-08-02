package manufacture.web.cart;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.user.User;
import manufacture.ifacade.cart.IPaiement;
import manufacture.web.user.LoginBean;
import manufacture.web.user.UserBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ManagedBean(name = "paymentBean")
@SessionScoped
public class PaymentBean {

    private Logger log = Logger.getLogger(PaymentBean.class);
    
	private String cardNumber = "";
	private String pin;
	private String expirationDate;
	private String reponse;
	private String cardOwnerName;

	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	@ManagedProperty(value="#{mbCart}")
	private ManagedBeanCart mbCart;

	@ManagedProperty(value="#{paiement}")
	private IPaiement paiementFacade;

	@PostConstruct
	public void init() {
		cardNumber = "";
		pin = "";
		expirationDate = "";
		reponse = "";
		cardOwnerName = "";
	}

	//Méthodes
	
	public String valider() {
	    Cart commande = mbCart.getCart();
	    User user = userBean.getUser();
	    commande.setUser(user);	    
	    commande.setCartProducts(mbCart.getPanier());
	    
		paiementFacade.processPaiement(commande);
		
//		mbCart.setSpecificUserCart(mbCart.generateCart(userBean.getUser()));
//		userBean.getUser().getCarts().add(mbCart.getSpecificUserCart());
		
		mbCart.setPanier(new ArrayList<CartProduct>());
		return "paymentSuccess.xhtml";
	}
	
	public String processPayment(){
		if(!userBean.isLogged()){
			FacesMessage fm = new FacesMessage("Erreur", "Vous devez vous connecter pour procéder au paiement");
			FacesContext.getCurrentInstance().addMessage(null, fm);
			loginBean.setRedirect("paiement.xhtml?faces-redirect=true");
			return "login.xhtml?faces-redirect=true";
		} 
		return "paiement.xhtml?faces-redirect=true";
	}
	
	@Override
	public String toString() {
		return " ** " + "Paiement : " + cardNumber + " ** " + pin + " ** ";
	}

	//Getters et Setters
	
	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardOwnerName() {
		return cardOwnerName;
	}

	public void setCardOwnerName(String cardOwnerName) {
		this.cardOwnerName = cardOwnerName;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public ManagedBeanCart getMbCart() {
		return mbCart;
	}

	public void setMbCart(ManagedBeanCart mbCart) {
		this.mbCart = mbCart;
	}

	public IPaiement getPaiementFacade() {
		return paiementFacade;
	}

	public void setPaiementFacade(IPaiement paiementFacade) {
		this.paiementFacade = paiementFacade;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

}
