package manufacture.entity.product;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import manufacture.entity.cart.CartProduct;
import manufacture.entity.report.Reporting;
import manufacture.entity.user.User;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_product")
	private Integer idProduct;

	//Par défaut FALSE = 0 et TRUE = 1
	@Column(name="is_disabled")
	private boolean isDisabled;

	private double price;

	private int stock;

	//bi-directional many-to-one association to CartProduct
	@OneToMany(mappedBy="product")
	private List<CartProduct> cartProducts;

	//bi-directional many-to-one association to Color
	@ManyToOne
	@JoinColumn(name="id_color")
	private Color color;
	
	@ManyToOne
	@JoinColumn(name="id_type_product")
	private TypeProduct typeProduct;

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
	
	//bi-directional many-to-one association to Constructor
	@ManyToOne
	@JoinColumn(name="id_constructor")
	private Constructor constructor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_publication")
	private Date datePublication;
	
	@Column(name="seller_comment")
	private String sellerComment;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="seller_id_user")
	private User user;

	public Product() {
	}

	public Integer getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	
	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
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
	
	public Constructor getConstructor() {
		return constructor;
	}

	public void setConstructor(Constructor constructor) {
		this.constructor = constructor;
	}
	
	public Date getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public String getSellerComment() {
		return sellerComment;
	}

	public void setSellerComment(String sellerComment) {
		this.sellerComment = sellerComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TypeProduct getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(TypeProduct typeProduct) {
		this.typeProduct = typeProduct;
	}
}
