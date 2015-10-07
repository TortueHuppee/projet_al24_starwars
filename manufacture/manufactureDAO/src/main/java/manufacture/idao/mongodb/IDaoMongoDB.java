package manufacture.idao.mongodb;

import java.util.List;

import com.mongodb.BasicDBObject;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.mongodb.CategoryProduct;
import manufacture.entity.mongodb.TypeProductProduct;
import manufacture.entity.product.Product;

public interface IDaoMongoDB {

	BasicDBObject createProduct(CartProduct cartProduct);
	
	void createOrder(Cart cart);
	
	List<CategoryProduct> productsSellByCategoryAndMonth();
	
	List<CategoryProduct> productsSellByCategoryAndOneMonthAgo();
	
	List<CategoryProduct> productsSellByCategoryAndTwoMonthAgo();
	
	List<CategoryProduct> productsSellByCategoryAndThreeMonthAgo();
	
	List<CategoryProduct> productsSellByCategoryAndDay();
	
	List<CategoryProduct> productsPublishedByCategoryAndDay();
	
	List<TypeProductProduct> productsSellByTypeProductAndMonth();
}
