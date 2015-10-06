package manufacture.dao.mongodb;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.mongodb.CategoryProduct;
import manufacture.entity.product.Product;
import manufacture.idao.mongodb.IDaoMongoDB;

@Service
@Transactional
public class DaoMongoDB implements IDaoMongoDB {

	private DBCollection dbCollection;

	public DaoMongoDB() {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e){
			e.printStackTrace();
		}
		DB db = mongoClient.getDB("manufacture");
		DBCollection coll = db.getCollection("produits");
		setDbCollection(coll);
	}

	@SuppressWarnings("static-access")
	@Override
	public List<CategoryProduct> productsSellByCategoryAndMonth() {

		List<CategoryProduct> result = new ArrayList<>();

		DBObject unwind = (DBObject) JSON.parse("{$unwind:'$produits'}");

		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.DATE,1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		DBObject match = new BasicDBObject("$match",
				new BasicDBObject("dateAchat",
				new BasicDBObject("$gte",calendar.getTime())));

		DBObject project = (DBObject) JSON.parse("{$project:{'produits.quantite':1, "
				+ "'produits.productRef.categorie':1,"
				+ "'_id':0}}");
		DBObject group = (DBObject) JSON.parse("{$group:{'_id':'$produits.productRef.categorie',"
				+ "'somme':{'$sum':'$produits.quantite'}}}");

		List<DBObject> operations = new ArrayList<DBObject>();
		operations.add(unwind);
		operations.add(match);
		operations.add(project);
		operations.add(group);

		AggregationOutput output = dbCollection.aggregate(operations);
		Iterable<DBObject> cursor = output.results();

		for (DBObject moObj : cursor)
		{
			CategoryProduct cp = new CategoryProduct();
			cp.setCategory(moObj.get("_id").toString());
			int quantity = Integer.parseInt(moObj.get("somme").toString());
			cp.setQuantity(quantity);
			result.add(cp);
		}
		return result;
	}

	@Override
	public List<CategoryProduct> productsSellByCategoryAndDay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryProduct> productsPublishedByCategoryAndDay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createOrder(Cart cart) {
		BasicDBObject panier = new BasicDBObject("_id", cart.getIdCart())
		.append("dateAchat", cart.getDateCommande());

		List<BasicDBObject> listeProduits = new ArrayList<>();
		List<CartProduct> listeCartProduct = cart.getCartProducts();

		for (CartProduct cartProduct : listeCartProduct)
		{
			listeProduits.add(createProduct(cartProduct));
		}

		panier.put("produits", listeProduits);
		dbCollection.insert(panier);
	}

	@Override
	public BasicDBObject createProduct(CartProduct cartProduct) {
		Product product = cartProduct.getProduct();
		BasicDBObject doc = new BasicDBObject("product_id", product.getIdProduct())
		.append("dateMiseEnVente", product.getDatePublication())
		.append("prix", product.getPrice())
		.append("type", product.getTypeProduct().getTypeProduct())
		.append("quantite", cartProduct.getQuantity())
		.append("productRef", new BasicDBObject("nom", product.getProductRef().getProductName())
		.append("categorie", product.getProductRef().getCategory().getCategoryName())
				);
		//		dbCollection.insert(doc);
		return doc;
	}

	public DBCollection getDbCollection() {
		return dbCollection;
	}

	public void setDbCollection(DBCollection dbCollection) {
		this.dbCollection = dbCollection;
	}
}
