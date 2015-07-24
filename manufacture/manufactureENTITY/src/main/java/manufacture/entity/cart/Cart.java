package manufacture.entity.cart;

import java.io.Serializable;

import javax.persistence.*;

import manufacture.entity.user.User;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cart database table.
 * 
 */
@Entity 
@Table(name="cart")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cart")
	private int idCart;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_commande")
	private Date dateCommande;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_payment")
	private Date datePayment;

	@Column(name="is_paid")
	private boolean isPaid;

	@Column(name="is_validated")
	private boolean isValidated;

	@Column(name="transaction_number")
	private int transactionNumber;

	//bi-directional many-to-one association to Delivery
	@ManyToOne
	@JoinColumn(name="id_delivery")
	private Delivery delivery;

	//bi-directional many-to-one association to PaymentType
	@ManyToOne
	@JoinColumn(name="id_payment")
	private PaymentType paymentType;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	//bi-directional many-to-one association to CartProduct
	@OneToMany(mappedBy="cart")
	private List<CartProduct> cartProducts;

	public Cart() {
	}

	public int getIdCart() {
		return this.idCart;
	}

	public void setIdCart(int idCart) {
		this.idCart = idCart;
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