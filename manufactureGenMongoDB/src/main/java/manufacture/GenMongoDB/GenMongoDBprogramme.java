/**
 * 
 */
package manufacture.GenMongoDB;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import manufacture.entity.product.Product;
import manufacture.idao.product.IDaoProduct;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * @author yanick
 *
 */
public class GenMongoDBprogramme {

	private static Logger log = Logger.getLogger(GenMongoDBprogramme.class);
	private static String mongoDBname = "manufacture";
	private static String mongoDBCollectionName = "produits";
	private static final int maxProduitsParCommande = 50;
	private static final int maxQuantiteProduitsParCommande = 30;

	private static MongoClient mongoClient;

	private static BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
	private static IDaoProduct proxyProduct = (IDaoProduct) bf.getBean(IDaoProduct.class);
	private static List<Product> listeProductInMySQL;

	/**
	 * 
	 * @param client
	 */
	public static void afficheListeBases(MongoClient client) {
		MongoIterable<String> liste = client.listDatabaseNames();
		for (String name : liste) {
			int i = 0;
			log.info( i++ + ") " + name);
		}
	}
	/**
	 * 
	 * @param collection
	 * @param document
	 */
	public static void insererDocumentCommande(MongoCollection<Document> collection, Document document) {
		collection.insertOne(document);
	}
	/**
	 * 
	 * @param produitsRef
	 * @return
	 */
	public static List<Document> creerListProduits(List<Product> produitsRef) {
		List<Product> liste = new ArrayList<Product>();
		liste.addAll(produitsRef);
		List<Document> listDocuments = new ArrayList<Document>();
		int nombreProduits = ThreadLocalRandom.current().nextInt(0, maxProduitsParCommande);
		for (int i = 0; i < nombreProduits; i++) {
			int quantite = ThreadLocalRandom.current().nextInt(0, maxQuantiteProduitsParCommande);
			int quelProduit  = ThreadLocalRandom.current().nextInt(0, produitsRef.size());
			Product p = liste.get(quelProduit);
			Document doc = new Document();
			doc.append("dateMiseEnVente", new Date(p.getDatePublication().getTime()));
			doc.append("prix", p.getPrice());
			doc.append("type", p.getTypeProduct().getTypeProduct());
			doc.append("quantite", quantite);
			doc.append("productRef", new BasicDBObject("nom", p.getProductRef().getProductName()));
			doc.append("categorie", p.getProductRef().getCategory().getCategoryName());
			listDocuments.add(doc);
		}
		return listDocuments;
	}
	
	public static Document creerDocumentCommande() {
		//Creation d'un document Commande
		Document commande = new Document();
		//TODO repartir les dates ...
		commande.append("dateAchat", new Date());
		//Ajoute des produits aléatoirement
		List<Document> listeProduits = creerListProduits(listeProductInMySQL);
		commande.put("produits", listeProduits);
		
		return commande;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		log.info("Connection au serveur MongoDB");
		
		try {
			mongoClient = new MongoClient("localhost", 27017);
			afficheListeBases(mongoClient);
			MongoDatabase db = mongoClient.getDatabase(mongoDBname);
			if (db == null) {
				log.info("La base de données MongoDB : " + mongoDBname + " n'existe pas");
			}
			
			MongoCollection<Document> collection = db.getCollection(mongoDBCollectionName);
			if (collection != null) {
				
				listeProductInMySQL = proxyProduct.getAllProduct();
				Chrono c = new Chrono();
				c.start();
				for (int i = 0; i < 100000; i++) {
					Document doc = creerDocumentCommande();
					insererDocumentCommande(collection, doc);
				}
				c.stop();
				log.info("Temps ecoulé (ms):" + c.tempsEcoule());
			}

			mongoClient.close();
			
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			log.debug("Erreur de connexion au serveur MongoDB");
			log.debug(e.getMessage());
			//e.printStackTrace();
		} 

		log.info("Fin du programme " + Thread.currentThread().getName());
	}

}
