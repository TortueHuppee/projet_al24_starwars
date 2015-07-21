package manufacture.web.user;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import manufacture.entity.user.User;
import manufacture.ifacade.user.IConnection;
import manufacture.web.util.ClassPathLoader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")
public class LoginBean {
	private static Logger LOGGER = Logger.getLogger(LoginBean.class);
	private BeanFactory bf = ClassPathLoader.getFacadeBeanFactory();
	@Autowired
	private UserBean userBean; 
	private User user;
	
	public LoginBean(){
		user = new User(); 
	}
	
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

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
