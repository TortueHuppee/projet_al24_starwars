package manufacture.web.user;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import manufacture.entity.cart.Cart;
import manufacture.entity.user.User;
import manufacture.ifacade.user.IConnection;
import manufacture.web.cart.ManagedBeanCart;

import org.apache.log4j.Logger;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
	
	private static Logger LOGGER = Logger.getLogger(LoginBean.class);
	
	@ManagedProperty(value="#{connection}")
	private IConnection proxyConnection; 

	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty(value="#{mbCart}")
	private ManagedBeanCart mbCart;
	
	private User user;
	private String redirect;
	
	public LoginBean(){
		user = new User(); 
	}
	
	public String doLogin(){
	    
		LOGGER.info("enter login bean");
		User userTmp = proxyConnection.connectUser(user);

		if(userTmp == null)
		{
            FacesMessage message = new FacesMessage("Utilisateur non trouvé");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, message);		
            return "login.xhtml?faces-redirect=true";
		}
		
		userBean.setUser(userTmp);
		String toPage = null;
		
		if(redirect != null)
		{
			if (redirect.equals("annonce.xhtml?faces-redirect=true"))
			{
				if (userTmp.getUserRole().getIdUserRole() == 1 || userTmp.getUserRole().getIdUserRole() == 3)
				{
					toPage = redirect;
					redirect = null;
				}
				else
				{
					toPage = "annonceNonAutorisee.xhtml?faces-redirect=true";
					redirect = null;
				}
			}
			else
			{
				toPage = redirect;
				redirect = null;
			}
		}
		else
		{
			//En attendant que Vincent nous explique cette partie là du code :
			toPage = "profil.xhtml?faces-redirect=true";
			
		    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		    try {
				ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return toPage;
	} 
	
	public String doLogout(){
		userBean.setUser(null);
		mbCart.setCart(new Cart());
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
	
	public IConnection getProxyConnection() {
		return proxyConnection;
	}

	public void setProxyConnection(IConnection proxyConnection) {
		this.proxyConnection = proxyConnection;
	}
	
	public boolean isLogged(){
		return user == null ? false : true;
	}

	public ManagedBeanCart getMbCart() {
		return mbCart;
	}

	public void setMbCart(ManagedBeanCart mbCart) {
		this.mbCart = mbCart;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	} 
}
