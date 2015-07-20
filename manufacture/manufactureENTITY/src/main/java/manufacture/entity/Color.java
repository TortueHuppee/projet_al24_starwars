package manufacture.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the color database table.
 * 
 */
@Entity
@NamedQuery(name="Color.findAll", query="SELECT c FROM Color c")
public class Color implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_color")
	private int idColor;

	@Column(name="color_name")
	private String colorName;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="color")
	private List<Product> products;

	public Color() {
	}

	public int getIdColor() {
		return this.idColor;
	}

	public void setIdColor(int idColor) {
		this.idColor = idColor;
	}

	public String getColorName() {
		return this.colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setColor(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setColor(null);

		return product;
	}

}