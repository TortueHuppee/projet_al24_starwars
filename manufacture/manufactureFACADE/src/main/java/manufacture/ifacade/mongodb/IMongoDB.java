package manufacture.ifacade.mongodb;

import java.util.List;

import com.mongodb.BasicDBObject;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.mongodb.CategoryProduct;
import manufacture.entity.product.Product;

public interface IMongoDB {

BasicDBObject createProduct(CartProduct cartProduct);
	
	void createOrder(Cart cart);
	
	List<CategoryProduct> productsSellByCategoryAndMonth();
	
	List<CategoryProduct> productsSellByCategoryAndDay();
	
	List<CategoryProduct> productsPublishedByCategoryAndDay();
	
	List<CategoryProduct> productsSellByCategoryAndOneMonthAgo();
	
	List<CategoryProduct> productsSellByCategoryAndTwoMonthAgo();
	
	List<CategoryProduct> productsSellByCategoryAndThreeMonthAgo();
}
