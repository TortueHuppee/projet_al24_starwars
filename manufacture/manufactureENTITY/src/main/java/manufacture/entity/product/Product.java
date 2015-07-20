package manufacture.entity.product;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import manufacture.entity.cart.CartProduct;
import manufacture.entity.report.Reporting;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
//Héritage :
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//Pour distinguer nos entités qui sont réunies dans une seule table :
@DiscriminatorColumn(name="type_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_product")
	private int idProduct;

	@Column(name="is_disabled")
	private byte isDisabled;

	private double price;

	private int stock;

	//bi-directional many-to-one association to CartProduct
	@OneToMany(mappedBy="product")
	private List<CartProduct> cartProducts;

	//bi-directional many-to-one association to Color
	@ManyToOne
	@JoinColumn(name="id_color")
	private Color color;

	//bi-directional many-to-one association to Material
	@ManyToOne
	@JoinColumn(name="id_material")
	private Material material;

	//bi-directional many-to-one association to ProductRef
	@ManyToOne
	@JoinColumn(name="id_product_ref")
	private ProductRef productRef;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="product")
	private List<Rating> ratings;

	//bi-directional many-to-one association to Reporting
	@OneToMany(mappedBy="product")
	private List<Reporting> reportings;

	public Product() {
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public byte getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(byte isDisabled) {
		this.isDisabled = isDisabled;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<CartProduct> getCartProducts() {
		return this.cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public CartProduct addCartProduct(CartProduct cartProduct) {
		getCartProducts().add(cartProduct);
		cartProduct.setProduct(this);

		return cartProduct;
	}

	public CartProduct removeCartProduct(CartProduct cartProduct) {
		getCartProducts().remove(cartProduct);
		cartProduct.setProduct(null);

		return cartProduct;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public ProductRef getProductRef() {
		return this.productRef;
	}

	public void setProductRef(ProductRef productRef) {
		this.productRef = productRef;
	}

	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Rating addRating(Rating rating) {
		getRatings().add(rating);
		rating.setProduct(this);

		return rating;
	}

	public Rating removeRating(Rating rating) {
		getRatings().remove(rating);
		rating.setProduct(null);

		return rating;
	}

	public List<Reporting> getReportings() {
		return this.reportings;
	}

	public void setReportings(List<Reporting> reportings) {
		this.reportings = reportings;
	}

	public Reporting addReporting(Reporting reporting) {
		getReportings().add(reporting);
		reporting.setProduct(this);

		return reporting;
	}

	public Reporting removeReporting(Reporting reporting) {
		getReportings().remove(reporting);
		reporting.setProduct(null);

		return reporting;
	}

}