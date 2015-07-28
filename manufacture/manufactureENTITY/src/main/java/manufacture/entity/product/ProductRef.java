package manufacture.entity.product;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the product_ref database table.
 * 
 */
@Entity
@Table(name="product_ref")
public class ProductRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_product_ref")
	private Integer idProductRef;

	private String description;

	@Column(name="product_name")
	private String productName;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productRef")
	private List<Product> products;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to SpaceshipProduct
	@OneToMany(mappedBy="productRef")
	private List<SpaceshipProduct> spaceshipProducts;
	
	@Column(name="url_image")
	private String urlImage;

	public ProductRef() {
	}

	public Integer getIdProductRef() {
		return this.idProductRef;
	}

	public void setIdProductRef(Integer idProductRef) {
		this.idProductRef = idProductRef;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductRef(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductRef(null);

		return product;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<SpaceshipProduct> getSpaceshipProducts() {
		return this.spaceshipProducts;
	}

	public void setSpaceshipProducts(List<SpaceshipProduct> spaceshipProducts) {
		this.spaceshipProducts = spaceshipProducts;
	}

	public SpaceshipProduct addSpaceshipProduct(SpaceshipProduct spaceshipProduct) {
		getSpaceshipProducts().add(spaceshipProduct);
		spaceshipProduct.setProductRef(this);

		return spaceshipProduct;
	}

	public SpaceshipProduct removeSpaceshipProduct(SpaceshipProduct spaceshipProduct) {
		getSpaceshipProducts().remove(spaceshipProduct);
		spaceshipProduct.setProductRef(null);

		return spaceshipProduct;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
}
