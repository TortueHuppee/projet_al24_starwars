package manufacture.entity.cart;

import java.io.Serializable;

import javax.persistence.*;

import manufacture.entity.user.User;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cart database table.
 */
@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    /**
     * Numéro de version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identifiant unique du panier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cart")
    private Integer idCart;

    /**
     * Date de commande du panier (correspondant à la date du jour).
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_commande")
    private Date dateCommande;

    /**
     * Date de paiement de la commande (correspondant à la date du jour).
     * La date de paiement et la même que la date de commande s'il s'agit d'un client particulier.
     * La date de paiement diffère de la date de commande s'il s'agit d'un client professionnel.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_payment")
    private Date datePayment;

    /**
     * Boolean pour savoir si la commande a été payée (TRUE) ou non (FALSE).
     */
    @Column(name = "is_paid")
    private boolean isPaid;

    /**
     * Boolean pour savoir si la commande a été passée (TRUE) ou non (FALSE).
     * La commande peut être passée sans être payée dans le cas du client professionnel.
     * La commande doit obligatoirement être payée pour pouvoir être enregistrée dans le cas
     * du client particulier.
     */
    @Column(name = "is_validated")
    private boolean isValidated;

    /**
     * Numéro de transaction bancaire correspond au paiement de la commande.
     */
    @Column(name = "transaction_number")
    private int transactionNumber;

    /**
     * Moyen de livraison de la commande.
     */
    //bi-directional many-to-one association to Delivery
    @ManyToOne
    @JoinColumn(name = "id_delivery")
    private Delivery delivery;

    /**
     * Moyen de paiement de la commande.
     */
    //bi-directional many-to-one association to PaymentType
    @ManyToOne
    @JoinColumn(name = "id_payment")
    private PaymentType paymentType;

    /**
     * Utilisateur propriétaire du panier.
     */
    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    /**
     * Liste des produits ajoutés au panier.
     */
    //bi-directional many-to-one association to CartProduct
    @OneToMany(mappedBy = "cart")
    private List<CartProduct> cartProducts;

    public Cart() {
    }

    /**
     * @return the idCart
     */
    public Integer getIdCart() {
        return this.idCart;
    }

    /**
     * @param paramIdCart the idCart to set
     */
    public void setIdCart(Integer paramIdCart) {
        this.idCart = paramIdCart;
    }

    /**
     * @param paramIsPaid the isPaid to set
     */
    public void setPaid(boolean paramIsPaid) {
        this.isPaid = paramIsPaid;
    }


    /**
     * @param paramIsValidated the isValidated to set
     */
    public void setValidated(boolean paramIsValidated) {
        this.isValidated = paramIsValidated;
    }


    public Date getDateCommande() {
        return this.dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDatePayment() {
        return this.datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public boolean getIsPaid() {
        return this.isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean getIsValidated() {
        return this.isValidated;
    }

    public void setIsValidated(boolean isValidated) {
        this.isValidated = isValidated;
    }

    public int getTransactionNumber() {
        return this.transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Delivery getDelivery() {
        return this.delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartProduct> getCartProducts() {
        return this.cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public CartProduct addCartProduct(CartProduct cartProduct) {
        getCartProducts().add(cartProduct);
        cartProduct.setCart(this);

        return cartProduct;
    }

    public CartProduct removeCartProduct(CartProduct cartProduct) {
        getCartProducts().remove(cartProduct);
        cartProduct.setCart(null);

        return cartProduct;
    }

}