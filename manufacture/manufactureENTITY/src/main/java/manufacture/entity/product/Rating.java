package manufacture.entity.product;

import java.io.Serializable;

import javax.persistence.*;

import manufacture.entity.user.User;


/**
 * The persistent class for the rating database table.
 * 
 */
@Entity
@Table(name="rating")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_rating")
	private Integer idRating;

	private String comment;

	private int value;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	public Rating() {
	}

	public Integer getIdRating() {
		return this.idRating;
	}

	public void setIdRating(Integer idRating) {
		this.idRating = idRating;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}