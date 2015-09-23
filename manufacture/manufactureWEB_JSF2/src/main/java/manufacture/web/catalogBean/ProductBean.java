package manufacture.web.catalogBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.ifacade.catalog.ICatalog;
import manufacture.web.datas.DataLoader;

import org.apache.log4j.Logger;

@ManagedBean(name="newmbProduct")
@SessionScoped
public class ProductBean {

	private static Logger log = Logger.getLogger(ProductManagedBean.class);

	@ManagedProperty(value="#{catalog}")
	private ICatalog proxyCatalog;

	private static final Integer PRODUCT_PROFESSIONNEL_TYPE_ID = 1;
	private static final Integer PRODUCT_ARTISAN_TYPE_ID = 2;
	private static final Integer PRODUCT_OCCASION_TYPE_ID = 3;

	//Produit affiche
	private Product produitSelectionne;
	private List<Product> listeProduitsTotauxPourCeProduitRef = new ArrayList<>();
	private List<Product> listeProduitsDuVendeur = new ArrayList<>();
    private List<SpaceshipProduct> listeVaisseauxProduit;
    private List<SpaceshipRef> listeVaisseaux = new ArrayList<>();
    private List<Category> listeCategories = new ArrayList<>();

	//Filtres couleurs et materiaux
	private List<Color> listeCouleursDisponibles = new ArrayList<>();
	private List<Material> listeMateriauxDisponibles = new ArrayList<>();
	private Integer idColorSelected = 0;
	private Integer idMaterialSelected = 0;

	//Listes de produits identiques vendus par d'autres vendeurs
	private List<Product> listeProduitsDesAutresVendeurs = new ArrayList<>();
	private List<Product> listeProduitsIdentiques = new ArrayList<>();

	//Méthodes
	public String detailProduit(Produit produit)
	{	
		//Initialisation du produit selectionne et du produit ref
		produitSelectionne = new Product();
		produitSelectionne.setIdProduct(produit.getIdProduct());

		ProductRef produitRef = new ProductRef();
		produitRef = proxyCatalog.getProductRefByIdProductRef(produit.getIdProductRef());
		
		produitSelectionne.setProductRef(produitRef);

		initialisationDonnees();

		return "/pages/newdetailproduit.xhtml?faces-redirect=true";
	}
	
	public String detailProduit(Product product)
	{	
		//Initialisation du produit selectionne et du produit ref
		produitSelectionne = product;

		initialisationDonnees();

		return "/pages/newdetailproduit.xhtml?faces-redirect=true";
	}
	
	public void initialisationDonnees()
	{
		listeVaisseauxProduit = proxyCatalog.getSpaceShipProductByProduct(produitSelectionne.getProductRef());
        listeVaisseaux = new ArrayList<SpaceshipRef>();
        for (SpaceshipProduct ssp : listeVaisseauxProduit)
        {
            listeVaisseaux.add(ssp.getSpaceshipRef());
        }
        
		listeProduitsTotauxPourCeProduitRef = proxyCatalog.getAllProductByProductRef(produitSelectionne.getProductRef().getIdProductRef());

		for (Product produit : listeProduitsTotauxPourCeProduitRef)
		{
			if (produit.getIdProduct() == produitSelectionne.getIdProduct())
			{
				produitSelectionne = produit;
			}
		}
		for (Product produit : listeProduitsTotauxPourCeProduitRef)
		{
			if (produit.getIdProduct() != produitSelectionne.getIdProduct())
			{
				initialiserListeProduitsAnnexes(produit);
			}
		}
		
		initialisationFiltres();
		miseAJourListeMaterial();
	}
	
	public void initialiserListeProduitsAnnexes(Product produit) {
		/*
		 * 6 cas :
		 * 		** produit selectionne = produit constructeur
		 * 				** produit passe en argument = produit constructeur
		 * 						** produit passe en argument = meme constructeur  => liste de ce vendeur
		 * 						** produit passe en argument = autre constructeur  => liste autres vendeurs
		 * 				** produit passe en argument = produit user => liste autres vendeurs
		 * 		** produit selectionne = produit user
		 * 				** produit passe en argument = produit user
		 * 						** produit passe en argument = meme user => liste de ce vendeur
		 * 						** produit passe en argument = autre user  => liste autres vendeurs
		 * 				** produit passe en argument = produit constructeur  => liste autres vendeurs
		 */

		if (produitSelectionne.getTypeProduct().getIdTypeProduct() == PRODUCT_PROFESSIONNEL_TYPE_ID)
		{
			if (produit.getTypeProduct().getIdTypeProduct() == PRODUCT_PROFESSIONNEL_TYPE_ID)
			{
				if (produit.getConstructor().getIdConstructor() == produitSelectionne.getConstructor().getIdConstructor())
				{
					listeProduitsDuVendeur.add(produit);
				}
				else
				{
					listeProduitsDesAutresVendeurs.add(produit);
				}
			}
			else
			{
				listeProduitsDesAutresVendeurs.add(produit);
			}
		}
		else
		{
			if (produit.getTypeProduct().getIdTypeProduct() == PRODUCT_PROFESSIONNEL_TYPE_ID)
			{
				listeProduitsDesAutresVendeurs.add(produit);
			}
			else
			{
				if (produit.getUser().getIdUser() == produitSelectionne.getUser().getIdUser())
				{
					listeProduitsDuVendeur.add(produit);
				}
				else
				{
					listeProduitsDesAutresVendeurs.add(produit);
				}
			}
		}
	}
	
	public void initialisationFiltres()
	{
		listeCouleursDisponibles = new ArrayList<Color>();

		//Couleurs
		listeCouleursDisponibles = new ArrayList<Color>();
		boolean ajoutColor = true;
		for (Product product : listeProduitsDuVendeur)
		{			
			for (Color couleur : listeCouleursDisponibles)
			{
				if (couleur.getIdColor() == product.getColor().getIdColor())
				{
					ajoutColor = false;
				}
			}
			if (ajoutColor)
			{
				listeCouleursDisponibles.add(product.getColor());
			}
		}
		idColorSelected = produitSelectionne.getColor().getIdColor();
		
		miseAJourListeMaterial();
	}
	
	public void miseAJourListeMaterial()
	{
		listeMateriauxDisponibles = new ArrayList<Material>();

		//Matériaux
		boolean ajoutMateriaux = true;
		for (Product product : listeProduitsDuVendeur)
		{	
			if (product.getColor().getIdColor() == idColorSelected)
			{
				for (Material material : listeMateriauxDisponibles)
				{
					if (material.getIdMaterial() == product.getMaterial().getIdMaterial())
					{
						ajoutMateriaux = false;
					}
				}
				if (ajoutMateriaux)
				{
					listeMateriauxDisponibles.add(product.getMaterial());
				}
			}	
		}
		idMaterialSelected = produitSelectionne.getMaterial().getIdMaterial();
	}
	
	public void miseAJourListeProduitsIdentiques()
	{
		listeProduitsIdentiques = new ArrayList<>();
		for (Product produit : listeProduitsDesAutresVendeurs)
		{
			if (produit.getColor().getIdColor() == idColorSelected && produit.getMaterial().getIdMaterial() == idMaterialSelected)
			{
				listeProduitsIdentiques.add(produit);
			}
		}
	}

	public double DoubleFormat(double number)
	{
		number = number*100;
		number = (double)((int) number);
		number = number /100;

		return number;
	}

	public void filtrerListeProduitsParCouleur()
	{
		for (Product produit : listeProduitsDuVendeur)
		{
			if (produit.getColor().getIdColor() == idColorSelected)
			{
				produitSelectionne = produit;
			}
		}
		miseAJourListeMaterial();
		miseAJourListeProduitsIdentiques();
	}

	public void filtrerListeProduitsParMateriaux()
	{
		for (Product produit : listeProduitsDuVendeur)
		{
			if (produit.getMaterial().getIdMaterial() == idMaterialSelected && produit.getColor().getIdColor() == idColorSelected)
			{
				produitSelectionne = produit;
			}
		}
		miseAJourListeProduitsIdentiques();
	}

	//Getters et Setters
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		ProductBean.log = log;
	}
	public ICatalog getProxyCatalog() {
		return proxyCatalog;
	}
	public void setProxyCatalog(ICatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}
	public static Integer getProductProfessionnelTypeId() {
		return PRODUCT_PROFESSIONNEL_TYPE_ID;
	}
	public static Integer getProductArtisanTypeId() {
		return PRODUCT_ARTISAN_TYPE_ID;
	}
	public static Integer getProductOccasionTypeId() {
		return PRODUCT_OCCASION_TYPE_ID;
	}

	public Product getProduitSelectionne() {
		return produitSelectionne;
	}

	public void setProduitSelectionne(Product produitSelectionne) {
		this.produitSelectionne = produitSelectionne;
	}

	public List<Color> getListeCouleursDisponibles() {
		return listeCouleursDisponibles;
	}

	public void setListeCouleursDisponibles(List<Color> listeCouleursDisponibles) {
		this.listeCouleursDisponibles = listeCouleursDisponibles;
	}

	public List<Material> getListeMateriauxDisponibles() {
		return listeMateriauxDisponibles;
	}

	public void setListeMateriauxDisponibles(
			List<Material> listeMateriauxDisponibles) {
		this.listeMateriauxDisponibles = listeMateriauxDisponibles;
	}

	public List<Product> getListeProduitsTotauxPourCeProduitRef() {
		return listeProduitsTotauxPourCeProduitRef;
	}

	public void setListeProduitsTotauxPourCeProduitRef(
			List<Product> listeProduitsTotauxPourCeProduitRef) {
		this.listeProduitsTotauxPourCeProduitRef = listeProduitsTotauxPourCeProduitRef;
	}

	public List<Product> getListeProduitsDuVendeur() {
		return listeProduitsDuVendeur;
	}

	public void setListeProduitsDuVendeur(List<Product> listeProduitsDuVendeur) {
		this.listeProduitsDuVendeur = listeProduitsDuVendeur;
	}

	public List<Product> getListeProduitsDesAutresVendeurs() {
		return listeProduitsDesAutresVendeurs;
	}

	public void setListeProduitsDesAutresVendeurs(
			List<Product> listeProduitsDesAutresVendeurs) {
		this.listeProduitsDesAutresVendeurs = listeProduitsDesAutresVendeurs;
	}

	public Integer getIdColorSelected() {
		return idColorSelected;
	}

	public void setIdColorSelected(Integer idColorSelected) {
		this.idColorSelected = idColorSelected;
	}

	public Integer getIdMaterialSelected() {
		return idMaterialSelected;
	}

	public void setIdMaterialSelected(Integer idMaterialSelected) {
		this.idMaterialSelected = idMaterialSelected;
	}

	public List<Product> getListeProduitsIdentiques() {
		return listeProduitsIdentiques;
	}

	public void setListeProduitsIdentiques(List<Product> listeProduitsIdentiques) {
		this.listeProduitsIdentiques = listeProduitsIdentiques;
	}

	public List<SpaceshipProduct> getListeVaisseauxProduit() {
		return listeVaisseauxProduit;
	}

	public void setListeVaisseauxProduit(
			List<SpaceshipProduct> listeVaisseauxProduit) {
		this.listeVaisseauxProduit = listeVaisseauxProduit;
	}

	public List<SpaceshipRef> getListeVaisseaux() {
		return listeVaisseaux;
	}

	public void setListeVaisseaux(List<SpaceshipRef> listeVaisseaux) {
		this.listeVaisseaux = listeVaisseaux;
	}

	public List<Category> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Category> listeCategories) {
		this.listeCategories = listeCategories;
	}
}
