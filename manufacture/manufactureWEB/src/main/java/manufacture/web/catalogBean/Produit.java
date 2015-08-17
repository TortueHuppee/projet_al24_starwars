package manufacture.web.catalogBean;

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

    public Produit(int paramIdProductRef, int paramIdProduct,
            int paramIdConcstructeur, int paramIdCouleur,
            int paramIdMateriaux, int paramIdCategorie, String paramNom, String paramUrlPhoto,
            double paramPrixMin) {
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
