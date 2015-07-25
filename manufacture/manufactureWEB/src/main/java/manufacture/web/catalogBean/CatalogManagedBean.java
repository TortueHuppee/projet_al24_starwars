package manufacture.web.catalogBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.ifacade.catalog.ICatalog;

import org.apache.log4j.Logger;

import manufacture.web.util.ClassPathLoader;
import manufacture.web.util.RepeatPaginator;

import org.springframework.beans.factory.BeanFactory;

@ManagedBean(name="mbCatalog")
@SessionScoped
public class CatalogManagedBean {

	private Logger log = Logger.getLogger(CatalogManagedBean.class);

	@ManagedProperty(value="#{catalog}")
	private ICatalog proxyCatalog;

	public void setProxyCatalog(ICatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}

	private List<ConstructorProduct> listeProductBrute;
	private List<ConstructorProduct> listeProductAffichee;
	private List<Produit> listeProduitAffichee;

	private List<Category> listeCatégories;
	private List<Color> listeCouleurs;
	private List<Material> listeMateriaux;
	private List<Constructor> listeConstructeurs;
	private List<SpaceshipRef> listeVaisseaux;

	private int idCategorySelected;
	private int idColorSelected;
	private int idMaterialSelected;
	private int idConstructorSelected;
	private int idSpaceShipSelected;
	private RepeatPaginator paginatedList;
	@PostConstruct
	void init()
	{
		listeProductBrute = proxyCatalog.getAllConstructorProduct();

		initialisationFiltres();

		initialisationListeAffichee();
	}

	//Méthodes de tri et de filtres
	
	public void choixCategorie(int idCategory)
	{
		this.idCategorySelected = idCategory;
		initialisationListeAffichee();
	}
	
	public void initialisationFiltres() {

		listeCatégories = proxyCatalog.getAllCategory();
		listeCouleurs = proxyCatalog.getAllColor();
		listeMateriaux = proxyCatalog.getAllMaterial();
		listeConstructeurs = proxyCatalog.getAllConstructor();
		listeVaisseaux = proxyCatalog.getAllSpaceShipRef();

		idCategorySelected = 1;
		idColorSelected = 0;
		idMaterialSelected = 0;
		idConstructorSelected = 0;
		idSpaceShipSelected = 0;
	}

	public void initialisationListeAffichee()
	{
		listeProduitAffichee = new ArrayList<Produit>();
		listeProductAffichee = new ArrayList<ConstructorProduct>();

		for (ConstructorProduct product : listeProductBrute)
		{	
			boolean ajout = true;

			ajout = filtreCategorie(product);

			if (idColorSelected != 0 && ajout){
				ajout = filtreCouleur(product);
			}

			if (idMaterialSelected != 0 && ajout){
				ajout = filtreMateriaux(product);
			}

			if (idConstructorSelected != 0 && ajout){
				ajout = filtreConstructeur(product);
			}

			if (idSpaceShipSelected != 0 && ajout){
				ajout = filtreModèleVaisseau(product);
			}

			if (ajout){
				listeProductAffichee.add(product);
				ajout = produitDejaDansLaListe(product);
			}

			//Ajout du produit à la liste s'il répond aux critères
			if (ajout)
			{
				int idProductRef = product.getProductRef().getIdProductRef();
				int idProduct = product.getIdProduct();
				
				String nomProduct = product.getProductRef().getProductName();
				String urlPhoto = product.getProductRef().getUrlImage();
				double prix = DoubleFormat(product.getPrice());

				Produit produit = new Produit(idProductRef, idProduct, nomProduct, urlPhoto, prix);
				listeProduitAffichee.add(produit);
			}
		}
		paginatedList = new RepeatPaginator(listeProduitAffichee, 24);
	} 

	//Filtres
	public boolean filtreCategorie(Product product)
	{
		if (product.getProductRef().getCategory().getIdCategory() != idCategorySelected)
		{
			return false;
		}
		else
		{
			return true;
		}
	}


	public boolean filtreCouleur(Product product)
	{
		if (product.getColor().getIdColor() != idColorSelected)
		{
			return false;
		}
		else
		{
			return true;
		}
	}


	public boolean filtreMateriaux(Product product)
	{
		if (product.getMaterial().getIdMaterial() != idMaterialSelected)
		{
			return false;
		}
		else
		{
			return true;
		}
	}


	public boolean filtreConstructeur(ConstructorProduct product)
	{
		if (product.getConstructor().getIdConstructor() != idConstructorSelected)
		{
			return false;
		}
		else
		{
			return true;
		}
	}


	public boolean filtreModèleVaisseau(Product product)
	{
		List<SpaceshipProduct> listeModèleVaisseauCompatibles = proxyCatalog.getSpaceShipProductByProduct(product.getProductRef());

		boolean ajout = true;

		for (SpaceshipProduct ssp : listeModèleVaisseauCompatibles)
		{
			if (ssp.getSpaceshipRef().getIdSpaceshipRef() != idSpaceShipSelected)
			{
				ajout = false;
			}
		}

		return ajout;
	}


	public boolean produitDejaDansLaListe(ConstructorProduct product)
	{
		boolean ajout = true;

		for (Produit pr : listeProduitAffichee)
		{
			if (pr.getIdProductRef() == product.getProductRef().getIdProductRef())
			{
				ajout = false;

				if (product.getPrice() < pr.getPrixMin())
				{
					double prix = DoubleFormat(product.getPrice());
					pr.setPrixMin(prix);
				}
			}
		}	
		return ajout;
	}

	//Tris
	public void trierParPrix()
	{
		Collections.sort(listeProduitAffichee);
	}


	public void reverseTrierParPrix()
	{
		Collections.sort(listeProduitAffichee, Collections.reverseOrder());
	}


	//Réinitialisation de la listeAffichée
	public void reinitialiseListeAffichee()
	{
		initialisationListeAffichee();
	}


	private double DoubleFormat(double number)
	{
		number = number*100;
		number = (double)((int) number);
		number = number /100;

		return number;
	}
	//Getters et Setters	

	public List<Produit> getListeProduitAffichee() {
		return listeProduitAffichee;
	}

	public void setListeProduitAffichee(List<Produit> listeProduitAffichee) {
		this.listeProduitAffichee = listeProduitAffichee;
	}

	public List<ConstructorProduct> getListeProduct() {
		return listeProductBrute;
	}

	public void setListeProduct(List<ConstructorProduct> listeProduct) {
		this.listeProductBrute = listeProduct;
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

	public List<Category> getListeCatégories() {
		return listeCatégories;
	}

	public void setListeCatégories(List<Category> listeCatégories) {
		this.listeCatégories = listeCatégories;
	}

	public List<SpaceshipRef> getListeVaisseaux() {
		return listeVaisseaux;
	}

	public void setListeVaisseaux(List<SpaceshipRef> listeVaisseaux) {
		this.listeVaisseaux = listeVaisseaux;
	}

	public int getIdCategorySelected() {
		return idCategorySelected;
	}

	public void setIdCategorySelected(int idCategorySelected) {
		this.idCategorySelected = idCategorySelected;
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

	public int getIdSpaceShipSelected() {
		return idSpaceShipSelected;
	}

	public void setIdSpaceShipSelected(int idSpaceShipSelected) {
		this.idSpaceShipSelected = idSpaceShipSelected;
	}

	public List<ConstructorProduct> getListeProductBrute() {
		return listeProductBrute;
	}

	public void setListeProductBrute(List<ConstructorProduct> listeProductBrute) {
		this.listeProductBrute = listeProductBrute;
	}

	public List<ConstructorProduct> getListeProductAffichee() {
		return listeProductAffichee;
	}

	public void setListeProductAffichee(
			List<ConstructorProduct> listeProductAffichee) {
		this.listeProductAffichee = listeProductAffichee;
	}

	public RepeatPaginator getPaginatedList() {
		return paginatedList;
	}

	public void setPaginatedList(RepeatPaginator paginatedList) {
		this.paginatedList = paginatedList;
	}

	//Classe interne	
	public class Produit implements Comparable<Produit>
	{
		private int idProductRef;
		private int idProduct;
		private String nom;
		private String urlPhoto;
		private double prixMin;

		public Produit(int idProductRef, int idProduct, String nom, String urlPhoto, double prixMoyen) {
			super();
			this.idProductRef = idProductRef;
			this.idProduct = idProduct;
			this.nom = nom;
			this.urlPhoto = urlPhoto;
			this.prixMin = prixMoyen;
		}

		public int getIdProductRef() {
			return idProductRef;
		}
		
		public void setIdProductRef(int idProductRef) {
			this.idProductRef = idProductRef;
		}
		
		public int getIdProduct() {
			return idProduct;
		}

		public void setIdProduct(int idProduct) {
			this.idProduct = idProduct;
		}

		public String getNom() {
			return nom;
		}
		
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public String getUrlPhoto() {
			return urlPhoto;
		}
		public void setUrlPhoto(String urlPhoto) {
			this.urlPhoto = urlPhoto;
		}
		public double getPrixMin() {
			return prixMin;
		}
		public void setPrixMin(double prixMin) {
			this.prixMin = prixMin;
		}

		@Override
		public int compareTo(Produit o) {
			return this.prixMin > o.prixMin ? 1 : (this.prixMin < o.prixMin ? -1 : 0);
		}
	}
}
