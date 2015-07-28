package manufacture.entity.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="artisan")
public class Artisan extends User {

	private static final long serialVersionUID = 1L;

	
	public Artisan()
	{
		
	}
}
