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

@Entity
@Table(name="address")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_address")
	private int idAddress;

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

//	//bi-directional many-to-one association to User
//	@OneToMany(mappedBy="address")
//	private List<User> users;
	
	//bi-directional many-to-one association to Adress
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	public Address() {
	}

	public int getIdAddress() {
		return this.idAddress;
	}

	public void setIdaddress(int idaddress) {
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

//	public List<User> getUsers() {
//		return this.users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
//
//	public User addUser(User user) {
//		getUsers().add(user);
//		user.setAddress(this);
//
//		return user;
//	}
//
//	public User removeUser(User user) {
//		getUsers().remove(user);
//		user.setAddress(null);
//
//		return user;
//	}

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

}