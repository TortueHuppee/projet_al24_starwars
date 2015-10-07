package manufacture.test.dao;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.mongodb.CategoryProduct;
import manufacture.entity.product.Product;
import manufacture.idao.cart.IDaoCart;
import manufacture.idao.cart.IDaoProductCart;
import manufacture.idao.mongodb.IDaoMongoDB;
import manufacture.idao.product.IDaoProduct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class TestMongoDB {

	private static DBCollection coll;
	
	private static BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");

	private static IDaoProduct proxyProduct = (IDaoProduct) bf.getBean(IDaoProduct.class);
	
	private static IDaoMongoDB proxyMongo = (IDaoMongoDB) bf.getBean(IDaoMongoDB.class);
	
	private static IDaoCart proxyCart = (IDaoCart) bf.getBean(IDaoCart.class);
	
	private static IDaoProductCart proxyCartProduct = (IDaoProductCart) bf.getBean(IDaoProductCart.class);

	public static void main(String[] args) {

		//Connexion au serveur MongoDb
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("localhost", 27017);

			//Connexion à la base "test"
//			DB db = mongoClient.getDB("manufacture");

//			System.out.println("Connexion à la base OK");

			//Connexion / Création collection (table)
//			coll = db.getCollection("produits");
//			System.out.println("Connexion à la table produits OK");
			
//			connexionMongoDB();
//			
//			grouperArticlesParCategorie();
			
//			testerCreationDaoMongo();
			
			testerRequeteDaoMongo();
			
//			List<Product> listeProductInMySQL = proxyProduct.getAllProduct();
			
			Calendar cal = Calendar.getInstance();
			cal.add(cal.MONTH, 2);
			SimpleDateFormat sdf = new SimpleDateFormat("MMMMMMMMMMMMMMMM");
			String date = sdf.format(cal.getTime());
			date = date.substring(0, 1).toUpperCase() + date.substring(1);
			System.out.println(date);
			cal.add(cal.MONTH, 1);
			date = sdf.format(cal.getTime());
			date = date.substring(0, 1).toUpperCase() + date.substring(1);
			System.out.println(date);
			
			System.out.println("DONE");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	static void testerRequeteDaoMongo()
	{
		List<CategoryProduct> result = proxyMongo.productsSellByCategoryAndOneMonthAgo();
		proxyMongo.productsSellByCategoryAndTwoMonthAgo();
		proxyMongo.productsSellByCategoryAndThreeMonthAgo();
		System.out.println("Nombre résultat : " + result.size());
		for (CategoryProduct cp : result)
		{
			System.out.println(cp.getCategory() + " : " + cp.getQuantity());
		}
	}
	
	static void testerCreationDaoMongo()
	{
		List<Cart> listeCart = proxyCart.getAllCart();
		for (Cart cart : listeCart)
		{
			List<CartProduct> listeCartProduct = proxyCartProduct.getCartProductByCart(cart);
			cart.setCartProducts(listeCartProduct);
			proxyMongo.createOrder(cart);
		}
	}
	
	static void connexionMongoDB() {
		DBCursor cursor = coll.find();
		int i = 1;
		while (cursor.hasNext())
		{
			System.out.println("Inserted Document : " + i);
			System.out.println(cursor.next());
			i++;
		}
		
		if (i == 1)
		{
//			injectionProduitsDansMongoDB();
		}
	}

	static void injectionProduitsDansMongoDB()
	{
		List<Product> listeProduct = proxyProduct.getAllProduct();
		
		for (Product product : listeProduct)
		 {
			 createNewMongoDBProduct(product);
		 }
		
		collFind("Vérification de l'injection des données");
	}

	static void createNewMongoDBProduct(Product product)
	{
		//Construction de l'objet
		BasicDBObject doc = new BasicDBObject("_id", product.getIdProduct())
		.append("stock",product.getStock())
		.append("dateMiseEnVente", product.getDatePublication())
		.append("prix", product.getPrice())
		.append("type", product.getTypeProduct().getTypeProduct())
		.append("productRef", new BasicDBObject("nom", product.getProductRef().getProductName())
			.append("categorie", product.getProductRef().getCategory().getCategoryName())
		);
		coll.insert(doc);

		System.out.println("Ajout du produit : " + product.getProductRef().getProductName());
	}

	static void collFind(String etape)
	{
		//Retrouver un ensemble de documents
		System.out.println("==================== [ " + etape + " ] ====================");
		DBCursor cursor = coll.find();
		int i = 1;
		while (cursor.hasNext())
		{
			System.out.println("Inserted Document : " + i);
			System.out.println(cursor.next());
			i++;
		}
		System.out.println();
	}
	
	static void grouperArticlesParCategorie()
	{
		//$project sert à sélectionner les colonnes
		DBObject project = (DBObject) JSON.parse("{$project:{'productRef.nom':1,'stock':1,'categorie':1, 'productRef.categorie':1,'_id':0}}");
		DBObject group = (DBObject) JSON.parse("{$group:{'_id':'$productRef.categorie','somme':{'$sum':'$stock'}}}");
		
		List<DBObject> operations = new ArrayList<DBObject>();
		operations.add(project);
		operations.add(group);
		
		AggregationOutput output = coll.aggregate(operations);
		Iterable<DBObject> cursor = output.results();
		
		for (DBObject moObj : cursor)
		{
			System.out.println(moObj.get("_id"));
		}
	}

	public DBCollection getColl() {
		return coll;
	}

	public void setColl(DBCollection coll) {
		this.coll = coll;
	}

	public static BeanFactory getBf() {
		return bf;
	}

	public static void setBf(BeanFactory bf) {
		TestMongoDB.bf = bf;
	}

	public static IDaoProduct getProxyProduct() {
		return proxyProduct;
	}

	public static void setProxyProduct(IDaoProduct proxyProduct) {
		TestMongoDB.proxyProduct = proxyProduct;
	}

	public static IDaoMongoDB getProxyMongo() {
		return proxyMongo;
	}

	public static void setProxyMongo(IDaoMongoDB proxyMongo) {
		TestMongoDB.proxyMongo = proxyMongo;
	}

	public static IDaoCart getProxyCart() {
		return proxyCart;
	}

	public static void setProxyCart(IDaoCart proxyCart) {
		TestMongoDB.proxyCart = proxyCart;
	}

	public static IDaoProductCart getProxyCartProduct() {
		return proxyCartProduct;
	}

	public static void setProxyCartProduct(IDaoProductCart proxyCartProduct) {
		TestMongoDB.proxyCartProduct = proxyCartProduct;
	}
}
