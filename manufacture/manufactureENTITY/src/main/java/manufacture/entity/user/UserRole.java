package manufacture.entity.user;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the civility database table.
 * 
 */
@Entity
@Table(name="user_role")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_user_role")
	private Integer idUserRole;

	@Column(name="role")
	private String role;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userRole")
	private List<User> users;

	public UserRole() {
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUserRole(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUserRole(null);

		return user;
	}

    public Integer getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Integer paramIdUserRole) {
        idUserRole = paramIdUserRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String paramRole) {
        role = paramRole;
    }

}