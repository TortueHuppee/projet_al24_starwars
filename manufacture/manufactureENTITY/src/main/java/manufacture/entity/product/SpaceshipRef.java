package manufacture.entity.product;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the spaceship_ref database table.
 * 
 */
@Entity
@Table(name="spaceship_ref")
public class SpaceshipRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_spaceship_ref")
	private int idSpaceshipRef;

	@Column(name="spaceship_name")
	private String spaceshipName;

	//bi-directional many-to-one association to SpaceshipProduct
	@OneToMany(mappedBy="spaceshipRef")
	private List<SpaceshipProduct> spaceshipProducts;

	public SpaceshipRef() {
	}

	public int getIdSpaceshipRef() {
		return this.idSpaceshipRef;
	}

	public void setIdSpaceshipRef(int idSpaceshipRef) {
		this.idSpaceshipRef = idSpaceshipRef;
	}

	public String getSpaceshipName() {
		return this.spaceshipName;
	}

	public void setSpaceshipName(String spaceshipName) {
		this.spaceshipName = spaceshipName;
	}

	public List<SpaceshipProduct> getSpaceshipProducts() {
		return this.spaceshipProducts;
	}

	public void setSpaceshipProducts(List<SpaceshipProduct> spaceshipProducts) {
		this.spaceshipProducts = spaceshipProducts;
	}

	public SpaceshipProduct addSpaceshipProduct(SpaceshipProduct spaceshipProduct) {
		getSpaceshipProducts().add(spaceshipProduct);
		spaceshipProduct.setSpaceshipRef(this);

		return spaceshipProduct;
	}

	public SpaceshipProduct removeSpaceshipProduct(SpaceshipProduct spaceshipProduct) {
		getSpaceshipProducts().remove(spaceshipProduct);
		spaceshipProduct.setSpaceshipRef(null);

		return spaceshipProduct;
	}

}