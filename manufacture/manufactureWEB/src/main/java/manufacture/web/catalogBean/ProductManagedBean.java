package manufacture.web.catalogBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.Material;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.ifacade.catalog.ICatalog;
import manufacture.web.util.ClassPathLoader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;

@ManagedBean(name="mbProduct")
@SessionScoped
public class ProductManagedBean {

	private Logger log = Logger.getLogger(ProductManagedBean.class);

	@ManagedProperty(value="#{catalog}")
	private ICatalog proxyCatalog;
	
	private int idProductRef;
	private ProductRef productRef;
	private ConstructorProduct produitAffiche;
	private List<ConstructorProduct> listeProduitsTotaux;
	private List<ConstructorProduct> listeProduitsAffiches;
	private int quantiteDispo;
	
	private List<Color> listeCouleurs;
	private List<Material> listeMateriaux;
	private List<Constructor> listeConstructeurs;
	private List<SpaceshipProduct> listeVaisseauxProduit;
	private List<SpaceshipRef> listeVaisseaux;
	
	private int idColorSelected;
	private int idMaterialSelected;
	private int idConstructorSelected;
	
	void init()
	{
		//photo, nom, description, catégorie, modèles vaisseaux
//		if (productRef == null)
//		{
//			idProductRef = 47;
//			productRef = proxyCatalog.getProductRefById(idProductRef);
//		}
		listeVaisseauxProduit = proxyCatalog.getSpaceShipProductByProduct(productRef);
		listeVaisseaux = new ArrayList<SpaceshipRef>();
		for (SpaceshipProduct ssp : listeVaisseauxProduit)
		{
			listeVaisseaux.add(ssp.getSpaceshipRef());
		}
		
		//prix, options disponibles (couleur, matériaux, constructeur)
		listeProduitsTotaux = proxyCatalog.getAllProductByProductRef(idProductRef);
		listeProduitsAffiches = listeProduitsTotaux;
		
		produitAffiche = listeProduitsTotaux.get(0);			

		//options
		idColorSelected = produitAffiche.getColor().getIdColor();
		idMaterialSelected = produitAffiche.getMaterial().getIdMaterial();
		idConstructorSelected = produitAffiche.getConstructor().getIdConstructor();
		
		//Couleurs
		listeCouleurs = new ArrayList<Color>();
		boolean ajoutColor = true;
		for (ConstructorProduct product : listeProduitsTotaux)
		{			
			for (Color color : listeCouleurs)
			{
				if (color.getIdColor() == product.getColor().getIdColor())
				{
					ajoutColor = false;
				}
			}
			if (ajoutColor)
			{
				listeCouleurs.add(product.getColor());
			}
		}
		quantiteDisponibleEtProduitSelectionne();
	}

	//Méthodes	
	
	public String detailProduit(int idProduit)
	{
		this.idProductRef = idProduit;
		this.productRef = proxyCatalog.getProductRefById(idProductRef);
		init();
		return "/pages/detailproduit.xhtml?faces-redirect=true";
	}
	
	public void filtrerListeProduitsParCouleur()
	{
		quantiteDispo = 0;
		listeProduitsAffiches = new ArrayList<ConstructorProduct>();

		for (ConstructorProduct product : listeProduitsTotaux)
		{
			if (product.getColor().getIdColor() == idColorSelected)
			{
				listeProduitsAffiches.add(product);
				quantiteDispo += product.getStock();
			}	
		}
		produitAffiche = listeProduitsAffiches.get(0);
		idMaterialSelected = produitAffiche.getMaterial().getIdMaterial();
		idConstructorSelected = produitAffiche.getConstructor().getIdConstructor();
		initialisationListes();
	}
	
	public void filtrerListeProduitsParMateriaux()
	{
		quantiteDispo = 0;
		listeProduitsAffiches = new ArrayList<ConstructorProduct>();

		for (ConstructorProduct product : listeProduitsTotaux)
		{
			if (product.getColor().getIdColor() == idColorSelected)
			{
				if (product.getMaterial().getIdMaterial() == idMaterialSelected)
				{
					listeProduitsAffiches.add(product);
					quantiteDispo += product.getStock();
				}
			}
		}
		produitAffiche = listeProduitsAffiches.get(0);
		idColorSelected = produitAffiche.getColor().getIdColor();
		idConstructorSelected = produitAffiche.getConstructor().getIdConstructor();
		initialisationListes();
	}
	
	public void filtrerListeProduitsParConstructeur()
	{
		quantiteDispo = 0;
		listeProduitsAffiches = new ArrayList<ConstructorProduct>();

		for (ConstructorProduct product : listeProduitsTotaux)
		{
			if (product.getColor().getIdColor() == idColorSelected)
			{
				if (product.getMaterial().getIdMaterial() == idMaterialSelected)
				{
					if (product.getConstructor().getIdConstructor() == idConstructorSelected)
					{
						listeProduitsAffiches.add(product);
						quantiteDispo += product.getStock();
					}
				}
			}
		}
		produitAffiche = listeProduitsAffiches.get(0);
		idColorSelected = produitAffiche.getColor().getIdColor();
		idMaterialSelected = produitAffiche.getMaterial().getIdMaterial();
		initialisationListes();
	}
	
	public void quantiteDisponibleEtProduitSelectionne()
	{
		quantiteDispo = 0;
		
		for (ConstructorProduct product : listeProduitsTotaux)
		{
			//if (idColorSelected == 00 || product.getColor().getIdColor() == idColorSelected)
			if (product.getColor().getIdColor() == idColorSelected)
			{
				if (product.getMaterial().getIdMaterial() == idMaterialSelected)
				{
					if (product.getConstructor().getIdConstructor() == idConstructorSelected)
					{
						quantiteDispo += product.getStock();
						produitAffiche = product;
					}
				}
			}	
		}
		initialisationListes();
	}
	
	public void initialisationListes()
	{
		listeMateriaux = new ArrayList<Material>();
		listeConstructeurs = new ArrayList<Constructor>();

		for (ConstructorProduct product : listeProduitsTotaux)
		{	
			boolean ajoutMateriaux = true;
			boolean ajoutConstructeur = true;
						
			//Matériaux
			if (product.getColor().getIdColor() == idColorSelected)
			{
				for (Material material : listeMateriaux)
				{
					if (material.getIdMaterial() == product.getMaterial().getIdMaterial())
					{
						ajoutMateriaux = false;
					}
				}
				if (ajoutMateriaux)
				{
					listeMateriaux.add(product.getMaterial());
				}
				
				//Constructeurs
				if (product.getMaterial().getIdMaterial() == idMaterialSelected)
				{
					for (Constructor constructeur : listeConstructeurs)
					{
						if (constructeur.getIdConstructor() == product.getConstructor().getIdConstructor())
						{
							ajoutConstructeur = false;
						}
					}
					if (ajoutConstructeur)
					{
						listeConstructeurs.add(product.getConstructor());
					}
				}
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
	
	//Getters et Setters

	public int getIdProductRef() {
		return idProductRef;
	}

	public void setIdProductRef(int idProductRef) {
		this.idProductRef = idProductRef;
	}

	public ProductRef getProductRef() {
		return productRef;
	}

	public void setProductRef(ProductRef productRef) {
		this.productRef = productRef;
	}

	public int getQuantiteDispo() {
		return quantiteDispo;
	}

	public void setQuantiteDispo(int quantiteDispo) {
		this.quantiteDispo = quantiteDispo;
	}

	public List<Color> getListeCouleurs() {
		return listeCouleurs;
	}

	public void setListeCouleurs(List<Color> listeCouleurs) {
		this.listeCouleurs = listeCouleurs;
	}

	public List<Material> getListeMateriaux() {
		return listeMateriaux;
	}

	public void setListeMateriaux(List<Material> listeMateriaux) {
		this.listeMateriaux = listeMateriaux;
	}

	public List<Constructor> getListeConstructeurs() {
		return listeConstructeurs;
	}

	public void setListeConstructeurs(List<Constructor> listeConstructeurs) {
		this.listeConstructeurs = listeConstructeurs;
	}

	public int getIdColorSelected() {
		return idColorSelected;
	}

	public void setIdColorSelected(int idColorSelected) {
		this.idColorSelected = idColorSelected;
	}

	public int getIdMaterialSelected() {
		return idMaterialSelected;
	}

	public void setIdMaterialSelected(int idMaterialSelected) {
		this.idMaterialSelected = idMaterialSelected;
	}

	public int getIdConstructorSelected() {
		return idConstructorSelected;
	}

	public void setIdConstructorSelected(int idConstructorSelected) {
		this.idConstructorSelected = idConstructorSelected;
	}
	
	public List<ConstructorProduct> getListeProduitsTotaux() {
		return listeProduitsTotaux;
	}

	public void setListeProduitsTotaux(List<ConstructorProduct> listeProduitsTotaux) {
		this.listeProduitsTotaux = listeProduitsTotaux;
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
	
	public ConstructorProduct getProduitAffiche() {
		return produitAffiche;
	}

	public void setProduitAffiche(ConstructorProduct produitAffiche) {
		this.produitAffiche = produitAffiche;
	}

	public List<ConstructorProduct> getListeProduitsAffiches() {
		return listeProduitsAffiches;
	}

	public void setListeProduitsAffiches(
			List<ConstructorProduct> listeProduitsAffiches) {
		this.listeProduitsAffiches = listeProduitsAffiches;
	}
	
	public void setProxyCatalog(ICatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}
}
