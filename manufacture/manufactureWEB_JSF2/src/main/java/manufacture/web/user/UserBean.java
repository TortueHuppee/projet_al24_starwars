package manufacture.web.user;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import manufacture.entity.user.User;

import org.apache.log4j.Logger;

/**
 * @author ocalik
 *	Bean gerant la connection et le maintien de la connexion de l'utilisateur sur le site.
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {
	
	private static Logger LOGGER = Logger.getLogger(UserBean.class);
	private User user;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isLogged(){
		return user == null ? false : true; 
	}
	
	public void checkLogin(){
		if(isLogged()){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("profil.xhtml?faces-redirect=true");
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}
	}

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger paramLOGGER) {
        LOGGER = paramLOGGER;
    }
}
