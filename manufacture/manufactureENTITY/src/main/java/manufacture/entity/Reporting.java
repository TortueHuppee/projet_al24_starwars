package manufacture.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reporting database table.
 * 
 */
@Entity
@NamedQuery(name="Reporting.findAll", query="SELECT r FROM Reporting r")
public class Reporting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_reporting")
	private int idReporting;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name="is_treated")
	private byte isTreated;

	private String reason;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="reported_product_id_product")
	private Product product;

	//bi-directional many-to-one association to ReportingDecision
	@ManyToOne
	@JoinColumn(name="id_reporting_decision")
	private ReportingDecision reportingDecision;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="reported_user_id_user")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="reporter_id_user")
	private User user2;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="administrator_id_user")
	private User user3;

	public Reporting() {
	}

	public int getIdReporting() {
		return this.idReporting;
	}

	public void setIdReporting(int idReporting) {
		this.idReporting = idReporting;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public byte getIsTreated() {
		return this.isTreated;
	}

	public void setIsTreated(byte isTreated) {
		this.isTreated = isTreated;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ReportingDecision getReportingDecision() {
		return this.reportingDecision;
	}

	public void setReportingDecision(ReportingDecision reportingDecision) {
		this.reportingDecision = reportingDecision;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public User getUser3() {
		return this.user3;
	}

	public void setUser3(User user3) {
		this.user3 = user3;
	}

}