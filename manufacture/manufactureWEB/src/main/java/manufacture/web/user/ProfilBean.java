package manufacture.web.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.Product;
import manufacture.entity.user.Address;
import manufacture.ifacade.user.IProfil;

import org.apache.log4j.Logger;

@ManagedBean(name="profilBean")
@SessionScoped
public class ProfilBean {
	
	private static Logger log = Logger.getLogger(ProfilBean.class);
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty(value="#{profil}")
	private IProfil proxyProfil; 
	
	private List<Address> adressesTotales;
	private List<Address> adressesFacturation;
	private List<Address> adressesLivraison;
	
	private List<Product> listeProduitsVendus;
	private List<Product> listeProduitsNonVendus;
	
	private List<CartProduct> listeCommandesVendus;
	
	private List<Cart> listeCommandesPassees;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date();
	
	private String rubriqueChoisie;
	
	private boolean donneesInitialisees = false;

	@PostConstruct
	public void init() {
	    if(userBean.isLogged()){
			initialiseDonnees();
			donneesInitialisees = true;
		}
	}
	
	//Méthodes
	public void chooseRubrique(String rubrique)
	{
	    rubriqueChoisie = rubrique;
	}
	
	public void initialiseDonnees()
	{
	    adressesTotales = new ArrayList<Address>();
	    adressesFacturation = new ArrayList<Address>();
	    adressesLivraison = new ArrayList<Address>();
	    
	    listeCommandesPassees = new ArrayList<Cart>();
	    listeCommandesVendus = new ArrayList<CartProduct>();
	    
	    listeProduitsNonVendus = new ArrayList<Product>();
	    listeProduitsVendus = new ArrayList<Product>();
	    
		adressesTotales = proxyProfil.getAllAdressByUser(userBean.getUser());
		
		log.info(adressesTotales.size());
		System.out.println(adressesTotales.size());
		
		userBean.getUser().setAddresses(adressesTotales);
		
		listeProduitsVendus = proxyProfil.getProductSendByUser(userBean.getUser());
		
		listeProduitsNonVendus = proxyProfil.getProductNotSendByUser(userBean.getUser());
		
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
	}

	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
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
//		return proxyProfil.getBillingAddressByUser(userBean.getUser());
	}
	public void setAdressesFacturation(List<Address> adressesFacturation) {
		this.adressesFacturation = adressesFacturation;
	}
	public List<Address> getAdressesLivraison() {
		return adressesLivraison;
//		return proxyProfil.getDeliveryAddressByUser(userBean.getUser());
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
	public List<Product> getListeProduitsNonVendus() {
		return listeProduitsNonVendus;
	}
	public void setListeProduitsNonVendus(List<Product> listeProduitsNonVendus) {
		this.listeProduitsNonVendus = listeProduitsNonVendus;
	}

	public boolean isDonneesInitialisees() {
		return donneesInitialisees;
	}

	public void setDonneesInitialisees(boolean donneesInitialisees) {
		this.donneesInitialisees = donneesInitialisees;
	}

    public String getRubriqueChoisie() {
        return rubriqueChoisie;
    }

    public void setRubriqueChoisie(String paramRubriqueChoisie) {
        rubriqueChoisie = paramRubriqueChoisie;
    }
}
