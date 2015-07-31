package manufacture.entity.cart;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import manufacture.entity.user.Address;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name="relay_point")
public class RelayPoint implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_relay_point")
	private Integer idRelayPoint;

	@Column(name="relay_point_name")
	private String RelayPointName;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="id_adress")
	private Address addresse;

	public RelayPoint() {
	}

    public Integer getIdRelayPoint() {
        return idRelayPoint;
    }

    public void setIdRelayPoint(Integer paramIdRelayPoint) {
        idRelayPoint = paramIdRelayPoint;
    }

    public String getRelayPointName() {
        return RelayPointName;
    }

    public void setRelayPointName(String paramRelayPointName) {
        RelayPointName = paramRelayPointName;
    }

    public Address getAddresse() {
        return addresse;
    }

    public void setAddresse(Address paramAddresse) {
        addresse = paramAddresse;
    }
}