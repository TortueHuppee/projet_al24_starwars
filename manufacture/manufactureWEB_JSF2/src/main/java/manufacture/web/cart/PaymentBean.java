package manufacture.web.cart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.user.Address;
import manufacture.entity.user.User;
import manufacture.ifacade.cart.IPaiement;
import manufacture.web.catalogBean.ProductManagedBean;
import manufacture.web.user.LoginBean;
import manufacture.web.user.ProfilBean;
import manufacture.web.user.UserBean;

import org.apache.log4j.Logger;

import fr.afcepf.al24.bank.bpel.BPELBank;
import fr.afcepf.al24.bank.bpel.BPELBankPortType;
import fr.afcepf.al24.bank.bpel.BPELBankRequest;
import fr.afcepf.al24.bank.bpel.BPELBankResponse;

@ManagedBean(name = "paymentBean")
@SessionScoped
public class PaymentBean {

    private static Logger log = Logger.getLogger(PaymentBean.class);
    
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
    
    @ManagedProperty(value="#{mbProduct}")
    private ProductManagedBean mbProduct;
    
    private Cart panierValide;
    private double prixArticlePanierValide;
    private double prixTotalPanierValide;
    
    private List<PaymentType> moyensDePaiement;
    private PaymentType moyenPaiementChoisi;
    private int idPaiement;
    
    private int idAdressePersonnelle = 0;
    private Address adresseFacturation;
    private List<Integer> listeIntMois;
    private List<Integer> listeIntAnnee;
    
    //Informations de paiement de la societe StarWars eShop
    private static final long NUMERO_CREDIT = 1;
    private XMLGregorianCalendar dateFinCredit ;
    private static final String DATE_STRING_DEBIT = "2017-01" ;
    private static final int CRYPTOGRAMME_DEBIT = 111;

    //Informations de paiement du client
    private long numeroDebit = 1234567891234567L;
    private XMLGregorianCalendar dateFinDebit ;
    private String dateStringDebit;
    private int cryptoDebit = 666;
    private int dateStringDebitMois ;
    private int dateStringDebitAnnee ;
    
    private SimpleDateFormat sdf;

    @PostConstruct
    public void init() {
        moyensDePaiement = new ArrayList<>();
        moyensDePaiement = paiementFacade.getAllPaymentType();
        
        //Informations de paiement de la societe StarWars eShop
        idPaiement = 1;
        Date dateFinValiditeCredit = new Date();
        sdf = new SimpleDateFormat("yyyy-MM");
        try {
        	dateFinValiditeCredit = sdf.parse(DATE_STRING_DEBIT);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        GregorianCalendar gcalendar = new GregorianCalendar();
		gcalendar.setTime(dateFinValiditeCredit);
		try {
			dateFinCredit = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalendar);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		//Informations de paiement du client
		numeroDebit = 1234567891234567L;
		dateStringDebit = "";
		dateStringDebitAnnee = 1;
		dateStringDebitMois = 2017;
		cryptoDebit = 666;

		//Donnees chargees pour l'affichage
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

    public String goToStep4()
    {
    	adresseFacturation = getAddressById();
        moyenPaiementChoisi = getPaiementById();
        
        //Appel du service
    	BPELBank bankService = new BPELBank();
    	BPELBankPortType iBPELBank = bankService.getBPELBankPort();
    	
    	BindingProvider bp = (BindingProvider)iBPELBank;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"http://192.168.100.130:8080/ode/processes/BPELBank?wsdl");
		
		//Preparation de la requete
		BPELBankRequest bpelBankRequest = convertDataToBPELBankRequest();
    	
    	BPELBankResponse bankResponse = iBPELBank.process(bpelBankRequest) ; // = resultOfCallOfTheService
    	
    	if (bankResponse.isTransactionValide()) {
        	return valider(bankResponse.getIdTransaction());
		} else {
			return "paiementRefuse.xhtml?faces-redirect=true"; //page de refus ou erreur paiement
		}
    	
    }

    public String valider(int idTransaction) {
    	
        Cart commande = mbCart.getCart();
        User user = userBean.getUser();
        commande.setUser(user);	    
        commande.setCartProducts(mbSteps.getListeProduitsAutorises());
        commande.setAddressBilling(adresseFacturation);
        commande.setPaymentType(moyenPaiementChoisi);
        commande.setTransactionNumber(idTransaction);

        panierValide = paiementFacade.processPaiement(commande);
        prixArticlePanierValide = mbSteps.getCartPrice();
        prixTotalPanierValide = mbSteps.calculePrixTotal();
        profilBean.initialiserAchats();

        mbCart.init();
        return "panierStep4.xhtml?faces-redirect=true";
    }
    
    private BPELBankRequest convertDataToBPELBankRequest() {
		
    	BPELBankRequest bpelBankRequest = new BPELBankRequest();
    	bpelBankRequest.setNumeroCredit(NUMERO_CREDIT);
    	bpelBankRequest.setCryptoCredit(CRYPTOGRAMME_DEBIT);
    	bpelBankRequest.setDateFinCredit(dateFinCredit);
    	
    	dateStringDebit = dateStringDebitAnnee + "-" + dateStringDebitMois ;
    	
    	Date dateFinValiditeDebit = new Date();
        sdf = new SimpleDateFormat("yyyy-MM");
        try {
        	dateFinValiditeDebit = sdf.parse(dateStringDebit);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        GregorianCalendar gcalendar = new GregorianCalendar();
		gcalendar.setTime(dateFinValiditeDebit);
		try {
			dateFinDebit = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalendar);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
    	bpelBankRequest.setNumeroDebit(numeroDebit);
    	bpelBankRequest.setCryptoDebit(cryptoDebit);
    	bpelBankRequest.setDateFinDebit(dateFinDebit);
    	bpelBankRequest.setMontant(mbSteps.calculePrixTotal());
    	
    	return bpelBankRequest ;
	}
    
    private PaymentType getPaiementById() {
        for (PaymentType payement : moyensDePaiement)
        {
            if (idPaiement == payement.getIdPayment())
            {
                return payement;
            }
        }
        return new PaymentType();
    }
    
    private Address getAddressById() {
        for (Address address : profilBean.getAdressesTotales())
        {
            if (idAdressePersonnelle == address.getIdAddress())
            {
                return address;
            }
        }
        return new Address();
    }

    //Getters et Setters

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

    public List<PaymentType> getMoyensDePaiement() {
        return moyensDePaiement;
    }

    public void setMoyensDePaiement(List<PaymentType> paramMoyensDePaiement) {
        moyensDePaiement = paramMoyensDePaiement;
    }

    public PaymentType getMoyenPaiementChoisi() {
        return moyenPaiementChoisi;
    }

    public void setMoyenPaiementChoisi(PaymentType paramMoyenPaiementChoisi) {
        moyenPaiementChoisi = paramMoyenPaiementChoisi;
    }

    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int paramIdPaiement) {
        idPaiement = paramIdPaiement;
    }


	public long getNumeroDebit() {
		return numeroDebit;
	}

	public XMLGregorianCalendar getDateFinDebit() {
		return dateFinDebit;
	}

	public String getDateStringDebit() {
		return dateStringDebit;
	}

	public void setDateStringDebit(String dateStringDebit) {
		this.dateStringDebit = dateStringDebit;
	}

	public int getCryptoDebit() {
		return cryptoDebit;
	}

	public int getDateStringDebitMois() {
		return dateStringDebitMois;
	}

	public void setDateStringDebitMois(int dateStringDebitMois) {
		this.dateStringDebitMois = dateStringDebitMois;
	}

	public int getDateStringDebitAnnee() {
		return dateStringDebitAnnee;
	}

	public void setDateStringDebitAnnee(int dateStringDebitAnnee) {
		this.dateStringDebitAnnee = dateStringDebitAnnee;
	}

	public ProductManagedBean getMbProduct() {
		return mbProduct;
	}

	public void setMbProduct(ProductManagedBean mbProduct) {
		this.mbProduct = mbProduct;
	}

	public XMLGregorianCalendar getDateFinCredit() {
		return dateFinCredit;
	}

	public void setDateFinCredit(XMLGregorianCalendar dateFinCredit) {
		this.dateFinCredit = dateFinCredit;
	}

	public static long getNumeroCredit() {
		return NUMERO_CREDIT;
	}

	public static int getCryptogrammeDebit() {
		return CRYPTOGRAMME_DEBIT;
	}

	public void setNumeroDebit(long numeroDebit) {
		this.numeroDebit = numeroDebit;
	}

	public void setDateFinDebit(XMLGregorianCalendar dateFinDebit) {
		this.dateFinDebit = dateFinDebit;
	}

	public void setCryptoDebit(int cryptoDebit) {
		this.cryptoDebit = cryptoDebit;
	}
}
