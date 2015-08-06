package manufacture.entity.product;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the color database table.
 * 
 */
@Entity
@Table(name="type_product")
public class TypeProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_type_product")
	private Integer idTypeProduct;

	@Column(name="type_product")
	private String typeProduct;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="typeProduct")
	private List<Product> products;

	public TypeProduct() {
	}
	
	public TypeProduct(Integer idTypeProduct, String typeProduct) {
		super();
		this.idTypeProduct = idTypeProduct;
		this.typeProduct = typeProduct;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setTypeProduct(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setTypeProduct(null);

		return product;
	}

	public Integer getIdTypeProduct() {
		return idTypeProduct;
	}

	public void setIdTypeProduct(Integer idTypeProduct) {
		this.idTypeProduct = idTypeProduct;
	}

	public String getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
	}

}