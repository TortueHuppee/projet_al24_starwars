package manufacture.web.catalogBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.SpaceshipRef;
import manufacture.ifacade.catalog.ICatalog;
import manufacture.web.util.ClassPathLoader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;

@ManagedBean(name="mbCatalog")
@SessionScoped
public class ManagedBeanCatalog {

	private Logger log = Logger.getLogger(ManagedBeanCatalog.class);

	private BeanFactory bf = ClassPathLoader.getFacadeBeanFactory();
	private ICatalog proxyCatalog = (ICatalog) bf.getBean(ICatalog.class);

	private List<Product> listeProduct;
	private List<Produit> listeProduitBrute;
	private List<Produit> listeProduitAffichee;

	private List<Category> listeCatégories; // OK
	private List<Color> listeCouleurs;
	private List<Material> listeMateriaux;
	private List<Constructor> listeConstructeurs;
	private List<SpaceshipRef> listeVaisseaux;

	private int idCategorySelected;
	private int idColorSelected;
	private int idMaterialSelected;
	private int idConstructorSelected;
	private int idSpaceShipSelected;
	
	private DecimalFormat df = new DecimalFormat("########.00");

	@PostConstruct
	void init()
	{
		listeProduct = proxyCatalog.getAllProduct();

		initialisationFiltres();
		
		initialisationListeBrute();
	}

	//Méthodes de tri et de filtres

	public void initialisationFiltres() {
		
		listeCatégories = proxyCatalog.getAllCategory();
		listeCouleurs = proxyCatalog.getAllColor();
		listeMateriaux = proxyCatalog.getAllMaterial();
		listeConstructeurs = proxyCatalog.getAllConstructor();
		listeVaisseaux = proxyCatalog.getAllSpaceShipRef();

		idCategorySelected = 1;
		idColorSelected = 1;
		idMaterialSelected = 1;
		idConstructorSelected = 1;
		idSpaceShipSelected = 1;
	}

	public void initialisationListeBrute()
	{
		listeProduitBrute = new ArrayList<Produit>();

		for (Product product : listeProduct)
		{	
			boolean ajout = true;

			if (product.getProductRef().getCategory().getIdCategory() != idCategorySelected)
			{
				ajout = false;
			}
			else
			{
				for (Produit pr : listeProduitBrute)
				{
					if (pr.getId() == product.getProductRef().getIdProductRef())
					{
						ajout = false;

						if (product.getPrice() < pr.getPrixMin())
						{
							pr.setPrixMin(product.getPrice());
						}
					}
				}
			}

			if (ajout)
			{
				int idProductRef = product.getProductRef().getIdProductRef();
				String nomProduct = product.getProductRef().getProductName();
				String urlPhoto = product.getProductRef().getUrlImage();
				double prix = Double.parseDouble(df.format(product.getPrice()));

				Produit produit = new Produit(idProductRef, nomProduct, urlPhoto, prix);
				listeProduitBrute.add(produit);
			}
		}
		listeProduitAffichee = listeProduitBrute;
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
		listeProduitAffichee = listeProduitBrute;
	}


	//Getters et Setters	
	public List<Produit> getListeProduitBrute() {
		return listeProduitBrute;
	}

	public void setListeProduitBrute(List<Produit> listeProduitBrute) {
		this.listeProduitBrute = listeProduitBrute;
	}

	public List<Produit> getListeProduitAffichee() {
		return listeProduitAffichee;
	}

	public void setListeProduitAffichee(List<Produit> listeProduitAffichee) {
		this.listeProduitAffichee = listeProduitAffichee;
	}

	public List<Product> getListeProduct() {
		return listeProduct;
	}

	public void setListeProduct(List<Product> listeProduct) {
		this.listeProduct = listeProduct;
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

	//Classe interne	
	public class Produit implements Comparable<Produit>
	{
		private int id;
		private String nom;
		private String urlPhoto;
		private double prixMin;

		public Produit(int id, String nom, String urlPhoto, double prixMoyen) {
			super();
			this.id = id;
			this.nom = nom;
			this.urlPhoto = urlPhoto;
			this.prixMin = prixMoyen;
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
