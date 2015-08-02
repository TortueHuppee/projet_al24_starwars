package manufacture.web.user;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import manufacture.entity.user.Civility;
import manufacture.entity.user.User;
import manufacture.entity.user.UserRole;
import manufacture.ifacade.user.IInscription;

import org.apache.log4j.Logger;

@ManagedBean(name="signUpBean")
@RequestScoped
public class SignUpBean {
	
	private static Logger LOGGER = Logger.getLogger(SignUpBean.class);
	
	private User user;
	
	@ManagedProperty(value="#{inscription}")
	private IInscription proxyInscription;
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * Informations à renseigner sur l'utilisateur :
	 */
	private String userType;
	private Civility civilite;
	private UserRole role;
	
	/**
	 * Informations rentrées par l'utilisateur :
	 */
	private String login;
	private String motDePasse;
	private String prenom;
	private String nomFamille;
	private String email;
	
	private String companyName;
	private String domaineActivite;
	
	
	/**
	 * Messages d'erreurs :
	 */
	private String messageErreurLogin;
	private String messageErreurMotDePasse;
	private String messageErreurPrenom;
	private String messageErreurNomFamille;
	private String messageErreurEmail;
	
	private String messageErreurCompanyName;
	private String messageErreurDomaineActivite;

	@PostConstruct
	void init(){
		user = new User();
		civilite = new Civility();
		civilite.setIdCivility(1);
		role = new UserRole();
		role.setIdUserRole(1);
		
		messageErreurLogin = "";
		messageErreurMotDePasse = "";
		messageErreurPrenom = "";
		messageErreurNomFamille = "";
		messageErreurEmail = "";
		messageErreurCompanyName = "";
		messageErreurDomaineActivite = "";
		
		login = "";
		motDePasse = "";
		prenom = "";
		nomFamille = "";
		email = "";
		
		companyName = "";
		domaineActivite = "";
		
		userType = "particulier";
	}

	//Méthodes
	
	/**
	 * Vérifie que l'utilisateur est unique et l'enregistre automatiquement.
	 * Connecte automatiquement l'utilisateur si la création se fait.
	 * @return String
	 */
	public String createUser()
	{        
	    user.setLogin(login);
	    user.setPassword(motDePasse);
	    user.setEmail(email);
	    user.setUserName(nomFamille);
	    user.setUserFirstName(prenom);
	    user.setCreateTime(new Date());
        user.setBlackListed(false);
        user.setCivility(civilite);
        user.setUserRole(role);
        
        if (!userType.equals("particulier"))
	    {
            user.setCompanyName(companyName);
            user.setActivityDomain(domaineActivite);
            user.setNbRecall(0);
	    }
        
        proxyInscription.createAccount(user);
        
		if(user.getIdUser() != null){
			getUserBean().setUser(user); //Realise la connexion automatique au site
			return "index.xhtml?faces-redirect=true"; 
		} else {

		    //Affiche un message d'erreur a l'utilisateur
			FacesContext.getCurrentInstance(). 
			addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Warning message...", null));        
		}
		
		//Si il y a un probleme on retourne sur la page d'inscription
		return "signin.xhtml?faces-redirect=true"; 
	} 
	
	/**
	 * Méthode vérifiant les informations entrées par l'utilisateur et comptant le nombre d'erreurs.
	 * Si le nombre d'erreur est de zéro, l'utilisateur est enregistré dans la base de données.
	 * @return String
	 */
	public String validationFormulaire()
	{
		int nombreErreur = 0;
		nombreErreur += validationLogin();
		nombreErreur += validationMotDePasse();
		nombreErreur += validationNom();
		nombreErreur += validationPrenom();
		nombreErreur += validationMail();
		
		if (!userType.equals("particulier"))
		{
		    nombreErreur += validationCompanyName();
		    nombreErreur += validationDomaineActivite();
		}

		if (nombreErreur == 0)
		{
			return createUser();
		}
		return null;
	}
	
	public int validationLogin()
	{
		if (login.equals(""))
		{
			messageErreurLogin = "Veuillez indiquer un login";
			return 1;
		}
		else
		{
			if (proxyInscription.loginAlreadyExisting(login))
			{
				messageErreurLogin = "Ce login est déjà utilisé";
				return 1;
			}
			else
			{
				messageErreurLogin = "";
				return 0;
			}
		}
	}

	public int validationMotDePasse()
	{
		if (motDePasse.equals(""))
		{
			messageErreurMotDePasse = "Veuillez indiquer un mot de passe";
			return 1;
		}
		else
		{
			messageErreurMotDePasse = "";
			return 0;
		}
	}
	
	public int validationNom()
	{
		if (nomFamille.equals(""))
		{
			messageErreurNomFamille = "Veuillez indiquer un nom de famille";
			return 1;
		}
		else
		{
			messageErreurNomFamille = "";
			return 0;
		}
	}
	
	public int validationPrenom()
	{
		if (prenom.equals(""))
		{
			messageErreurPrenom = "Veuillez indiquer un prénom";
			return 1;
		}
		else
		{
			messageErreurPrenom = "";
			return 0;
		}
	}
	
	public int validationMail()
	{
		if (email.equals(""))
		{
			messageErreurEmail = "Veuillez indiquer un email";
			return 1;
		}
		else
		{
			if (!email.contains("@"))
			{
				messageErreurEmail = "Mail invalide, veuillez vérifier votre saisie";
				return 1;
			}
			else
			{
				if (proxyInscription.emailAlreadyExisting(email))
				{
					messageErreurEmail = "Cet email est déjà utilisé";
					return 1;
				}
				else
				{
					messageErreurPrenom = "";
					return 0;
				}
			}
		}
	}
	
	public int validationCompanyName()
    {
        if (companyName.equals(""))
        {
            messageErreurCompanyName = "Veuillez indiquer le nom de votre companie";
            return 1;
        }
        else
        {
            messageErreurCompanyName = "";
            return 0;
        }
    }
	
	public int validationDomaineActivite()
    {
        if (domaineActivite.equals(""))
        {
            messageErreurDomaineActivite = "Veuillez indiquer votre domaine d'activité";
            return 1;
        }
        else
        {
            messageErreurDomaineActivite = "";
            return 0;
        }
    }
	
	//Getters et Setters

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

	public String getMessageErreurLogin() {
		return messageErreurLogin;
	}

	public void setMessageErreurLogin(String messageErreurLogin) {
		this.messageErreurLogin = messageErreurLogin;
	}

	public String getMessageErreurMotDePasse() {
		return messageErreurMotDePasse;
	}

	public void setMessageErreurMotDePasse(String messageErreurMotDePasse) {
		this.messageErreurMotDePasse = messageErreurMotDePasse;
	}

	public String getMessageErreurPrenom() {
		return messageErreurPrenom;
	}

	public void setMessageErreurPrenom(String messageErreurPrenom) {
		this.messageErreurPrenom = messageErreurPrenom;
	}

	public String getMessageErreurNomFamille() {
		return messageErreurNomFamille;
	}

	public void setMessageErreurNomFamille(String messageErreurNomFamille) {
		this.messageErreurNomFamille = messageErreurNomFamille;
	}

	public String getMessageErreurEmail() {
		return messageErreurEmail;
	}

	public void setMessageErreurEmail(String messageErreurEmail) {
		this.messageErreurEmail = messageErreurEmail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNomFamille() {
		return nomFamille;
	}

	public void setNomFamille(String nomFamille) {
		this.nomFamille = nomFamille;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMessageErreurCompanyName() {
		return messageErreurCompanyName;
	}

	public void setMessageErreurCompanyName(String messageErreurCompanyName) {
		this.messageErreurCompanyName = messageErreurCompanyName;
	}

    public Civility getCivilite() {
        return civilite;
    }

    public void setCivilite(Civility paramCivilite) {
        civilite = paramCivilite;
    }

    public String getDomaineActivite() {
        return domaineActivite;
    }

    public void setDomaineActivite(String paramDomaineActivite) {
        domaineActivite = paramDomaineActivite;
    }

    public String getMessageErreurDomaineActivite() {
        return messageErreurDomaineActivite;
    }

    public void setMessageErreurDomaineActivite(
            String paramMessageErreurDomaineActivite) {
        messageErreurDomaineActivite = paramMessageErreurDomaineActivite;
    }
}
