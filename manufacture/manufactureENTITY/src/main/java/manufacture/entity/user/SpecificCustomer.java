package manufacture.entity.user;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import manufacture.entity.product.UsedProduct;

@Entity
@DiscriminatorValue(value="specific_customer")
public class SpecificCustomer extends User {

	private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "user")
    private List<UsedProduct> usedProducts;
	
	public SpecificCustomer()
	{
		
	}

    public List<UsedProduct> getUsedProducts() {
        return usedProducts;
    }

    public void setUsedProducts(List<UsedProduct> paramUsedProducts) {
        usedProducts = paramUsedProducts;
    }

}
