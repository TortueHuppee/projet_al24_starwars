package manufacture.web.user;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import manufacture.entity.user.Address;
import manufacture.entity.user.Administrator;
import manufacture.entity.user.Artisan;
import manufacture.entity.user.City;
import manufacture.entity.user.ProfessionnalCustomer;
import manufacture.entity.user.SpecificCustomer;
import manufacture.entity.user.User;
import manufacture.ifacade.user.IInscription;

import org.apache.log4j.Logger;

@ManagedBean(name="signUpBean")
@RequestScoped
public class SignUpBean {
	
	private static Logger LOGGER = Logger.getLogger(SignUpBean.class);
	
	private User user;
	private SpecificCustomer clientParticulier;
	private ProfessionnalCustomer clientProfesionnel;
	private Administrator administrateur;
	private Artisan artisan;
	
	@ManagedProperty(value="#{inscription}")
	private IInscription proxyInscription;
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	private String userType;
	private Address addresse;
	
	/**
	 * Informations rentr�es par l'utilisateur
	 */
	private String login;
	private String motDePasse;
	private String prenom;
	private String nomFamille;
	private String email;
	
	private String companyName;
	
	
	/**
	 * Messages d'erreurs :
	 */
	private String messageErreurLogin;
	private String messageErreurMotDePasse;
	private String messageErreurPrenom;
	private String messageErreurNomFamille;
	private String messageErreurEmail;
	
	private String messageErreurCompanyName;

	@PostConstruct
	void init(){
		user = new User();
		addresse = new Address();
		addresse.setCity(new City());
		
		messageErreurLogin = "";
		messageErreurMotDePasse = "";
		messageErreurPrenom = "";
		messageErreurNomFamille = "";
		messageErreurEmail = "";
		messageErreurCompanyName = "";
		
		login = "";
		motDePasse = "";
		prenom = "";
		nomFamille = "";
		email = "";
		
		companyName = "";
		
		userType = "particulier";
	}

	//M�thodes
	
	/**
	 * Verifie que l'utilisateur est unique et l'enregistre automatiquement.
	 * Connecte automatiquement l'utilisateur si la cr�ation se fait.
	 * @return String
	 */
	public String createUser(){
	    
		user.setLogin(login);
		user.setPassword(motDePasse);
		user.setEmail(email);
		user.setUserName(nomFamille);
		user.setUserFirstName(prenom);
		
	    user.setAddresses(new ArrayList<Address>());
        user.addAddress(addresse);
        
		user = proxyInscription.createAccount(user);
		
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
	 * M�thode v�rifiant les informations entr�es par l'utilisateur et comptant le nombre d'erreurs.
	 * Si le nombre d'erreur est de z�ro, l'utilisateur est enregistr� dans la base de donn�es.
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
		
		if (nombreErreur == 0)
		{
			createUser();
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
				messageErreurLogin = "Ce login est d�j� utilis�";
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
			messageErreurPrenom = "Veuillez indiquer un pr�nom";
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
				messageErreurEmail = "Mail invalide, veuillez v�rifier votre saisie";
				return 1;
			}
			else
			{
				if (proxyInscription.emailAlreadyExisting(email))
				{
					messageErreurEmail = "Cet email est d�j� utilis�";
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

    public Address getAddresse() {
        return addresse;
    }

    public void setAddresse(Address paramAddresse) {
        addresse = paramAddresse;
    }

	public SpecificCustomer getClientParticulier() {
		return clientParticulier;
	}

	public void setClientParticulier(SpecificCustomer clientParticulier) {
		this.clientParticulier = clientParticulier;
	}

	public ProfessionnalCustomer getClientProfesionnel() {
		return clientProfesionnel;
	}

	public void setClientProfesionnel(ProfessionnalCustomer clientProfesionnel) {
		this.clientProfesionnel = clientProfesionnel;
	}

	public Administrator getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrator administrateur) {
		this.administrateur = administrateur;
	}

	public Artisan getArtisan() {
		return artisan;
	}

	public void setArtisan(Artisan artisan) {
		this.artisan = artisan;
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
}
