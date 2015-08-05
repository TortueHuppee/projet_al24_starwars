package manufacture.entity.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.RelayPoint;

@Entity
@Table(name="address")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_address")
	private Integer idAddress;

	//Par convention FALSE = 0 et TRUE = 1
	@Column(name="is_billing_address")
	private boolean isBillingaddress;

	//Par convention FALSE = 0 et TRUE = 1
	@Column(name="is_delivery_address")
	private boolean isDeliveryaddress;

	@Column(name="number")
	private String number;

	@Column(name="street")
	private String street;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="id_city")
	private City city;

	@OneToMany(mappedBy="addresse")
	private List<RelayPoint> relayPoints;
	
	@OneToMany(mappedBy="addressDelivery")
	private List<Cart> cartsDelivery;

	@OneToMany(mappedBy="addressBilling")
	private List<Cart> cartsBilling;
	
	//bi-directional many-to-one association to Adress
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	public Address() {
	}

	public Integer getIdAddress() {
		return this.idAddress;
	}

	public void setIdaddress(Integer idaddress) {
		this.idAddress = idaddress;
	}

	public boolean getIsBillingaddress() {
		return this.isBillingaddress;
	}

	public void setIsBillingaddress(boolean isBillingaddress) {
		this.isBillingaddress = isBillingaddress;
	}

	public boolean getIsDeliveryaddress() {
		return this.isDeliveryaddress;
	}

	public void setIsDeliveryaddress(boolean isDeliveryaddress) {
		this.isDeliveryaddress = isDeliveryaddress;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<RelayPoint> getRelayPoints() {
		return this.relayPoints;
	}

	public void setRelayPoints(List<RelayPoint> relayPoints) {
		this.relayPoints = relayPoints;
	}

	public RelayPoint addRelayPoint(RelayPoint relayPoint) {
		getRelayPoints().add(relayPoint);
		relayPoint.setAddresse(this);

		return relayPoint;
	}

	public RelayPoint removeRelayPoint(RelayPoint relayPoint) {
		getRelayPoints().remove(relayPoint);
		relayPoint.setAddresse(null);

		return relayPoint;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}

	public void setBillingaddress(boolean isBillingaddress) {
		this.isBillingaddress = isBillingaddress;
	}

	public void setDeliveryaddress(boolean isDeliveryaddress) {
		this.isDeliveryaddress = isDeliveryaddress;
	}

	public List<Cart> getCartsDelivery() {
		return cartsDelivery;
	}

	public void setCartsDelivery(List<Cart> cartsDelivery) {
		this.cartsDelivery = cartsDelivery;
	}

	public List<Cart> getCartsBilling() {
		return cartsBilling;
	}

	public void setCartsBilling(List<Cart> cartsBilling) {
		this.cartsBilling = cartsBilling;
	}

	public void setIdAddress(Integer idAddress) {
		this.idAddress = idAddress;
	}
	
	public Cart addCartDelivery(Cart cart) {
		getCartsDelivery().add(cart);
		cart.setAddressDelivery(this);

		return cart;
	}

	public Cart removeCartDelivery(Cart cart) {
		getCartsDelivery().remove(cart);
		cart.setAddressDelivery(null);

		return cart;
	}
	
	public Cart addCartBilling(Cart cart) {
		getCartsBilling().add(cart);
		cart.setAddressBilling(this);

		return cart;
	}

	public Cart removeCartBilling(Cart cart) {
		getCartsBilling().remove(cart);
		cart.setAddressBilling(null);

		return cart;
	}

}