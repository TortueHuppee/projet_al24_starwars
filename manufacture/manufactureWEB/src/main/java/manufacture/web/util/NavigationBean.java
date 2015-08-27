package manufacture.web.util;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import manufacture.web.advert.AnnonceManagedBean;
import manufacture.web.cart.StepsCartManagedBean;
import manufacture.web.user.LoginBean;
import manufacture.web.user.ProfilBean;
import manufacture.web.user.UserBean;

@ManagedBean(name="navigationBean")
@SessionScoped
public class NavigationBean {

    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;
    
    @ManagedProperty(value="#{profilBean}")
    private ProfilBean profilBean;
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    @ManagedProperty(value = "#{mbAnnonce}")
    private AnnonceManagedBean annonceBean;
    
    @ManagedProperty(value = "#{mbSteps}")
    private StepsCartManagedBean mbSteps;
    
    private static final Integer USER_PARTICULIER_ROLE_ID = 1;
    private static final Integer USER_PROFESSIONNEL_ROLE_ID = 2;
    private static final Integer USER_ARTISAN_ROLE_ID = 3;
    
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
            loginBean.setRedirect("profil.xhtml?faces-redirect=true");
            return "login.xhtml?faces-redirect=true";
        }
    }
    
    public String accessAdvert(){
        if(userBean.isLogged()){
            if (userBean.getUser().getUserRole().getIdUserRole() == USER_ARTISAN_ROLE_ID || userBean.getUser().getUserRole().getIdUserRole() == USER_PARTICULIER_ROLE_ID)
            {
                annonceBean.initialiseDonnees();
                annonceBean.setDonneesInitialisees(true);
                return "annonce.xhtml?faces-redirect=true";
            }
            else
            {
                return "annonceNonAutorisee.xhtml?faces-redirect=true";
            }
        }else{
            annonceBean.setDate(new Date());
            loginBean.setRedirect("annonce.xhtml?faces-redirect=true");
            return "login.xhtml?faces-redirect=true";
        }
    }

    public String accessVisualisationPanier(){
        if(!userBean.isLogged()){
//            FacesMessage fm = new FacesMessage("Erreur", "Vous devez vous connecter pour continuer la commande");
//            FacesContext.getCurrentInstance().addMessage(null, fm);
            loginBean.setRedirect("panierStep1.xhtml?faces-redirect=true");
            return "login.xhtml?faces-redirect=true";
        } else {
            mbSteps.initialisationDonnees();
            return "panierStep1.xhtml?faces-redirect=true";
        }
    }
    
    //Getters et Setters
    
    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean paramUserBean) {
        userBean = paramUserBean;
    }

    public ProfilBean getProfilBean() {
        return profilBean;
    }

    public void setProfilBean(ProfilBean paramProfilBean) {
        profilBean = paramProfilBean;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean paramLoginBean) {
        loginBean = paramLoginBean;
    }

    public AnnonceManagedBean getAnnonceBean() {
        return annonceBean;
    }

    public void setAnnonceBean(AnnonceManagedBean paramAnnonceBean) {
        annonceBean = paramAnnonceBean;
    }

    public static Integer getUserArtisanRoleId() {
        return USER_ARTISAN_ROLE_ID;
    }

    public static Integer getUserParticulierRoleId() {
        return USER_PARTICULIER_ROLE_ID;
    }

    public StepsCartManagedBean getMbSteps() {
        return mbSteps;
    }

    public void setMbSteps(StepsCartManagedBean paramMbSteps) {
        mbSteps = paramMbSteps;
    }

    public static Integer getUserProfessionnelRoleId() {
        return USER_PROFESSIONNEL_ROLE_ID;
    }
}
