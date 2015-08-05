package manufacture.web.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import manufacture.entity.user.Address;
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.ifacade.user.IConnection;

@ManagedBean(name="editBean")
@ViewScoped
public class EditManagedBean {

	private boolean editModePersonnel;
	private boolean editModeAdresse;

	@ManagedProperty(value="#{connection}")
	private IConnection proxyConnection;
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	private Address nouvelleAdresse = new Address();
	private City ville = new City();
	
	//Méthodes
	
	public void saveNewAddress()
	{
		nouvelleAdresse.setUser(userBean.getUser());
		nouvelleAdresse.setCity(ville);
		proxyConnection.saveAddress(nouvelleAdresse);
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
	   proxyConnection.editUser(userBean.getUser());
	   editModePersonnel = false;
	}
	
	public void saveAdresse(Address addresse) {
		proxyConnection.editAddress(addresse);
		editModeAdresse = false;
	}

	//Getters et Setters
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public IConnection getProxyConnection() {
		return proxyConnection;
	}

	public void setProxyConnection(IConnection proxyConnection) {
		this.proxyConnection = proxyConnection;
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
}
