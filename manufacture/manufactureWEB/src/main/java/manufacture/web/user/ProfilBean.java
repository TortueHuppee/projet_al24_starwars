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
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.ifacade.user.IProfil;
import manufacture.web.datas.DataLoader;

import org.apache.log4j.Logger;

@ManagedBean(name="profilBean")
@SessionScoped
public class ProfilBean {
	
	private static Logger log = Logger.getLogger(ProfilBean.class);
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty(value="#{profil}")
	private IProfil proxyProfil; 
	
	@ManagedProperty(value="#{mbDataLoader}")
	private DataLoader dataloader;
	
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
	
	//Methodes
	public void afficherInformationsAdresses()
	{
	    for (Address ad : adressesTotales)
	    {
	        log.info("ID Ville : " + ad.getCity().getIdCity());
	        log.info("ID Pays : " + ad.getCity().getCountry().getIdCountry());
	        log.info("ID Planète : " + ad.getCity().getCountry().getPlanet().getIdPlanet());
	    }
	}
	
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
			}
			if (adresse.getIsDeliveryaddress())
			{
				adressesLivraison.add(adresse);
			}
			
//			for (City city : dataloader.getListCity())
//			{
//			    if (adresse.getCity().getIdCity() == city.getIdCity())
//			    {
//			        adresse.setCity(city);
//			        log.info(city.getCityName());
//			    }
//			    
//			    for (Country country : dataloader.getListCountry())
//	            {
//	                for (Planet planet : dataloader.getListPlanet())
//	                {
//	                    if (country.getPlanet().getIdPlanet() == planet.getIdPlanet())
//	                    {
//	                        country.setPlanet(planet);
//	                    }
//	                }
//	                
//	                if (city.getCountry().getIdCountry() == country.getIdCountry())
//	                {
//	                    city.setCountry(country);
//	                }
//	            }
//			}
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

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger paramLog) {
        log = paramLog;
    }

    public DataLoader getDataloader() {
        return dataloader;
    }

    public void setDataloader(DataLoader paramDataloader) {
        dataloader = paramDataloader;
    }
}
