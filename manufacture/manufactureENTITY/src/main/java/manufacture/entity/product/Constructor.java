package manufacture.entity.product;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="constructor")
public class Constructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_constructor")
	private int idConstructor;

	@Column(name="constructor_name")
	private String constructorName;

	//bi-directional many-to-one association to ConstructorProduct
	@OneToMany(mappedBy="constructor")
	private List<ConstructorProduct> products;

	public Constructor() {
	}

	public int getIdConstructor() {
		return this.idConstructor;
	}

	public void setIdConstructor(int idConstructor) {
		this.idConstructor = idConstructor;
	}

	public String getConstructorName() {
		return this.constructorName;
	}

	public void setConstructorName(String constructorName) {
		this.constructorName = constructorName;
	}

	public List<ConstructorProduct> getProducts() {
		return this.products;
	}

	public void setProducts(List<ConstructorProduct> products) {
		this.products = products;
	}

	public ConstructorProduct addProduct(ConstructorProduct product) {
		getProducts().add(product);

		return product;
	}

	public ConstructorProduct removeProduct(ConstructorProduct product) {
		getProducts().remove(product);

		return product;
	}

}