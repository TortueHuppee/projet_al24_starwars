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

	@Column(name = "activity_domain")
    private String activityDomain;
	
    @Column(name = "company_name")
    private String companyName;

    public ProfessionnalCustomer(int paramNbRecall, String paramActivityDomain,
            String paramCompanyName) {
        super();
        nbRecall = paramNbRecall;
        activityDomain = paramActivityDomain;
        companyName = paramCompanyName;
    }

    public ProfessionnalCustomer()
	{
		
	}

	public int getNbRecall() {
		return nbRecall;
	}


	public void setNbRecall(int nbRecall) {
		this.nbRecall = nbRecall;
	}

    public String getActivityDomain() {
        return activityDomain;
    }

    public void setActivityDomain(String paramActivityDomain) {
        activityDomain = paramActivityDomain;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String paramCompanyName) {
        companyName = paramCompanyName;
    }

}
