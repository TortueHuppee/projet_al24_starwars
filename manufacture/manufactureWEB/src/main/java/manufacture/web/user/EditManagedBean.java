package manufacture.web.user;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import manufacture.entity.user.Address;
import manufacture.entity.user.City;
import manufacture.entity.user.User;
import manufacture.ifacade.user.IProfil;

@ManagedBean(name="editBean")
@ViewScoped
public class EditManagedBean {

	private boolean editModePersonnel;
	private boolean editModeAdresse;

	@ManagedProperty(value="#{profil}")
	private IProfil proxyProfil;
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	@ManagedProperty(value="#{profilBean}")
	private ProfilBean profilBean;
	
	private Address nouvelleAdresse = new Address();
	private City ville = new City();
	
	@PostConstruct
	public void init()
	{
	    nouvelleAdresse.setUser(userBean.getUser());
        nouvelleAdresse.setCity(ville);
	}
	
	//Méthodes
	
	public void saveNewAddress()
	{
		proxyProfil.saveAddress(nouvelleAdresse);

		if (nouvelleAdresse.getIsBillingaddress())
		{
			profilBean.getAdressesFacturation().add(nouvelleAdresse);
		}
		
		if (nouvelleAdresse.getIsDeliveryaddress())
		{
			profilBean.getAdressesLivraison().add(nouvelleAdresse);
		}
			
		nouvelleAdresse = new Address();
	}
	
	public void editModePersonnel() {
		editModePersonnel = true;
	}
	
	public void cancelModePersonnel() {
		editModePersonnel = false;
	}
	
	public void editModeAdresse(Address addresse) {
		editModeAdresse = true;
	}
	
	public void cancelModeAdresse() {
		editModeAdresse = false;
	}

	public void saveUser() {
	   User newuser = proxyProfil.editUser(userBean.getUser());
	   userBean.setUser(newuser);
	   editModePersonnel = false;
	}
	
	public void saveAdresse(Address addresse) {
		proxyProfil.editAddress(addresse);
		editModeAdresse = false;
	}

	//Getters et Setters
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public boolean isEditModePersonnel() {
		return editModePersonnel;
	}

	public void setEditModePersonnel(boolean editModePersonnel) {
		this.editModePersonnel = editModePersonnel;
	}

	public boolean isEditModeAdresse() {
		return editModeAdresse;
	}

	public void setEditModeAdresse(boolean editModeAdresse) {
		this.editModeAdresse = editModeAdresse;
	}

	public Address getNouvelleAdresse() {
		return nouvelleAdresse;
	}

	public void setNouvelleAdresse(Address nouvelleAdresse) {
		this.nouvelleAdresse = nouvelleAdresse;
	}

	public City getVille() {
		return ville;
	}

	public void setVille(City ville) {
		this.ville = ville;
	}

	public IProfil getProxyProfil() {
		return proxyProfil;
	}

	public void setProxyProfil(IProfil proxyProfil) {
		this.proxyProfil = proxyProfil;
	}

	public ProfilBean getProfilBean() {
		return profilBean;
	}

	public void setProfilBean(ProfilBean profilBean) {
		this.profilBean = profilBean;
	}
}
