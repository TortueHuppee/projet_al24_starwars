package manufacture.web.cart;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ManagedBean(name = "paymentBean")
@SessionScoped
public class PaymentBean {

	private String cardNumber = "";
	private String pin;
	private String expirationDate;
	private String reponse;
	private String cardOwnerName;

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

	@PostConstruct
	public void init() {
		cardNumber = "";
		pin = "";
		expirationDate = "";
		reponse = "";
		cardOwnerName = "";
	}

	public String valider() {
		reponse = "Paiement pris en compte pour" + cardNumber + " " + pin;
		cardNumber = "";
		pin = "";
		return "";
	}

	@Override
	public String toString() {
		return " ** " + "Paiement : " + cardNumber + " ** " + pin + " ** ";
	}

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

	
	
	

}
