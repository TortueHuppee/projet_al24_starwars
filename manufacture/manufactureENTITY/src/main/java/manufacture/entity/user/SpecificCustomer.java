package manufacture.entity.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="specific_customer")
public class SpecificCustomer extends User {

	private static final long serialVersionUID = 1L;

	
	public SpecificCustomer()
	{
		
	}
}
