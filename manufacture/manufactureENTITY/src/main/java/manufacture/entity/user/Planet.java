package manufacture.entity.user;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the planet database table.
 * 
 */
@Entity
@Table(name="planet")
public class Planet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_planet")
	private Integer idPlanet;

	@Column(name="planet_name")
	private String planetName;

	//bi-directional many-to-one association to Country
	@OneToMany(mappedBy="planet")
	private List<Country> countries;

	public Planet() {
	}

	public Integer getIdPlanet() {
		return this.idPlanet;
	}

	public void setIdPlanet(Integer idPlanet) {
		this.idPlanet = idPlanet;
	}

	public String getPlanetName() {
		return this.planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public List<Country> getCountries() {
		return this.countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Country addCountry(Country country) {
		getCountries().add(country);
		country.setPlanet(this);

		return country;
	}

	public Country removeCountry(Country country) {
		getCountries().remove(country);
		country.setPlanet(null);

		return country;
	}

}