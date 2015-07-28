package manufacture.web.user;

import manufacture.entity.user.Address;
import manufacture.entity.user.User;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ocalik
 *	Bean gérant la connection et le maintien de la connexion de l'utilisateur sur le site.
 */
@Component
@Scope(value="session")
public class UserBean {
	
	private static Logger LOGGER = Logger.getLogger(UserBean.class);
	private User user;
	private Address adress;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isLogged(){
		return user == null ? false : true; 
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}
}
