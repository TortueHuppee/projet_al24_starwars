package manufacture.entity.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import manufacture.entity.user.User;

@Entity
@DiscriminatorValue(value="used_product")
public class UsedProduct extends Product {

	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_publication")
	private Date datePublication;
	
	@Column(name="seller_comment")
	private String sellerComment;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="seller_id_user")
	private User user;

	public UsedProduct(Date datePublication, String sellerComment, User user) {
		super();
		this.datePublication = datePublication;
		this.sellerComment = sellerComment;
		this.user = user;
	}
	
	public UsedProduct()
	{
		
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
}
