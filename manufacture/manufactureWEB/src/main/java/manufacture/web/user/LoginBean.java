package manufacture.web.user;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
	
	@ManagedProperty(value="#{profilBean}")
	private ProfilBean profilBean;
	
	private User user;
	private String redirect;
	
	private static final Integer USER_PARTICULIER_ROLE_ID = 1;
	private static final Integer USER_PROFESSIONNEL_ROLE_ID = 2;
	private static final Integer USER_ARTISAN_ROLE_ID = 3;
	
	private String erreurConnexion;
	
	public LoginBean(){
		user = new User(); 
	}
	
	public String doLogin(){
	    
		LOGGER.info("enter login bean");
		User userTmp = proxyConnection.connectUser(user);

		if(userTmp == null)
		{
            FacesMessage message = new FacesMessage("Utilisateur non trouv�");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, message);		
            erreurConnexion = "Erreur de connexion, utilisateur non trouv�";
            return "login.xhtml?faces-redirect=true";
		}
		
		erreurConnexion = "";
		
		userBean.setUser(userTmp);
		profilBean.initialiseDonnees();
		String toPage = null;
		
		//Redirection provenant d'une page de l'application pour laquelle il faut �tre connect�
		if(redirect != null)
		{
			//Si la connection a �t� demand� par la page Valider son panier,
			//il faut v�rifier que l'utilisateur est un particulier ou un professionnel
			if (redirect.equals("panierStep1.xhtml?faces-redirect=true"))
			{
				if (userTmp.getUserRole().getIdUserRole() == USER_PARTICULIER_ROLE_ID || userTmp.getUserRole().getIdUserRole() == USER_PROFESSIONNEL_ROLE_ID)
				{
					toPage = redirect;
					redirect = null;
				}
				else
				{
					toPage = "panierNonAutorise.xhtml?faces-redirect=true";
					redirect = null;
				}
			}
			//Si la connection est demand�e par une autre page de l'application
			//On peut renvoyer sur la page, n'importe quel utilisateur a acc�s aux autres pages
			else
			{
				toPage = redirect;
				redirect = null;
			}
		}
		else
		{
			toPage = "index.xhtml?faces-redirect=true";
			
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
		mbCart.init();
		LOGGER.info("user reseted");
		return "index.xhtml?faces-redirect=true";
	}
	
	//Navigation
	public String accessProfil(){
        if(userBean.isLogged()){
            if (!profilBean.isDonneesInitialisees())
            {
                profilBean.initialiseDonnees();
            }
            return "profil.xhtml?faces-redirect=true";
        }else{
            profilBean.setDate(new Date());
            redirect = "profil.xhtml?faces-redirect=true";
            return "login.xhtml?faces-redirect=true";
        }
    }
	
	//Getters et Setters

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

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger paramLOGGER) {
        LOGGER = paramLOGGER;
    }

    public static Integer getUserParticulierRoleId() {
        return USER_PARTICULIER_ROLE_ID;
    }

    public static Integer getUserProfessionnelRoleId() {
        return USER_PROFESSIONNEL_ROLE_ID;
    }

    public static Integer getUserArtisanRoleId() {
        return USER_ARTISAN_ROLE_ID;
    }

    public String getErreurConnexion() {
        return erreurConnexion;
    }

    public void setErreurConnexion(String paramErreurConnexion) {
        erreurConnexion = paramErreurConnexion;
    }

    public ProfilBean getProfilBean() {
        return profilBean;
    }

    public void setProfilBean(ProfilBean paramProfilBean) {
        profilBean = paramProfilBean;
    }
}
