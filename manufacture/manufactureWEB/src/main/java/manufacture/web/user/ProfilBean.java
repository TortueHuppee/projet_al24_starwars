package manufacture.web.user;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import manufacture.entity.user.Address;
import manufacture.entity.user.City;
import manufacture.entity.user.User;
import manufacture.ifacade.user.IConnection;
import manufacture.ifacade.user.IInscription;
import manufacture.web.util.ClassPathLoader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

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
