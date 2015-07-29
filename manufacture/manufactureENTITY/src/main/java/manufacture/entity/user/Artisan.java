package manufacture.entity.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import manufacture.entity.product.ArtisanProduct;

@Entity
@DiscriminatorValue(value="artisan")
public class Artisan extends User {

	private static final long serialVersionUID = 1L;

	@Column(name = "activity_domain")
    private String activityDomain;
	
    @Column(name = "company_name")
    private String companyName;

    //bi-directional many-to-one association to Product
    @OneToMany(mappedBy = "user")
    private List<ArtisanProduct> artisanProducts;

    public Artisan(String paramActivityDomain, String paramCompanyName) {
        super();
        activityDomain = paramActivityDomain;
        companyName = paramCompanyName;
    }

    public Artisan()
	{
		
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

    public List<ArtisanProduct> getArtisanProducts() {
        return artisanProducts;
    }

    public void setArtisanProducts(List<ArtisanProduct> paramArtisanProducts) {
        artisanProducts = paramArtisanProducts;
    }
}
