package manufacture.web.cart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.cart.Cart;
import manufacture.entity.user.Address;
import manufacture.entity.user.User;
import manufacture.ifacade.cart.IPaiement;
import manufacture.web.user.LoginBean;
import manufacture.web.user.ProfilBean;
import manufacture.web.user.UserBean;

import org.apache.log4j.Logger;

@ManagedBean(name = "paymentBean")
@SessionScoped
public class PaymentBean {

    private static Logger log = Logger.getLogger(PaymentBean.class);

    private String cardNumber = "";
    private String pin;
    private String expirationDate;
    private String reponse;
    private String cardOwnerName;

    private int idAdressePersonnelle = 0;
    private Address adresseFacturation;
    private List<Integer> listeIntMois;
    private List<Integer> listeIntAnnee;

    @ManagedProperty(value="#{profilBean}")
    private ProfilBean profilBean;

    @ManagedProperty(value="#{mbSteps}")
    private StepsCartManagedBean mbSteps;

    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;

    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;

    @ManagedProperty(value="#{mbCart}")
    private ManagedBeanCart mbCart;

    @ManagedProperty(value="#{paiement}")
    private IPaiement paiementFacade;
    
    private Cart panierValide;
    private double prixArticlePanierValide;
    private double prixTotalPanierValide;
    
    private SimpleDateFormat sdf;

    @PostConstruct
    public void init() {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        cardNumber = "";
        pin = "";
        expirationDate = "";
        reponse = "";
        cardOwnerName = "";

        listeIntMois = new ArrayList<>();
        for (int i = 1; i <= 12; i++)
        {
            listeIntMois.add(i);
        }

        Calendar calendar = Calendar.getInstance();
        int annee = calendar.get(Calendar.YEAR);

        listeIntAnnee = new ArrayList<>();
        for (int i = annee; i <= annee + 10; i++)
        {
            listeIntAnnee.add(i);
        }

        //Adresse de facturation
        if (profilBean.getAdressesFacturation().size() > 0)
        {
            adresseFacturation = profilBean.getAdressesFacturation().get(0);
        }
        else
        {
            adresseFacturation = new Address();
        }
    }

    //Methodes
    public boolean validatorNumber(String string)
    {
        log.info("log de string : " + string);
        System.out.println("Syso de string : " + string);
        boolean isNumber = false;
        try {
            Integer.parseInt(string);
            isNumber = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return isNumber;
    }

    public String goToStep4()
    {
        adresseFacturation = new Address();
        adresseFacturation.setIdAddress(idAdressePersonnelle);

        return valider();
    }

    public String valider() {
        Cart commande = mbCart.getCart();
        User user = userBean.getUser();
        commande.setUser(user);	    
        commande.setCartProducts(mbSteps.getListeProduitsAutorises());
        commande.setAddressBilling(adresseFacturation);

        panierValide = paiementFacade.processPaiement(commande);
        prixArticlePanierValide = mbSteps.getCartPrice();
        prixTotalPanierValide = mbSteps.getTotalPrice();
        profilBean.initialiserAchats();

        mbCart.init();
//        mbCart.setPanier(new ArrayList<CartProduct>());
        return "panierStep4.xhtml?faces-redirect=true";
    }

    @Override
    public String toString() {
        return " ** " + "Paiement : " + cardNumber + " ** " + pin + " ** ";
    }

    //Getters et Setters

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public ManagedBeanCart getMbCart() {
        return mbCart;
    }

    public void setMbCart(ManagedBeanCart mbCart) {
        this.mbCart = mbCart;
    }

    public IPaiement getPaiementFacade() {
        return paiementFacade;
    }

    public void setPaiementFacade(IPaiement paiementFacade) {
        this.paiementFacade = paiementFacade;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger paramLog) {
        log = paramLog;
    }

    public int getIdAdressePersonnelle() {
        return idAdressePersonnelle;
    }

    public ProfilBean getProfilBean() {
        return profilBean;
    }

    public void setProfilBean(ProfilBean paramProfilBean) {
        profilBean = paramProfilBean;
    }

    public void setIdAdressePersonnelle(int paramIdAdressePersonnelle) {
        idAdressePersonnelle = paramIdAdressePersonnelle;
    }

    public Address getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(Address paramAdresseFacturation) {
        adresseFacturation = paramAdresseFacturation;
    }

    public List<Integer> getListeIntMois() {
        return listeIntMois;
    }

    public void setListeIntMois(List<Integer> paramListeIntMois) {
        listeIntMois = paramListeIntMois;
    }

    public List<Integer> getListeIntAnnee() {
        return listeIntAnnee;
    }

    public void setListeIntAnnee(List<Integer> paramListeIntAnnee) {
        listeIntAnnee = paramListeIntAnnee;
    }

    public StepsCartManagedBean getMbSteps() {
        return mbSteps;
    }

    public void setMbSteps(StepsCartManagedBean paramMbSteps) {
        mbSteps = paramMbSteps;
    }

    public Cart getPanierValide() {
        return panierValide;
    }

    public void setPanierValide(Cart paramPanierValide) {
        panierValide = paramPanierValide;
    }

    public double getPrixArticlePanierValide() {
        return prixArticlePanierValide;
    }

    public void setPrixArticlePanierValide(double paramPrixArticlePanierValide) {
        prixArticlePanierValide = paramPrixArticlePanierValide;
    }

    public double getPrixTotalPanierValide() {
        return prixTotalPanierValide;
    }

    public void setPrixTotalPanierValide(double paramPrixTotalPanierValide) {
        prixTotalPanierValide = paramPrixTotalPanierValide;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat paramSdf) {
        sdf = paramSdf;
    }
}
