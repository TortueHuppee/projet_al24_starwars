package manufacture.web.catalogBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.user.User;
import manufacture.ifacade.catalog.ICatalog;

import org.apache.log4j.Logger;

import manufacture.web.datas.DataLoader;
import manufacture.web.util.RepeatPaginator;

@ManagedBean(name="mbCatalog")
@ApplicationScoped
public class CatalogManagedBean {

    private Logger log = Logger.getLogger(CatalogManagedBean.class);

    @ManagedProperty(value="#{catalog}")
    private ICatalog proxyCatalog;
    
    @ManagedProperty(value="#{mbIndex}")
    private IndexManagedBean indexManagedBean;
    
    @ManagedProperty(value="#{mbDataLoader}")
    private DataLoader dataLoader;

   /**
    * Constantes
    */
    
    private static final Integer PRODUCT_CONSTRUCTEUR_TYPE_ID = 1;
    private static final Integer PRODUCT_ARTISAN_TYPE_ID = 2;
    private static final Integer PRODUCT_OCCASION_TYPE_ID = 3;
    
    /**
     * Les listes de produits.
     */
    private List<Product> listeProductBrute;

    private List<Produit> listeProduitConstructeurBrute;
    private List<Produit> listeProduitConstructeurAffichee;

    private List<Produit> listeProduitArtisanBrute;
    private List<Produit> listeProduitArtisanAffichee;

    private List<Produit> listeProduitOccasionBrute;
    private List<Produit> listeProduitOccasionAffichee;

    /**
     * Filtres communs aux trois listes de produit.
     */
    private List<SpaceshipProduct> listeModèleVaisseauProduit;
    
    private List<Category> listeCatégories;
    private List<Color> listeCouleurs;
    private List<Material> listeMateriaux;
    private List<SelectItem> listeItemConstructeur;
    private List<Constructor> listeConstructeurs;
    private List<User> listeArtisans;
    private List<SpaceshipRef> listeVaisseaux;

    private int idCategorySelected;
    private int idColorSelected;
    private int idMaterialSelected;
    private int idConstructorSelected;
    private int idSpaceShipSelected;

    /**
     * Trois paginator pour chacune des listes.
     */
    private RepeatPaginator paginatedListConstructorProduct;
    private RepeatPaginator paginatedListArtisanProduct;
    private RepeatPaginator paginatedListUsedProduct;


    @PostConstruct
    void init()
    {
    	listeProductBrute = indexManagedBean.getListeProduitBrute();
        
        listeModèleVaisseauProduit = proxyCatalog.getAllSpaceShipProduct();

        initialisationFiltres();
        initialisationListesBrutes();
        initialisationListesAffichees();
    }

    //Méthodes de tri et de filtres

    public void choixCategorie(int idCategory)
    {
        this.idCategorySelected = idCategory;
        initialisationListesAffichees();
    }

    public void initialisationFiltres() {

        listeCatégories = dataLoader.getListeCatégories();
        listeCouleurs = dataLoader.getListeCouleurs();
        listeMateriaux = dataLoader.getListeMateriaux();
        listeConstructeurs = dataLoader.getListeConstructeurs();
        listeArtisans = dataLoader.getListeArtisans();
        
        SelectItemGroup groupeConstructeur = new SelectItemGroup("Constructeurs");
        SelectItemGroup groupeArtisan = new SelectItemGroup("Artisans");
        
        SelectItem[] tableauConstructeur = new SelectItem[listeConstructeurs.size()];
        SelectItem[] tableauArtisan = new SelectItem[listeArtisans.size()];
        
        for (int i = 0; i < listeConstructeurs.size() ; i++)
        {
        	Constructor c = listeConstructeurs.get(i);
        	SelectItem item = new SelectItem(c.getIdConstructor(), c.getConstructorName(), "Produit constructeur");
        	tableauConstructeur[i] = item;
        }
        groupeConstructeur.setSelectItems(tableauConstructeur);
        
        for (int i = 0; i < listeArtisans.size() ; i++)
        {
        	User u = listeArtisans.get(i);
        	SelectItem item = new SelectItem((u.getIdUser() * -1), u.getUserName(), "Produit artisan");
        	tableauArtisan[i] = item;
        }
        groupeArtisan.setSelectItems(tableauArtisan);
        
        listeItemConstructeur = new ArrayList<SelectItem>();
        
        if (listeConstructeurs.size() > 0)
        {
        	listeItemConstructeur.add(groupeConstructeur);
        }
        
        if (listeArtisans.size() > 0)
        {
        	listeItemConstructeur.add(groupeArtisan);
        }
        listeVaisseaux = dataLoader.getListeVaisseaux();

        idCategorySelected = 1;
        idColorSelected = 0;
        idMaterialSelected = 0;
        idConstructorSelected = 0;
        idSpaceShipSelected = 0;
    }
    
    public void initialisationListesAffichees()
    {
        listeProduitConstructeurAffichee = new ArrayList<Produit>();
        listeProduitArtisanAffichee = new ArrayList<Produit>();
        listeProduitOccasionAffichee = new ArrayList<Produit>();

        tousLesFiltres(listeProduitConstructeurBrute , listeProduitConstructeurAffichee);
        
        paginatedListConstructorProduct = new RepeatPaginator(listeProduitConstructeurAffichee, 24);
        paginatedListArtisanProduct = new RepeatPaginator(listeProduitArtisanAffichee, 24);
        paginatedListUsedProduct = new RepeatPaginator(listeProduitOccasionAffichee, 24);
    }
    
    public void initialisationListesBrutes()
    {
        listeProduitConstructeurBrute = new ArrayList<Produit>();
        listeProduitArtisanBrute = new ArrayList<Produit>();
        listeProduitOccasionBrute = new ArrayList<Produit>();
        
        for (Product product : listeProductBrute)
        {  
            int idProductRef = product.getProductRef().getIdProductRef();
            int idProduct = product.getIdProduct();
            int idCouleur = product.getColor().getIdColor();
            
            int idConstructeur = 0;
            if (product.getTypeProduct().getIdTypeProduct() == PRODUCT_CONSTRUCTEUR_TYPE_ID)
            {
            	idConstructeur = product.getConstructor().getIdConstructor();
            }
            else
            {
            	idConstructeur = product.getUser().getIdUser() * -1;
            }
            
            int idMateriaux = product.getMaterial().getIdMaterial();
            int idCategorie = product.getProductRef().getCategory().getIdCategory();

            String nomProduct = product.getProductRef().getProductName();
            String urlPhoto = product.getProductRef().getUrlImage();
            double prix = DoubleFormat(product.getPrice());

            Produit produit = new Produit(idProductRef, idProduct, idConstructeur, idCouleur, idMateriaux, idCategorie, nomProduct, urlPhoto, prix);
            
            switch (product.getTypeProduct().getIdTypeProduct()) {
            case 1 : listeProduitConstructeurBrute.add(produit); break;
            case 2 : listeProduitArtisanBrute.add(produit); break;
            case 3 : listeProduitOccasionBrute.add(produit); break;
			default: break;
			}
        }
    }

    //Filtres
    public void tousLesFiltres(List<Produit> listeBrute, List<Produit> listeAffichée)
    {
        for (Produit produit : listeBrute)
        {
            boolean ajout = true;

            ajout = filtreCategorie(produit);

            if (idColorSelected != 0 && ajout){
                ajout = filtreCouleur(produit);
            }

            if (idMaterialSelected != 0 && ajout){
                ajout = filtreMateriaux(produit);
            }

            if (idConstructorSelected != 0 && ajout){
                ajout = filtreConstructeur(produit);
            }

            if (idSpaceShipSelected != 0 && ajout){
                ajout = filtreModèleVaisseau(produit);
            }

            if (ajout){
                ajout = produitDejaDansLaListe(produit, listeAffichée);
            }

            //Ajout du produit à la liste s'il répond aux critères
            if (ajout)
            {
                listeAffichée.add(produit);
            }
        }
    }
    
    public boolean filtreCategorie(Produit produit)
    {
        if (produit.getIdCategorie() != idCategorySelected)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean filtreCouleur(Produit produit)
    {
        if (produit.getIdCouleur() != idColorSelected)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean filtreMateriaux(Produit produit)
    {
        if (produit.getIdMateriaux() != idMaterialSelected)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean filtreConstructeur(Produit produit)
    {
        //La notion de constructeur est généralisée ici :
        //constructeur = constructeur pour les produits constructeurs
        //constructeur = artisan pour les produits artisans
        //constructeur = particulier pour les produits d'occasion
        if (produit.getIdConcstructeur() != idConstructorSelected)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean filtreModèleVaisseau(Produit produit)
    {
        boolean ajout = false;
        
        for (SpaceshipProduct ssp : listeModèleVaisseauProduit)
        {
        	if (ssp.getProductRef().getIdProductRef() == produit.getIdProductRef())
        	{
        		if (ssp.getSpaceshipRef().getIdSpaceshipRef() == idSpaceShipSelected)
        		{
        			ajout = true;
        		}
        	}
        }
        return ajout;
    }

    public boolean produitDejaDansLaListe(Produit produit, List<Produit> liste)
    {
        boolean ajout = true;

        for (Produit pr : liste)
        {
            if (pr.getIdProductRef() == produit.getIdProductRef())
            {
                ajout = false;

                if (produit.getPrixMin() < pr.getPrixMin())
                {
                    double prix = DoubleFormat(produit.getPrixMin());
                    pr.setPrixMin(prix);
                }
            }
        }	
        return ajout;
    }

    //Tris
    public void trierParPrix()
    {
        Collections.sort(listeProduitConstructeurAffichee);
    }

    public void reverseTrierParPrix()
    {
        Collections.sort(listeProduitConstructeurAffichee, Collections.reverseOrder());
    }

    //Réinitialisation de la listeAffichée
    public void reinitialiseListeAffichee()
    {
        initialisationListesAffichees();
    }

    private double DoubleFormat(double number)
    {
        number = number*100;
        number = (double)((int) number);
        number = number /100;

        return number;
    }
    //Getters et Setters	

    public List<SpaceshipProduct> getListeModèleVaisseauProduit() {
		return listeModèleVaisseauProduit;
	}

	public void setListeModèleVaisseauProduit(
			List<SpaceshipProduct> listeModèleVaisseauProduit) {
		this.listeModèleVaisseauProduit = listeModèleVaisseauProduit;
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

    public List<Produit> getListeProduitBrute() {
        return listeProduitConstructeurBrute;
    }

    public void setListeProduitBrute(List<Produit> paramListeProduitBrute) {
        listeProduitConstructeurBrute = paramListeProduitBrute;
    }

    public ICatalog getProxyCatalog() {
        return proxyCatalog;
    }

    public void setProxyCatalog(ICatalog proxyCatalog) {
        this.proxyCatalog = proxyCatalog;
    }
    
	public List<Produit> getListeProduitConstructeurBrute() {
		return listeProduitConstructeurBrute;
	}

	public void setListeProduitConstructeurBrute(
			List<Produit> listeProduitConstructeurBrute) {
		this.listeProduitConstructeurBrute = listeProduitConstructeurBrute;
	}

	public List<Produit> getListeProduitConstructeurAffichee() {
		return listeProduitConstructeurAffichee;
	}

	public void setListeProduitConstructeurAffichee(
			List<Produit> listeProduitConstructeurAffichee) {
		this.listeProduitConstructeurAffichee = listeProduitConstructeurAffichee;
	}

	public List<Produit> getListeProduitArtisanBrute() {
		return listeProduitArtisanBrute;
	}

	public void setListeProduitArtisanBrute(List<Produit> listeProduitArtisanBrute) {
		this.listeProduitArtisanBrute = listeProduitArtisanBrute;
	}

	public List<Produit> getListeProduitArtisanAffichee() {
		return listeProduitArtisanAffichee;
	}

	public void setListeProduitArtisanAffichee(
			List<Produit> listeProduitArtisanAffichee) {
		this.listeProduitArtisanAffichee = listeProduitArtisanAffichee;
	}

	public List<Produit> getListeProduitOccasionBrute() {
		return listeProduitOccasionBrute;
	}

	public void setListeProduitOccasionBrute(List<Produit> listeProduitOccasionBrute) {
		this.listeProduitOccasionBrute = listeProduitOccasionBrute;
	}

	public List<Produit> getListeProduitOccasionAffichee() {
		return listeProduitOccasionAffichee;
	}

	public void setListeProduitOccasionAffichee(
			List<Produit> listeProduitOccasionAffichee) {
		this.listeProduitOccasionAffichee = listeProduitOccasionAffichee;
	}

	public RepeatPaginator getPaginatedListConstructorProduct() {
		return paginatedListConstructorProduct;
	}

	public void setPaginatedListConstructorProduct(
			RepeatPaginator paginatedListConstructorProduct) {
		this.paginatedListConstructorProduct = paginatedListConstructorProduct;
	}

	public RepeatPaginator getPaginatedListArtisanProduct() {
		return paginatedListArtisanProduct;
	}

	public void setPaginatedListArtisanProduct(
			RepeatPaginator paginatedListArtisanProduct) {
		this.paginatedListArtisanProduct = paginatedListArtisanProduct;
	}

	public RepeatPaginator getPaginatedListUsedProduct() {
		return paginatedListUsedProduct;
	}

	public void setPaginatedListUsedProduct(RepeatPaginator paginatedListUsedProduct) {
		this.paginatedListUsedProduct = paginatedListUsedProduct;
	}

    public IndexManagedBean getIndexManagedBean() {
		return indexManagedBean;
	}

	public void setIndexManagedBean(IndexManagedBean indexManagedBean) {
		this.indexManagedBean = indexManagedBean;
	}

	public List<Product> getListeProductBrute() {
		return listeProductBrute;
	}

	public void setListeProductBrute(List<Product> listeProductBrute) {
		this.listeProductBrute = listeProductBrute;
	}

	public static Integer getProductConstructeurTypeId() {
		return PRODUCT_CONSTRUCTEUR_TYPE_ID;
	}

	public static Integer getProductArtisanTypeId() {
		return PRODUCT_ARTISAN_TYPE_ID;
	}

	public static Integer getProductOccasionTypeId() {
		return PRODUCT_OCCASION_TYPE_ID;
	}

	public List<SelectItem> getListeItemConstructeur() {
		return listeItemConstructeur;
	}

	public void setListeItemConstructeur(List<SelectItem> listeItemConstructeur) {
		this.listeItemConstructeur = listeItemConstructeur;
	}

	public List<User> getListeArtisans() {
		return listeArtisans;
	}

	public void setListeArtisans(List<User> listeArtisans) {
		this.listeArtisans = listeArtisans;
	}

	public DataLoader getDataLoader() {
		return dataLoader;
	}

	public void setDataLoader(DataLoader dataLoader) {
		this.dataLoader = dataLoader;
	}
}
