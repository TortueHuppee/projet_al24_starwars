package manufacture.web.catalogBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
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

	private BeanFactory bf = ClassPathLoader.getFacadeBeanFactory();
	private ICatalog proxyCatalog = (ICatalog) bf.getBean(ICatalog.class);
	
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
	
	@PostConstruct
	void init()
	{
		idProductRef = 1;
		
		//photo, nom, description, catégorie, modèles vaisseaux
		productRef = proxyCatalog.getProductRefById(idProductRef);
		listeVaisseauxProduit = proxyCatalog.getSpaceShipProductByProduct(productRef);
		listeVaisseaux = new ArrayList<SpaceshipRef>();
		for (SpaceshipProduct ssp : listeVaisseauxProduit)
		{
			listeVaisseaux.add(ssp.getSpaceshipRef());
		}
		
		//prix, options disponibles (couleur, matériaux, constructeur)
		listeProduitsTotaux = proxyCatalog.getAllProductByProductRef(idProductRef);
		listeProduitsAffiches = listeProduitsTotaux;
		produitAffiche = listeProduitsAffiches.get(0);
	
		//options
		idColorSelected = produitAffiche.getColor().getIdColor();
		idMaterialSelected = produitAffiche.getMaterial().getIdMaterial();
		idConstructorSelected = produitAffiche.getConstructor().getIdConstructor();
		
		quantiteDisponible();
	}

	//Méthodes
	
	public void quantiteDisponible()
	{
		quantiteDispo = 0;
		
		for (ConstructorProduct product : listeProduitsAffiches)
		{
			//if (idColorSelected == 00 || product.getColor().getIdColor() == idColorSelected)
			if (product.getColor().getIdColor() == idColorSelected)
			{
				if (product.getMaterial().getIdMaterial() == idMaterialSelected)
				{
					if (product.getConstructor().getIdConstructor() == idConstructorSelected)
					{
						quantiteDispo += product.getStock();
					}
				}
			}	
		}
	}
	
	public void initialisationListes()
	{
		listeCouleurs = new ArrayList<Color>();
		listeMateriaux = new ArrayList<Material>();
		listeConstructeurs = new ArrayList<Constructor>();

		for (ConstructorProduct product : listeProduitsAffiches)
		{
			if (!listeCouleurs.contains(product.getColor()))
			{
				listeCouleurs.add(product.getColor());
			}
			if (!listeMateriaux.contains(product.getMaterial()))
			{
				listeMateriaux.add(product.getMaterial());
			}
			if (!listeConstructeurs.contains(product.getConstructor()))
			{
				listeConstructeurs.add(product.getConstructor());
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

	public List<ConstructorProduct> getListeProduitsAffiches() {
		return listeProduitsAffiches;
	}

	public void setListeProduitsAffiches(
			List<ConstructorProduct> listeProduitsAffiches) {
		this.listeProduitsAffiches = listeProduitsAffiches;
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
}
