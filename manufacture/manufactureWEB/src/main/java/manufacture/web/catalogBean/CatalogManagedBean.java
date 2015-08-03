package manufacture.web.catalogBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.product.ArtisanProduct;
import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.Material;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.product.UsedProduct;
import manufacture.ifacade.catalog.ICatalog;

import org.apache.log4j.Logger;

import manufacture.web.util.RepeatPaginator;

@ManagedBean(name="mbCatalog")
@ApplicationScoped
public class CatalogManagedBean {

    private Logger log = Logger.getLogger(CatalogManagedBean.class);

    @ManagedProperty(value="#{catalog}")
    private ICatalog proxyCatalog;

    /**
     * Les listes de produits.
     */

    private List<ConstructorProduct> listeConstructorProductBrute;
    private List<ArtisanProduct> listeArtisanProductBrute;
    private List<UsedProduct> listeUsedProductBrute;

    private List<Produit> listeProduitConstructeurBrute;
    private List<Produit> listeProduitConstructeurAffichee;

    private List<Produit> listeProduitArtisanBrute;
    private List<Produit> listeProduitArtisanAffichee;

    private List<Produit> listeProduitOccasionBrute;
    private List<Produit> listeProduitOccasionAffichee;

    /**
     * Filtres communs aux trois listes de produit.
     */
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

    /**
     * Trois paginator pour chacune des listes.
     */
    private RepeatPaginator paginatedListConstructorProduct;
    private RepeatPaginator paginatedListArtisanProduct;
    private RepeatPaginator paginatedListUsedProduct;


    @PostConstruct
    void init()
    {
        listeConstructorProductBrute = proxyCatalog.getAllConstructorProduct();    
        listeArtisanProductBrute = proxyCatalog.getAllArtisanProduct();
        listeUsedProductBrute = proxyCatalog.getAllUsedProduct();

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

    public void initialisationListesAffichees()
    {
        listeProduitConstructeurAffichee = new ArrayList<Produit>();
        listeProduitArtisanAffichee = new ArrayList<Produit>();
        listeProduitOccasionAffichee = new ArrayList<Produit>();

        tousLesFiltres(listeProduitConstructeurBrute , listeProduitConstructeurAffichee);
        paginatedListConstructorProduct = new RepeatPaginator(listeProduitConstructeurAffichee, 24);
        
        tousLesFiltres(listeProduitArtisanBrute , listeProduitArtisanAffichee);
        paginatedListArtisanProduct = new RepeatPaginator(listeProduitArtisanAffichee, 24);
        
        tousLesFiltres(listeProduitOccasionBrute , listeProduitOccasionAffichee);  
        paginatedListUsedProduct = new RepeatPaginator(listeProduitOccasionAffichee, 24);
    }
    
    public void initialisationListesBrutes()
    {
        listeProduitConstructeurBrute = new ArrayList<Produit>();
        listeProduitArtisanBrute = new ArrayList<Produit>();
        listeProduitOccasionBrute = new ArrayList<Produit>();

        for (ConstructorProduct product : listeConstructorProductBrute)
        {  
            int idProductRef = product.getProductRef().getIdProductRef();
            int idProduct = product.getIdProduct();
            int idCouleur = product.getColor().getIdColor();
            int idConstructeur = product.getConstructor().getIdConstructor();
            int idMateriaux = product.getMaterial().getIdMaterial();
            int idCategorie = product.getProductRef().getCategory().getIdCategory();

            String nomProduct = product.getProductRef().getProductName();
            String urlPhoto = product.getProductRef().getUrlImage();
            double prix = DoubleFormat(product.getPrice());

            List<SpaceshipProduct> listeModèleVaisseauCompatibles = proxyCatalog.getSpaceShipProductByProduct(product.getProductRef());
            List<Integer> listeIdVaisseau = new ArrayList<>();

            for (SpaceshipProduct ssp : listeModèleVaisseauCompatibles)
            {
                listeIdVaisseau.add(ssp.getIdSpaceshipProduct());
            }
            Produit produit = new Produit(idProductRef, idProduct, idConstructeur, idCouleur, idMateriaux, idCategorie, nomProduct, urlPhoto, prix, listeIdVaisseau);

            listeProduitConstructeurBrute.add(produit);
        }

        for (ArtisanProduct product : listeArtisanProductBrute)
        {  
            int idProductRef = product.getProductRef().getIdProductRef();
            int idProduct = product.getIdProduct();
            int idCouleur = product.getColor().getIdColor();
            int idConstructeur = product.getUser().getIdUser();
            int idMateriaux = product.getMaterial().getIdMaterial();
            int idCategorie = product.getProductRef().getCategory().getIdCategory();

            String nomProduct = product.getProductRef().getProductName();
            String urlPhoto = product.getProductRef().getUrlImage();
            double prix = DoubleFormat(product.getPrice());

            List<SpaceshipProduct> listeModèleVaisseauCompatibles = proxyCatalog.getSpaceShipProductByProduct(product.getProductRef());
            List<Integer> listeIdVaisseau = new ArrayList<>();

            for (SpaceshipProduct ssp : listeModèleVaisseauCompatibles)
            {
                listeIdVaisseau.add(ssp.getIdSpaceshipProduct());
            }
            Produit produit = new Produit(idProductRef, idProduct, idConstructeur, idCouleur, idMateriaux, idCategorie, nomProduct, urlPhoto, prix, listeIdVaisseau);

            listeProduitArtisanBrute.add(produit);
        }

        for (UsedProduct product : listeUsedProductBrute)
        {  
            int idProductRef = product.getProductRef().getIdProductRef();
            int idProduct = product.getIdProduct();
            int idCouleur = product.getColor().getIdColor();
            int idConstructeur = product.getUser().getIdUser();
            int idMateriaux = product.getMaterial().getIdMaterial();
            int idCategorie = product.getProductRef().getCategory().getIdCategory();

            String nomProduct = product.getProductRef().getProductName();
            String urlPhoto = product.getProductRef().getUrlImage();
            double prix = DoubleFormat(product.getPrice());

            List<SpaceshipProduct> listeModèleVaisseauCompatibles = proxyCatalog.getSpaceShipProductByProduct(product.getProductRef());
            List<Integer> listeIdVaisseau = new ArrayList<>();

            for (SpaceshipProduct ssp : listeModèleVaisseauCompatibles)
            {
                listeIdVaisseau.add(ssp.getIdSpaceshipProduct());
            }
            Produit produit = new Produit(idProductRef, idProduct, idConstructeur, idCouleur, idMateriaux, idCategorie, nomProduct, urlPhoto, prix, listeIdVaisseau);

            listeProduitOccasionBrute.add(produit);
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

        for (int idModeleVaisseau : produit.getListeModèleVaisseauCompatibles())
        {
            if (idModeleVaisseau == idSpaceShipSelected)
            {
                ajout = true;
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

    public List<Produit> getListeProduitAffichee() {
        return listeProduitConstructeurAffichee;
    }

    public void setListeProduitAffichee(List<Produit> listeProduitAffichee) {
        this.listeProduitConstructeurAffichee = listeProduitAffichee;
    }

    public List<ConstructorProduct> getListeProduct() {
        return listeConstructorProductBrute;
    }

    public void setListeProduct(List<ConstructorProduct> listeProduct) {
        this.listeConstructorProductBrute = listeProduct;
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
        return listeConstructorProductBrute;
    }

    public void setListeProductBrute(List<ConstructorProduct> listeProductBrute) {
        this.listeConstructorProductBrute = listeProductBrute;
    }

    public List<ConstructorProduct> getListeConstructorProductBrute() {
        return listeConstructorProductBrute;
    }

    public void setListeConstructorProductBrute(
            List<ConstructorProduct> paramListeConstructorProductBrute) {
        listeConstructorProductBrute = paramListeConstructorProductBrute;
    }

    public List<ArtisanProduct> getListeArtisanProductBrute() {
        return listeArtisanProductBrute;
    }

    public void setListeArtisanProductBrute(
            List<ArtisanProduct> paramListeArtisanProductBrute) {
        listeArtisanProductBrute = paramListeArtisanProductBrute;
    }

    public List<UsedProduct> getListeUsedProductBrute() {
        return listeUsedProductBrute;
    }

    public void setListeUsedProductBrute(
            List<UsedProduct> paramListeUsedProductBrute) {
        listeUsedProductBrute = paramListeUsedProductBrute;
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

    //Classe interne	
    public class Produit implements Comparable<Produit>
    {
        private int idProductRef;
        private int idProduct;
        private int idConcstructeur;
        private int idCouleur;
        private int idMateriaux;
        private int idCategorie;
        private String nom;
        private String urlPhoto;
        private double prixMin;
        private List<Integer> listeModèleVaisseauCompatibles;

        public Produit(int paramIdProductRef, int paramIdProduct,
                int paramIdConcstructeur, int paramIdCouleur,
                int paramIdMateriaux, int paramIdCategorie, String paramNom, String paramUrlPhoto,
                double paramPrixMin,
                List<Integer> paramListeModèleVaisseauCompatibles) {
            super();
            idProductRef = paramIdProductRef;
            idProduct = paramIdProduct;
            idConcstructeur = paramIdConcstructeur;
            idCouleur = paramIdCouleur;
            idMateriaux = paramIdMateriaux;
            idCategorie = paramIdCategorie;
            nom = paramNom;
            urlPhoto = paramUrlPhoto;
            prixMin = paramPrixMin;
            listeModèleVaisseauCompatibles = paramListeModèleVaisseauCompatibles;
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
        public List<Integer> getListeModèleVaisseauCompatibles() {
            return listeModèleVaisseauCompatibles;
        }
        public void setListeModèleVaisseauCompatibles(
                List<Integer> paramListeModèleVaisseauCompatibles) {
            listeModèleVaisseauCompatibles = paramListeModèleVaisseauCompatibles;
        }
        public int getIdConcstructeur() {
            return idConcstructeur;
        }
        public void setIdConcstructeur(int paramIdConcstructeur) {
            idConcstructeur = paramIdConcstructeur;
        }
        public int getIdCouleur() {
            return idCouleur;
        }
        public void setIdCouleur(int paramIdCouleur) {
            idCouleur = paramIdCouleur;
        }
        public int getIdMateriaux() {
            return idMateriaux;
        }
        public void setIdMateriaux(int paramIdMateriaux) {
            idMateriaux = paramIdMateriaux;
        }
        public int getIdCategorie() {
            return idCategorie;
        }
        public void setIdCategorie(int paramIdCategorie) {
            idCategorie = paramIdCategorie;
        }

        @Override
        public int compareTo(Produit o) {
            return this.prixMin > o.prixMin ? 1 : (this.prixMin < o.prixMin ? -1 : 0);
        }
    }
}
