package manufacture.web.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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
	
	public void editModePersonnel() {
		editModePersonnel = true;
	}
	
	public void cancelModePersonnel() {
		editModePersonnel = false;
	}
	
	public void editModeAdresse() {
		editModeAdresse = true;
	}
	
	public void cancelModeAdresse() {
		editModeAdresse = false;
	}

	public void saveUser() {
	   proxyConnection.editUser(userBean.getUser());
	   editModePersonnel = false;
	}
	
	public void saveAdresse() {
		   //proxyConnection.editUser(userBean.getUser());
		   editModeAdresse = false;
		}

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
}
