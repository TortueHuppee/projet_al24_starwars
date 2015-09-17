package manufacture.ibusiness.advert;

import manufacture.entity.product.Product;

public interface IBusinessAdvert {

	void createAdvert(Product product);

	void updateAdvertState(Product product);
}
