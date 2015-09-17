package manufacture.web.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.Product;
import manufacture.entity.user.Address;
import manufacture.entity.user.City;
import manufacture.entity.user.Civility;
import manufacture.ifacade.advert.IAdvert;
import manufacture.ifacade.catalog.ICatalog;
import manufacture.ifacade.user.IProfil;
import manufacture.web.catalogBean.CatalogManagedBean;
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

	@ManagedProperty(value="#{advert}")
	private IAdvert proxyAdvert;

	@ManagedProperty(value="#{mbCatalog}")
	private CatalogManagedBean catalogBean;

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
	private List<Civility> listeCivilites;

	private int idDerniereAnnonceModifiee;

	private boolean donneesInitialisees = false;

	@PostConstruct
	public void init() {
		if(userBean.isLogged()){
			initialiseDonnees();
			donneesInitialisees = true;
		}
	}

	//Methodes
	public void chooseRubrique(String rubrique)
	{
		rubriqueChoisie = rubrique;
	}

	public void initialiseDonnees()
	{
		date = userBean.getUser().getCreateTime();
		listeCivilites = new ArrayList<>();
		listeCivilites = proxyProfil.getAllCivility();
		idDerniereAnnonceModifiee = 0;

		initialiserAchats();
		initialiserAdresses();
		initialiserVentes();

		donneesInitialisees = true;
	}

	public void initialiserVentes()
	{
		listeCommandesVendus = new ArrayList<CartProduct>();

		listeProduitsNonVendus = new ArrayList<Product>();
		listeProduitsVendus = new ArrayList<Product>();

		listeProduitsVendus = proxyProfil.getProductSendByUser(userBean.getUser());

		listeProduitsNonVendus = proxyProfil.getProductNotSendByUser(userBean.getUser());

		listeCommandesVendus = proxyProfil.getCartSendByUser(userBean.getUser());
	}

	public void initialiserAchats()
	{
		listeCommandesPassees = new ArrayList<Cart>();

		listeCommandesPassees = proxyProfil.getCartByUser(userBean.getUser());

		for (Cart cart : listeCommandesPassees)
		{
			cart.setCartProducts(proxyProfil.getCartProductByCart(cart));
		}
	}

	public void initialiserAdresses()
	{
		adressesTotales = new ArrayList<Address>();
		adressesFacturation = new ArrayList<Address>();
		adressesLivraison = new ArrayList<Address>();

		adressesTotales = proxyProfil.getAllAdressByUser(userBean.getUser());

		userBean.getUser().setAddresses(adressesTotales);

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
		}
	}

	public String getCivilityById(int idCivility)
	{
		for (Civility civilite : listeCivilites)
		{
			if (civilite.getIdCivility() == idCivility)
			{
				return civilite.getCivility();
			}
		}
		return "Donnée indisponible";
	}

	public String getPostalCodeById(int idCity)
	{
		for (City city : dataloader.getListCity())
		{
			if (city.getIdCity() == idCity)
			{
				return city.getPostalCode() + "";
			}
		}
		return "Ville inconnue";
	}

	public void toggleAdvertState(Product product)
	{
		idDerniereAnnonceModifiee = product.getIdProduct();

		product.setOnLine(!product.isOnLine());

		FacesContext context = FacesContext.getCurrentInstance();

		if (product.isOnLine())
		{
			context.addMessage(null, new FacesMessage("Modification effectuée", "L'annonce pour le produit " + product.getProductRef().getProductName() + " a été désactivée." ) );
		}
		else
		{
			context.addMessage(null, new FacesMessage("Modification effectuée", "L'annonce pour le produit " + product.getProductRef().getProductName() + " a été mise en ligne." ) );
		}

		proxyAdvert.updateAdvertState(product);	
		catalogBean.initialisationListesAffichees();
	}

	//Getters et Setters

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

	public List<Civility> getListeCivilites() {
		return listeCivilites;
	}

	public void setListeCivilites(List<Civility> listeCivilites) {
		this.listeCivilites = listeCivilites;
	}

	public int getIdDerniereAnnonceModifiee() {
		return idDerniereAnnonceModifiee;
	}

	public void setIdDerniereAnnonceModifiee(int idDerniereAnnonceModifiee) {
		this.idDerniereAnnonceModifiee = idDerniereAnnonceModifiee;
	}

	public IAdvert getProxyAdvert() {
		return proxyAdvert;
	}

	public void setProxyAdvert(IAdvert proxyAdvert) {
		this.proxyAdvert = proxyAdvert;
	}

	public CatalogManagedBean getCatalogBean() {
		return catalogBean;
	}

	public void setCatalogBean(CatalogManagedBean catalogBean) {
		this.catalogBean = catalogBean;
	}
}
