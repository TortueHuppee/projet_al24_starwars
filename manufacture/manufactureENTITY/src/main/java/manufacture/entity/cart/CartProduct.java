package manufacture.entity.cart;

import java.io.Serializable;

import javax.persistence.*;

import manufacture.entity.product.Product;


/**
 * The persistent class for the cart_product database table.
 * 
 */
@Entity
@Table(name="cart_product")
@NamedQuery(name="CartProduct.findAll", query="SELECT c FROM CartProduct c")
public class CartProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cart_product")
	private Integer idCartProduct;

	private int quantity;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;

	//bi-directional many-to-one association to Cart
	@ManyToOne
	@JoinColumn(name="id_cart")
	private Cart cart;

	public CartProduct() {
	}

	public Integer getIdCartProduct() {
		return this.idCartProduct;
	}

	public void setIdCartProduct(Integer idCartProduct) {
		this.idCartProduct = idCartProduct;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}