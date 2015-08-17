package manufacture.business.advert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.product.Product;
import manufacture.ibusiness.advert.IBusinessAdvert;
import manufacture.idao.product.IDaoProduct;

@Service
public class BusinessAdvert implements IBusinessAdvert {

	private IDaoProduct proxyProduct;
	
	@Override
	public void createAdvert(Product product) {
		proxyProduct.addProduct(product);
	}

	public IDaoProduct getProxyProduct() {
		return proxyProduct;
	}

	@Autowired
	public void setProxyProduct(IDaoProduct proxyProduct) {
		this.proxyProduct = proxyProduct;
	}
}
