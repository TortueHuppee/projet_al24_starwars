package manufacture.entity.user;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="professional_customer")
public class ProfessionnalCustomer extends User {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "number_recall")
	private int nbRecall ;

	
	public ProfessionnalCustomer()
	{
		
	}


	public int getNbRecall() {
		return nbRecall;
	}


	public void setNbRecall(int nbRecall) {
		this.nbRecall = nbRecall;
	}
	
	
}
