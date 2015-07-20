package manufacture.entity.product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="constructor_product")
public class ConstructorProduct extends Product {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//bi-directional many-to-one association to Constructor
	@ManyToOne
	@JoinColumn(name="id_constructor")
	private Constructor constructor;

	public ConstructorProduct() {
		super();
	}

	public Constructor getConstructor() {
		return constructor;
	}

	public void setConstructor(Constructor constructor) {
		this.constructor = constructor;
	}
}
