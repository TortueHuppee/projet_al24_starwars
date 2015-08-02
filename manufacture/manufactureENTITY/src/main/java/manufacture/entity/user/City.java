package manufacture.entity.user;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name="city")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_city")
	private Integer idCity;

	@Column(name="city_name")
	private String cityName;

	@Column(name="postal_code")
	private int postalCode;

	//bi-directional many-to-one association to Adress
	@OneToMany(mappedBy="city")
	private List<Address> adresses;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="id_country")
	private Country country;

	public City() {
	}

	public Integer getIdCity() {
		return this.idCity;
	}

	public void setIdCity(Integer idCity) {
		this.idCity = idCity;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public List<Address> getAdresses() {
		return this.adresses;
	}

	public void setAdresses(List<Address> adresses) {
		this.adresses = adresses;
	}

	public Address addAdress(Address adress) {
		getAdresses().add(adress);
		adress.setCity(this);

		return adress;
	}

	public Address removeAdress(Address adress) {
		getAdresses().remove(adress);
		adress.setCity(null);

		return adress;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}