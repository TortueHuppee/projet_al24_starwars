package manufacture.entity.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="administrator")
public class Administrator extends User {

	private static final long serialVersionUID = 1L;

	
	public Administrator()
	{
		
	}
}
