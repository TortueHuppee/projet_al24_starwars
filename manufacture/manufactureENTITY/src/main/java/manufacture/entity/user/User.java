package manufacture.entity.user;

import java.io.Serializable;

import javax.persistence.*;

import manufacture.entity.cart.Cart;
import manufacture.entity.product.ArtisanProduct;
import manufacture.entity.product.Product;
import manufacture.entity.product.Rating;
import manufacture.entity.product.UsedProduct;
import manufacture.entity.report.Reporting;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
//Héritage :
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//Pour distinguer nos entités qui sont réunies dans une seule table :
@DiscriminatorColumn(name="user_role")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_user")
	private Integer idUser;

	@Column(name="activity_domain")
	private String activityDomain;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="closing_time")
	private Date closingTime;

	@Column(name="company_name")
	private String companyName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String email;

	private String login;

	private String password;

	@Column(name="user_first_name")
	private String userFirstName;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to Cart
	@OneToMany(mappedBy="user")
	private List<Cart> carts;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="user")
	private List<ArtisanProduct> artisanProducts;
	
	@OneToMany(mappedBy="user")
	private List<UsedProduct> usedProducts;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="user")
	private List<Rating> ratings;

	//bi-directional many-to-one association to Reporting
	@OneToMany(mappedBy="user1")
	private List<Reporting> reportings1;

	//bi-directional many-to-one association to Reporting
	@OneToMany(mappedBy="user2")
	private List<Reporting> reportings2;

	//bi-directional many-to-one association to Reporting
	@OneToMany(mappedBy="user3")
	private List<Reporting> reportings3;

	//bi-directional many-to-one association to Civility
	@ManyToOne
	@JoinColumn(name="id_civility")
	private Civility civility;

	//bi-directional many-to-one association to Adress
	@ManyToOne
	@JoinColumn(name="id_address")
	private Address address;

	public User() {
	}

	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getActivityDomain() {
		return this.activityDomain;
	}

	public void setActivityDomain(String activityDomain) {
		this.activityDomain = activityDomain;
	}

	public Date getClosingTime() {
		return this.closingTime;
	}

	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserFirstName() {
		return this.userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setUser(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setUser(null);

		return cart;
	}

	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Rating addRating(Rating rating) {
		getRatings().add(rating);
		rating.setUser(this);

		return rating;
	}

	public Rating removeRating(Rating rating) {
		getRatings().remove(rating);
		rating.setUser(null);

		return rating;
	}

	public List<Reporting> getReportings1() {
		return this.reportings1;
	}

	public void setReportings1(List<Reporting> reportings1) {
		this.reportings1 = reportings1;
	}

	public Reporting addReportings1(Reporting reportings1) {
		getReportings1().add(reportings1);
		reportings1.setUser1(this);

		return reportings1;
	}

	public Reporting removeReportings1(Reporting reportings1) {
		getReportings1().remove(reportings1);
		reportings1.setUser1(null);

		return reportings1;
	}

	public List<Reporting> getReportings2() {
		return this.reportings2;
	}

	public void setReportings2(List<Reporting> reportings2) {
		this.reportings2 = reportings2;
	}

	public Reporting addReportings2(Reporting reportings2) {
		getReportings2().add(reportings2);
		reportings2.setUser2(this);

		return reportings2;
	}

	public Reporting removeReportings2(Reporting reportings2) {
		getReportings2().remove(reportings2);
		reportings2.setUser2(null);

		return reportings2;
	}

	public List<Reporting> getReportings3() {
		return this.reportings3;
	}

	public void setReportings3(List<Reporting> reportings3) {
		this.reportings3 = reportings3;
	}

	public Reporting addReportings3(Reporting reportings3) {
		getReportings3().add(reportings3);
		reportings3.setUser3(this);

		return reportings3;
	}

	public Reporting removeReportings3(Reporting reportings3) {
		getReportings3().remove(reportings3);
		reportings3.setUser3(null);

		return reportings3;
	}

	public Civility getCivility() {
		return this.civility;
	}

	public void setCivility(Civility civility) {
		this.civility = civility;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<ArtisanProduct> getArtisanProducts() {
		return artisanProducts;
	}

	public void setArtisanProducts(List<ArtisanProduct> artisanProducts) {
		this.artisanProducts = artisanProducts;
	}

	public List<UsedProduct> getUsedProducts() {
		return usedProducts;
	}

	public void setUsedProducts(List<UsedProduct> usedProducts) {
		this.usedProducts = usedProducts;
	}

}