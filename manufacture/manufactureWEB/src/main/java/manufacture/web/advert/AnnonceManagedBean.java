package manufacture.web.advert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import manufacture.entity.product.Color;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.TypeProduct;
import manufacture.ifacade.advert.IAdvert;
import manufacture.ifacade.catalog.ICatalog;
import manufacture.web.cart.PaymentBean;
import manufacture.web.catalogBean.CatalogManagedBean;
import manufacture.web.user.LoginBean;
import manufacture.web.user.UserBean;

import org.apache.log4j.Logger;

@ManagedBean(name = "mbAnnonce")
@SessionScoped
public class AnnonceManagedBean {

	private Logger log = Logger.getLogger(PaymentBean.class);

	/**
	 * Constantes
	 */

	private static final Integer PRODUCT_ARTISAN_TYPE_ID = 2;
	private static final Integer PRODUCT_OCCASION_TYPE_ID = 3;
	
	private static final Integer USER_ARTISAN_ROLE_ID = 3;
	private static final Integer USER_PARTICULIER_ROLE_ID = 1;

	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;

	@ManagedProperty(value="#{catalog}")
	private ICatalog proxyCatalog;
	
	@ManagedProperty(value="#{advert}")
	private IAdvert proxyAdvert;
	
	@ManagedProperty(value="#{mbCatalog}")
	private CatalogManagedBean catalogBean;

	private List<ProductRef> listeProduitRef;

	private Product newProduct;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date();

	/**
	 * Messages d'erreurs :
	 */
	private String messageErreur = "";

	@PostConstruct
	public void init()
	{
		listeProduitRef = proxyCatalog.getAllProductRef();
		
		newProduct = new Product();
		
		newProduct.setUser(userBean.getUser());
		newProduct.setTypeProduct(new TypeProduct());
		if (userBean.getUser().getUserRole().getIdUserRole() == USER_PARTICULIER_ROLE_ID)
		{
			newProduct.getTypeProduct().setIdTypeProduct(PRODUCT_OCCASION_TYPE_ID);
		}
		else if (userBean.getUser().getUserRole().getIdUserRole() == USER_ARTISAN_ROLE_ID)
		{
			newProduct.getTypeProduct().setIdTypeProduct(PRODUCT_ARTISAN_TYPE_ID);
		}
		
		newProduct.setProductRef(new ProductRef());
		newProduct.setColor(new Color());
		newProduct.setMaterial(new Material());
		newProduct.setSellerComment("");
		
		newProduct.setPrice(1);
		newProduct.setStock(1);
				
		newProduct.setDatePublication(date);
		newProduct.setDisabled(false);
	}

	// Méthodes
	public String validationFormulaire()
	{
		if (newProduct.getSellerComment().equals(""))
		{
			messageErreur = "Veuillez rentrer un commentaire pour écrire votre produit.";
		}
		else
		{
			messageErreur = "";
			return createAdvert();
		}
		return null;
	}

	public String createAdvert()
	{
		proxyAdvert.createAdvert(newProduct);
		catalogBean.getListeProductBrute().add(newProduct);
		catalogBean.initialisationListesBrutes();
		catalogBean.initialisationListesAffichees();
		
		return "annonceEnregistree.xhtml?faces-redirect=true";
	}

	public String submitAdvert()
	{
		if(!userBean.isLogged())
		{
			FacesMessage fm = new FacesMessage("Erreur", "Vous devez vous connecter pour déposer une annonce");
			FacesContext.getCurrentInstance().addMessage(null, fm);
			loginBean.setRedirect("annonce.xhtml?faces-redirect=true");
			return "login.xhtml?faces-redirect=true";
		} 
		else
		{
			if (userBean.getUser().getUserRole().getIdUserRole() == 1 || userBean.getUser().getUserRole().getIdUserRole() == 3)
			{
				return "annonce.xhtml?faces-redirect=true";
			}
			else
			{
				return "annonceNonAutorisee.xhtml?faces-redirect=true";
			}

		}
	}

	// Getters et Setters

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

	public ICatalog getProxyCatalog() {
		return proxyCatalog;
	}

	public void setProxyCatalog(ICatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}

	public List<ProductRef> getListeProduitRef() {
		return listeProduitRef;
	}

	public void setListeProduitRef(List<ProductRef> listeProduitRef) {
		this.listeProduitRef = listeProduitRef;
	}

	public Product getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(Product newProduct) {
		this.newProduct = newProduct;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessageErreur() {
		return messageErreur;
	}

	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
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
