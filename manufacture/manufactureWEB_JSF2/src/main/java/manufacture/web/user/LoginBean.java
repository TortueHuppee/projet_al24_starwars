package manufacture.web.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.user.User;
import manufacture.ifacade.user.IConnection;
import manufacture.web.advert.AnnonceManagedBean;
import manufacture.web.cart.ManagedBeanCart;
import manufacture.web.cart.StepsCartManagedBean;

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

    @ManagedProperty(value = "#{mbAnnonce}")
    private AnnonceManagedBean annonceBean;

    @ManagedProperty(value="#{profilBean}")
    private ProfilBean profilBean;
    
    @ManagedProperty(value = "#{mbSteps}")
    private StepsCartManagedBean mbSteps;

    private User user;
    private String redirect;

    private String erreurConnexion;
    
    private static final Integer USER_ADMINISTRATEUR_ROLE_ID = 4;

    public LoginBean(){
        user = new User(); 
    }

    public String doLogin(){

        LOGGER.info("enter login bean");
        User userTmp = proxyConnection.connectUser(user);

        if(userTmp == null)
        {
            erreurConnexion = "Erreur de connexion, utilisateur non trouvé";
            return "login.xhtml?faces-redirect=true";
        }

        erreurConnexion = "";

        userBean.setUser(userTmp);
        profilBean.initialiseDonnees();
        annonceBean.initialiseDonnees();
        mbSteps.initialisationDonnees();
        String toPage = null;

        if(redirect != null)
        {
            toPage = redirect;
            redirect = null;
        }
        else
        {
        	if (userBean.getUser().getUserRole().getIdUserRole() == USER_ADMINISTRATEUR_ROLE_ID)
        	{
        		toPage = "administrator.xhtml?faces-redirect=true";
        	}
        	else
        	{
        		toPage = "index.xhtml?faces-redirect=true";
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

    public AnnonceManagedBean getAnnonceBean() {
        return annonceBean;
    }

    public void setAnnonceBean(AnnonceManagedBean paramAnnonceBean) {
        annonceBean = paramAnnonceBean;
    }

    public StepsCartManagedBean getMbSteps() {
        return mbSteps;
    }

    public void setMbSteps(StepsCartManagedBean paramMbSteps) {
        mbSteps = paramMbSteps;
    }
}
