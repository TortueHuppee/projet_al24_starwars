package manufacture.web.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import manufacture.entity.user.Address;
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.ifacade.user.IConnection;
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
	
	private Address nouvelleAdresse = new Address();
	private City ville = new City();
	
	//Méthodes
	
	public void saveNewAddress()
	{
		nouvelleAdresse.setUser(userBean.getUser());
		nouvelleAdresse.setCity(ville);
		proxyProfil.saveAddress(nouvelleAdresse);
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
	   proxyProfil.editUser(userBean.getUser());
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
}
