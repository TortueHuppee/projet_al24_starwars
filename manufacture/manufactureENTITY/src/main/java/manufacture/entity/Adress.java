package manufacture.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adress database table.
 * 
 */
@Entity
@NamedQuery(name="Adress.findAll", query="SELECT a FROM Adress a")
public class Adress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_adress")
	private int idAdress;

	@Column(name="is_billing_adress")
	private byte isBillingAdress;

	@Column(name="is_delivery_adress")
	private byte isDeliveryAdress;

	private String number;

	private String street;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="id_city")
	private City city;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="adress")
	private List<User> users;

	public Adress() {
	}

	public int getIdAdress() {
		return this.idAdress;
	}

	public void setIdAdress(int idAdress) {
		this.idAdress = idAdress;
	}

	public byte getIsBillingAdress() {
		return this.isBillingAdress;
	}

	public void setIsBillingAdress(byte isBillingAdress) {
		this.isBillingAdress = isBillingAdress;
	}

	public byte getIsDeliveryAdress() {
		return this.isDeliveryAdress;
	}

	public void setIsDeliveryAdress(byte isDeliveryAdress) {
		this.isDeliveryAdress = isDeliveryAdress;
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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setAdress(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setAdress(null);

		return user;
	}

}