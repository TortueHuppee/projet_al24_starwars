package manufacture.entity.user;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the civility database table.
 * 
 */
@Entity
@Table(name="civility")
public class Civility implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_civility")
	private int idCivility;

	private String civility;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="civility")
	private List<User> users;

	public Civility() {
	}

	public int getIdCivility() {
		return this.idCivility;
	}

	public void setIdCivility(int idCivility) {
		this.idCivility = idCivility;
	}

	public String getCivility() {
		return this.civility;
	}

	public void setCivility(String civility) {
		this.civility = civility;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setCivility(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setCivility(null);

		return user;
	}

}