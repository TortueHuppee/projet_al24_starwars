package manufacture.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the constructor database table.
 * 
 */
@Entity
@NamedQuery(name="Constructor.findAll", query="SELECT c FROM Constructor c")
public class Constructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_constructor")
	private int idConstructor;

	@Column(name="constructor_name")
	private String constructorName;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="constructor")
	private List<Product> products;

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

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setConstructor(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setConstructor(null);

		return product;
	}

}