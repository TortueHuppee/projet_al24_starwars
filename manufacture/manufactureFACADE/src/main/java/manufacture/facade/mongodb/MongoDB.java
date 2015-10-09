package manufacture.facade.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.mongodb.CategoryProduct;
import manufacture.entity.mongodb.TypeProductProduct;
import manufacture.entity.product.Product;
import manufacture.ibusiness.mongodb.IBusinessMongoDB;
import manufacture.ifacade.mongodb.IMongoDB;

@Service
public class MongoDB implements IMongoDB{

	private IBusinessMongoDB proxyMongo;
	
	@Override
	public BasicDBObject createProduct(CartProduct cartProduct) {
		return proxyMongo.createProduct(cartProduct);
	}

	@Override
	public void createOrder(Cart cart) {
		proxyMongo.createOrder(cart);
	}

	@Override
	public List<CategoryProduct> productsSellByCategoryAndMonth() {
		return proxyMongo.productsSellByCategoryAndMonth();
	}

	@Override
	public List<CategoryProduct> productsSellByCategoryAndDay() {
		return proxyMongo.productsSellByCategoryAndDay();
	}

	@Override
	public List<CategoryProduct> productsPublishedByCategoryAndDay() {
		return proxyMongo.productsPublishedByCategoryAndDay();
	}
	
	@Override
	public List<CategoryProduct> productsSellByCategoryAndOneMonthAgo() {
		return proxyMongo.productsSellByCategoryAndOneMonthAgo();
	}

	@Override
	public List<CategoryProduct> productsSellByCategoryAndTwoMonthAgo() {
		return proxyMongo.productsSellByCategoryAndTwoMonthAgo();
	}

	@Override
	public List<CategoryProduct> productsSellByCategoryAndThreeMonthAgo() {
		return proxyMongo.productsSellByCategoryAndThreeMonthAgo();
	}

	public IBusinessMongoDB getProxyMongo() {
		return proxyMongo;
	}

	@Autowired
	public void setProxyMongo(IBusinessMongoDB proxyMongo) {
		this.proxyMongo = proxyMongo;
	}

	@Override
	public List<TypeProductProduct> productsSellByTypeProductAndMonth() {
		return proxyMongo.productsSellByTypeProductAndMonth();
	}
}
