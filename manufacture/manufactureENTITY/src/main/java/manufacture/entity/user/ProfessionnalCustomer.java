package manufacture.entity.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import manufacture.entity.user.User;

@Entity
@DiscriminatorValue(value="professional_customer")
public class ProfessionnalCustomer extends User {

	private static final long serialVersionUID = 1L;

	
	public ProfessionnalCustomer()
	{
		
	}
}
