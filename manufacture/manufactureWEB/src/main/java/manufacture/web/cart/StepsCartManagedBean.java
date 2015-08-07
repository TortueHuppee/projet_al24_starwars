package manufacture.web.cart;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.RelayPoint;
import manufacture.entity.user.Address;
import manufacture.ifacade.cart.IGestionCart;
import manufacture.ifacade.user.IProfil;
import manufacture.web.user.LoginBean;
import manufacture.web.user.ProfilBean;
import manufacture.web.user.UserBean;

@ManagedBean(name = "mbSteps")
@SessionScoped
public class StepsCartManagedBean {

	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	@ManagedProperty(value="#{profilBean}")
	private ProfilBean profilBean;
	
	@ManagedProperty(value="#{mbCart}")
	private ManagedBeanCart mbCart;
	
	@ManagedProperty(value = "#{gestionCart}")
    private IGestionCart proxyCart;
	
	@ManagedProperty(value="#{profil}")
	private IProfil proxyProfil;

	private static Logger log = Logger.getLogger(StepsCartManagedBean.class);
	
	//Step 1
	
	private List<CartProduct> listeProduitsAutorises;
	private List<CartProduct> listeProduitsNonAutorises;
	
	private static final Integer USER_PARTICULIER_ROLE_ID = 1;
	private static final Integer USER_PROFESSIONNEL_ROLE_ID = 2;
	private static final Integer USER_ARTISAN_ROLE_ID = 3;
	
	private static final Integer PRODUCT_CONSTRUCTEUR_TYPE_ID = 1;
	private static final Integer PRODUCT_ARTISAN_TYPE_ID = 2;
	private static final Integer PRODUCT_OCCASION_TYPE_ID = 3;

	private double cartPrice;
	
	//Step 2
	
	private static final Integer DELIVERY_RELAY_POINTS_ID = 4;
	
	private List<Delivery> moyensDeLivraisons;
	private List<RelayPoint> listePointsRelais;
	private Address adresseLivraison;
	private int idAdressePersonnelle = 0;
	private int idAdressePointRelais = 0;
	
	//Step 3
	
	private double totalPrice;
	private Address adresseFacturation;
	
	//Step 4
	
	
	@PostConstruct
	public void init()
	{
		moyensDeLivraisons = proxyCart.getAllDelivery();
		listePointsRelais = proxyCart.getAllRelayPoints();
	}
	
	// Méthodes
	
	public String goToStep3()
	{
		adresseLivraison = new Address();
		
		if (mbCart.getCart().getDelivery().getIdDelivery() == DELIVERY_RELAY_POINTS_ID)
		{
			adresseLivraison.setIdAddress(idAdressePointRelais);
		}
		else
		{
			adresseLivraison.setIdAddress(idAdressePersonnelle);
		}
		
		return "panierStep3.xhtml?faces-redirect=true";
	}
	
	public double calculePrixTotal()
	{
		double prixTransport = 0;
		
		for (Delivery d : moyensDeLivraisons)
		{
			if (d.getIdDelivery() == mbCart.getCart().getDelivery().getIdDelivery())
			{
				prixTransport = d.getDeliveryPrice();
			}
		}
		totalPrice = cartPrice + prixTransport;
		return totalPrice;
	}
	
	public void initialisationDonnees()
	{
		if (userBean.getUser().getUserRole().getIdUserRole() == USER_PARTICULIER_ROLE_ID)
		{
			listeProduitsAutorises = mbCart.getPanier();
		} else if (userBean.getUser().getUserRole().getIdUserRole() == USER_PROFESSIONNEL_ROLE_ID)
		{
			listeProduitsAutorises = new ArrayList<CartProduct>();
			listeProduitsNonAutorises = new ArrayList<CartProduct>();
			
			for (CartProduct cp : mbCart.getPanier())
			{
				if (cp.getProduct().getTypeProduct().getIdTypeProduct() == PRODUCT_CONSTRUCTEUR_TYPE_ID)
				{
					listeProduitsAutorises.add(cp);
				} else
				{
					listeProduitsNonAutorises.add(cp);
				}
			}
		}
		
		if (!profilBean.isDonneesInitialisees())
		{
			profilBean.initialiseDonnees();
			profilBean.setDonneesInitialisees(true); ;
		}
		
		if (mbCart.getCart().getDelivery().getIdDelivery() != DELIVERY_RELAY_POINTS_ID)
		{
			//Adresse de livraison
			if (profilBean.getAdressesLivraison().size() > 0)
			{
				adresseLivraison = profilBean.getAdressesLivraison().get(0);
			}
			else
			{
				adresseLivraison = new Address();
			}
			
			//Adresse de facturation
			if (profilBean.getAdressesFacturation().size() > 0)
			{
				adresseFacturation = profilBean.getAdressesFacturation().get(0);
			}
			else
			{
				adresseFacturation = new Address();
			}
		}
	}
	
	public String goToStep1(){
		if(!userBean.isLogged()){
			FacesMessage fm = new FacesMessage("Erreur", "Vous devez vous connecter pour continuer la commande");
			FacesContext.getCurrentInstance().addMessage(null, fm);
			loginBean.setRedirect("panierStep1.xhtml?faces-redirect=true");
			return "login.xhtml?faces-redirect=true";
		}
		
		if (userBean.getUser().getUserRole().getIdUserRole() == USER_PARTICULIER_ROLE_ID || userBean.getUser().getUserRole().getIdUserRole() == USER_PROFESSIONNEL_ROLE_ID)
		{
			initialisationDonnees();
			return "panierStep1.xhtml?faces-redirect=true";
		}
		else
		{
			return "panierNonAutorise.xhtml?faces-redirect=true";
		}
	}

	public void augmenterQuantite(int idProduct)
	{
		mbCart.incrementProductQuantity(idProduct);
		initialisationDonnees();
	}
	
	public void diminueQuantite(int idProduct)
	{
		mbCart.decrementProductQuantity(idProduct);
		initialisationDonnees();
	}
	
	public void modifieQuantite(int idProduct, int newQuantity)
	{
		mbCart.upadateProductQuantityWithSpinner(idProduct, newQuantity);
		initialisationDonnees();
	}
	
	public double getTotalPrice() {
		cartPrice = 0 ;
		totalPrice = 0;
		for (CartProduct cp : listeProduitsAutorises) {
			cartPrice += cp.getProduct().getPrice() * cp.getQuantity();
		}
		totalPrice += cartPrice;
		return cartPrice;
	}

	//Getters et Setters
	
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

	public ManagedBeanCart getMbCart() {
		return mbCart;
	}

	public void setMbCart(ManagedBeanCart mbCart) {
		this.mbCart = mbCart;
	}

	public List<CartProduct> getListeProduitsAutorises() {
		return listeProduitsAutorises;
	}

	public void setListeProduitsAutorises(List<CartProduct> listeProduitsAutorises) {
		this.listeProduitsAutorises = listeProduitsAutorises;
	}

	public List<CartProduct> getListeProduitsNonAutorises() {
		return listeProduitsNonAutorises;
	}

	public void setListeProduitsNonAutorises(
			List<CartProduct> listeProduitsNonAutorises) {
		this.listeProduitsNonAutorises = listeProduitsNonAutorises;
	}

	public double getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(double cartPrice) {
		this.cartPrice = cartPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public IGestionCart getProxyCart() {
		return proxyCart;
	}

	public void setProxyCart(IGestionCart proxyCart) {
		this.proxyCart = proxyCart;
	}

	public List<Delivery> getMoyensDeLivraisons() {
		return moyensDeLivraisons;
	}

	public void setMoyensDeLivraisons(List<Delivery> moyensDeLivraisons) {
		this.moyensDeLivraisons = moyensDeLivraisons;
	}

	public List<RelayPoint> getListePointsRelais() {
		return listePointsRelais;
	}

	public void setListePointsRelais(List<RelayPoint> listePointsRelais) {
		this.listePointsRelais = listePointsRelais;
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

	public Address getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(Address adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public Address getAdresseFacturation() {
		return adresseFacturation;
	}

	public void setAdresseFacturation(Address adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}

	public int getIdAdressePersonnelle() {
		return idAdressePersonnelle;
	}

	public void setIdAdressePersonnelle(int idAdressePersonnelle) {
		this.idAdressePersonnelle = idAdressePersonnelle;
	}

	public int getIdAdressePointRelais() {
		return idAdressePointRelais;
	}

	public void setIdAdressePointRelais(int idAdressePointRelais) {
		this.idAdressePointRelais = idAdressePointRelais;
	}

}
