package manufacture.web.user;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import manufacture.entity.user.Address;
import manufacture.entity.user.City;
import manufacture.entity.user.User;
import manufacture.ifacade.user.IConnection;
import manufacture.ifacade.user.IInscription;
import manufacture.web.util.ClassPathLoader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@ManagedBean(name="signUpBean")
@SessionScoped
public class SignUpBean {
	
	private static Logger LOGGER = Logger.getLogger(SignUpBean.class);
	
	private User user;
	
	@ManagedProperty(value="#{inscription}")
	private IInscription proxyInscription;
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	private String userType;
	public SignUpBean(){
		user = new User();
//		user.setAddress(new Address());
//		user.getAddress().setCity(new City());
	}

	/**
	 * Verifie que l'utilisateur et unique et l'enregistre automatiquement si non
	 * Connecte automatiquement l'utilisateur si la création se fait
	 * @return String
	 */
	public String createUser(){
		user = getProxyInscription().createAccount(user);
		if(user.getIdUser() != null){
			getUserBean().setUser(user); //Realise la connexion automatique au site
			return "index.xhtml?faces-redirect=true"; 
		}else{
			//Affiche un message d'erreur a l'utilisateur
			FacesContext.getCurrentInstance(). 
			addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Warning message...", null));        
		}
		//Si il y a un probleme on retourne sur la page d'inscription
		return "signin.xhtml?faces-redirect=true"; 
	} 

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public IInscription getProxyInscription() {
		return proxyInscription;
	}

	public void setProxyInscription(IInscription proxyInscription) {
		this.proxyInscription = proxyInscription;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
