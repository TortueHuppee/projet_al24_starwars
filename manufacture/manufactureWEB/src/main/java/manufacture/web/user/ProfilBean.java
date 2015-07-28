package manufacture.web.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="profilBean")
@SessionScoped
public class ProfilBean {
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	

	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	public String accessProfil(){
		if(userBean.isLogged()){
			return "profil.xhtml?faces-redirect=true";
		}else{
			loginBean.setRedirect("profil.xhtml?faces-redirect=true");
			return "login.xhtml?faces-redirect=true";
		}
	}
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
}
