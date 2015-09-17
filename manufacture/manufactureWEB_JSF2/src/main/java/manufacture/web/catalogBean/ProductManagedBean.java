package manufacture.web.catalogBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.ifacade.catalog.ICatalog;

import org.apache.log4j.Logger;

@ManagedBean(name="mbProduct")
@SessionScoped
public class ProductManagedBean {

    private static Logger log = Logger.getLogger(ProductManagedBean.class);

    @ManagedProperty(value="#{catalog}")
    private ICatalog proxyCatalog;

    private static final Integer PRODUCT_PROFESSIONNEL_TYPE_ID = 1;
    private static final Integer PRODUCT_ARTISAN_TYPE_ID = 2;
    private static final Integer PRODUCT_OCCASION_TYPE_ID = 3;

    private int idProductRef;
    private ProductRef productRef;
    private Product produitAffiche;
    private List<Product> listeProduitsTotaux;
    private List<Product> listeProduitsAffiches;
    private int quantiteDispo;

    private List<Color> listeCouleurs;
    private List<Material> listeMateriaux;
    private List<Constructor> listeConstructeurs;
    private List<SpaceshipProduct> listeVaisseauxProduit;
    private List<SpaceshipRef> listeVaisseaux;

    private int idColorSelected;
    private int idMaterialSelected;
    private int idConstructorSelected;
    private int idSeller;

    void init()
    {
        //photo, nom, description, catégorie, modèles vaisseaux
        listeVaisseauxProduit = proxyCatalog.getSpaceShipProductByProduct(productRef);
        listeVaisseaux = new ArrayList<SpaceshipRef>();
        for (SpaceshipProduct ssp : listeVaisseauxProduit)
        {
            listeVaisseaux.add(ssp.getSpaceshipRef());
        }

        //prix, options disponibles (couleur, matériaux, constructeur)
        listeProduitsTotaux = proxyCatalog.getAllProductByProductRef(productRef.getIdProductRef());
        
        listeProduitsAffiches = listeProduitsTotaux;
        
        produitAffiche = listeProduitsTotaux.get(0);

        //options
        idColorSelected = produitAffiche.getColor().getIdColor();
        idMaterialSelected = produitAffiche.getMaterial().getIdMaterial();
        
        if (produitAffiche.getTypeProduct().getIdTypeProduct() == PRODUCT_PROFESSIONNEL_TYPE_ID)
        {
            idConstructorSelected = produitAffiche.getConstructor().getIdConstructor();
            idSeller = 0;
        }
        else
        {
            idSeller = produitAffiche.getUser().getIdUser();
            idConstructorSelected = 0;
        }
        

        //Couleurs
        listeCouleurs = new ArrayList<Color>();
        boolean ajoutColor = true;
        for (Product product : listeProduitsTotaux)
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
        listeProduitsAffiches = new ArrayList<Product>();

        for (Product product : listeProduitsTotaux)
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
        listeProduitsAffiches = new ArrayList<Product>();

        for (Product product : listeProduitsTotaux)
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
        listeProduitsAffiches = new ArrayList<Product>();

        for (Product product : listeProduitsTotaux)
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

        for (Product product : listeProduitsTotaux)
        {
            //if (idColorSelected == 00 || product.getColor().getIdColor() == idColorSelected)
            if (product.getColor().getIdColor() == idColorSelected)
            {
                if (product.getMaterial().getIdMaterial() == idMaterialSelected)
                {
                    if (product.getTypeProduct().getIdTypeProduct() == 1)
                    {
                        if (product.getConstructor().getIdConstructor() == idConstructorSelected)
                        {
                            quantiteDispo += product.getStock();
                            produitAffiche = product;
                        }
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

        for (Product product : listeProduitsTotaux)
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
                    if (product.getTypeProduct().getIdTypeProduct() == 1)
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

    public List<Product> getListeProduitsTotaux() {
        return listeProduitsTotaux;
    }

    public void setListeProduitsTotaux(List<Product> listeProduitsTotaux) {
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

    public Product getProduitAffiche() {
        return produitAffiche;
    }

    public void setProduitAffiche(Product produitAffiche) {
        this.produitAffiche = produitAffiche;
    }

    public List<Product> getListeProduitsAffiches() {
        return listeProduitsAffiches;
    }

    public void setListeProduitsAffiches(
            List<Product> listeProduitsAffiches) {
        this.listeProduitsAffiches = listeProduitsAffiches;
    }

    public void setProxyCatalog(ICatalog proxyCatalog) {
        this.proxyCatalog = proxyCatalog;
    }

    public ICatalog getProxyCatalog() {
        return proxyCatalog;
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger paramLog) {
        log = paramLog;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int paramIdSeller) {
        idSeller = paramIdSeller;
    }

    public static Integer getProductArtisanTypeId() {
        return PRODUCT_ARTISAN_TYPE_ID;
    }

    public static Integer getProductOccasionTypeId() {
        return PRODUCT_OCCASION_TYPE_ID;
    }

    public static Integer getProductProfessionnelTypeId() {
        return PRODUCT_PROFESSIONNEL_TYPE_ID;
    }
}
