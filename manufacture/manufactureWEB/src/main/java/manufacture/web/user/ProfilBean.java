package manufacture.web.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.Product;
import manufacture.entity.user.Address;
import manufacture.ifacade.user.IConnection;
import manufacture.ifacade.user.IProfil;

@ManagedBean(name="profilBean")
@SessionScoped
public class ProfilBean {
	
	private static Logger log = Logger.getLogger(ProfilBean.class);
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	@ManagedProperty(value="#{profil}")
	private IProfil proxyProfil; 
	
	private List<Address> adressesTotales;
	private List<Address> adressesFacturation = new ArrayList<Address>();
	private List<Address> adressesLivraison = new ArrayList<Address>();
	
	private List<Product> listeProduitsVendus = new ArrayList<Product>();
	
	private List<CartProduct> listeCommandesVendus = new ArrayList<CartProduct>();
	
	private List<Cart> listeCommandesPassees = new ArrayList<Cart>();

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date();

	//Méthodes
		
	public String accessProfil(){
		if(userBean.isLogged()){
			
			adressesTotales = proxyProfil.getAllAdressByUser(userBean.getUser());
			adressesFacturation = new ArrayList<Address>();
			adressesLivraison = new ArrayList<Address>();
			
			userBean.getUser().setAddresses(adressesTotales);
			
			listeProduitsVendus = proxyProfil.getProductSendByUser(userBean.getUser());
			
			listeCommandesVendus = proxyProfil.getCartSendByUser(userBean.getUser());
			
			listeCommandesPassees = proxyProfil.getCartByUser(userBean.getUser());
			
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
	public IProfil getProxyProfil() {
		return proxyProfil;
	}
	public void setProxyProfil(IProfil proxyProfil) {
		this.proxyProfil = proxyProfil;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Product> getListeProduitsVendus() {
		return listeProduitsVendus;
	}
	public void setListeProduitsVendus(List<Product> listeProduitsVendus) {
		this.listeProduitsVendus = listeProduitsVendus;
	}
	public List<Cart> getListeCommandesPassees() {
		return listeCommandesPassees;
	}
	public void setListeCommandesPassees(List<Cart> listeCommandesPassees) {
		this.listeCommandesPassees = listeCommandesPassees;
	}
	public List<CartProduct> getListeCommandesVendus() {
		return listeCommandesVendus;
	}
	public void setListeCommandesVendus(List<CartProduct> listeCommandesVendus) {
		this.listeCommandesVendus = listeCommandesVendus;
	}
}
