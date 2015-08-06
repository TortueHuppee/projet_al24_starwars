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
	private List<Product> products;

	public Constructor() {
	}

	public Constructor(Integer idConstructor, String constructorName) {
		super();
		this.idConstructor = idConstructor;
		this.constructorName = constructorName;
	}

	public Integer getIdConstructor() {
		return this.idConstructor;
	}

	public void setIdConstructor(Integer idConstructor) {
		this.idConstructor = idConstructor;
	}

	public String getConstructorName() {
		return this.constructorName;
	}

	public void setConstructorName(String constructorName) {
		this.constructorName = constructorName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);

		return product;
	}

}