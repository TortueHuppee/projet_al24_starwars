package manufacture.web.user;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import manufacture.entity.user.Address;
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

@Component(value="signUpBean")
@Scope(value="session")
public class SignUpBean {
	
	private static Logger LOGGER = Logger.getLogger(SignUpBean.class);
	
	private User user;
	
	@ManagedProperty(value="#{inscription}")
	private IInscription proxyInscription;

	@Autowired
	private UserBean userBean;

	public SignUpBean(){
		user = new User();
		//		user.setAddress(new Address());
	}

	/**
	 * Verifie que l'utilisateur et unique et l'enregistre automatiquement si non
	 * Connecte automatiquement l'utilisateur si la cr�ation se fait
	 * @return String
	 */
	public String createUser(){
		user = proxyInscription.createAccount(user);
		if(user.getIdUser() != null){
			userBean.setUser(user); //Realise la connexion automatique au site
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
}
