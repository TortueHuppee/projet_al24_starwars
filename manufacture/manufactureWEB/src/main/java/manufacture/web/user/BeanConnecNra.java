package manufacture.web.user;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import manufacture.entity.user.User;
import manufacture.ifacade.nra.IFacNra;
import manufacture.ifacade.user.IConnection;
import manufacture.web.util.ClassPathLoader;

@ManagedBean(name = "mbConnexion")
@SessionScoped
public class BeanConnecNra {


	private String email;
	private String login;
	private String password;
	private String reponse;

	//Instanciation "seul new normalement"
	private BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springFacade.xml");
	
	private IFacNra proxyConnexion = (IFacNra) bf.getBean(IFacNra.class);

	public String verifier() {
		User monUser = proxyConnexion.getUserByLoginAndPassword(login, password);
		reponse = login + " +++ " + password;
		System.out.println("il est là : ");
		System.out.println(monUser.getLogin());
		System.out.println(" : c'est bon ");
		return reponse;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	
	/*
	public String doLogin(){
		LOGGER.info("enter login bean");
		IConnection connection = bf.getBean(IConnection.class);
		User userTmp = connection.connectUser(user);
		if(userTmp==null){
            FacesMessage message = new FacesMessage("Utilisateur non trouvé");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, message);		
            return "login.xhtml?faces-redirect=true";
		}
		userBean.setUser(userTmp); 
		return "index.xhtml?faces-redirect=true";
	} 
	
	public String doLogout(){
		userBean.setUser(null);
		LOGGER.info("user reseted");
		return null;
	}

	public UserBeanTmp getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBeanTmp userBean) {
		this.userBean = userBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/
}
