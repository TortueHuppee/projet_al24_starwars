package manufacture.web.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import manufacture.entity.user.Address;
import manufacture.ifacade.user.IConnection;

@ManagedBean(name="profilBean")
@SessionScoped
public class ProfilBean {
	
	private static Logger log = Logger.getLogger(ProfilBean.class);
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	@ManagedProperty(value="#{connection}")
	private IConnection proxyConnection; 
	
	private List<Address> adressesTotales;
	private List<Address> adressesFacturation = new ArrayList<Address>();
	private List<Address> adressesLivraison = new ArrayList<Address>();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date();
	
	public String accessProfil(){
		if(userBean.isLogged()){
			adressesTotales = proxyConnection.getAllAdressByUser(userBean.getUser());
			userBean.getUser().setAddresses(adressesTotales);
			date = userBean.getUser().getCreateTime();
			
			for (Address adresse : adressesTotales)
			{
				if (adresse.getIsBillingaddress())
				{
					adressesFacturation.add(adresse);
					log.info("Facturation : " + adresse.getStreet());
				}
				if (adresse.getIsDeliveryaddress())
				{
					adressesLivraison.add(adresse);
					log.info("Livraison : " + adresse.getStreet());
				}
			}
			
			return "profil.xhtml?faces-redirect=true";
		}else{
			date = new Date();
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
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	public IConnection getProxyConnection() {
		return proxyConnection;
	}
	public void setProxyConnection(IConnection proxyConnection) {
		this.proxyConnection = proxyConnection;
	}
	public List<Address> getAdressesFacturation() {
		return adressesFacturation;
	}
	public void setAdressesFacturation(List<Address> adressesFacturation) {
		this.adressesFacturation = adressesFacturation;
	}
	public List<Address> getAdressesLivraison() {
		return adressesLivraison;
	}
	public void setAdressesLivraison(List<Address> adressesLivraison) {
		this.adressesLivraison = adressesLivraison;
	}
	public List<Address> getAdressesTotales() {
		return adressesTotales;
	}
	public void setAdressesTotales(List<Address> adressesTotales) {
		this.adressesTotales = adressesTotales;
	}
}
