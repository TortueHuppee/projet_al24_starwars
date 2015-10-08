/**
 * Programme permettant de générer un historique des commandes de produits
 * et de générer des commandes en temps réel et d'enregistrer cela dans
 * une base MongoDB
 */
package manufacture.GenMongoDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import manufacture.entity.product.Product;
import manufacture.idao.product.IDaoProduct;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
	private static long nombreDocumentsAgenerer = 10;
	private static Date dateMiseEnVente;

	/**
	 * Generer l'historique ou le temps réel.
	 */
	private static boolean genererHistorique = true;

	/**
	 * Intervale de temps entre deux commandes
	 */
	private static long intervalDateCommande; 

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

			//Date de mise en ligne depuis la base ?
			//doc.append("dateMiseEnVente", new Date(p.getDatePublication().getTime()));
			doc.append("dateMiseEnVente", dateMiseEnVente);
			doc.append("prix", p.getPrice());
			doc.append("type", p.getTypeProduct().getTypeProduct());
			doc.append("quantite", quantite);
			doc.append("productRef", new Document("nom", p.getProductRef().getProductName()).append("categorie", p.getProductRef().getCategory().getCategoryName() ));
			listDocuments.add(doc);
		}
		return listDocuments;
	}

	/**
	 * 
	 * @param dateCommande
	 * @return
	 */
	public static Document creerDocumentCommande(Date dateCommande) {
		//Creation d'un document Commande
		Document commande = new Document();
		commande.append("dateAchat", dateCommande);
		//Ajoute des produits aléatoirement
		List<Document> listeProduits = creerListProduits(listeProductInMySQL);
		commande.put("produits", listeProduits);

		return commande;
	}
	/**
	 * 
	 * @param collection
	 */
	public static void genererHistoriqueCommandes(MongoCollection<Document> collection) {
		if (collection != null) {

			//Date de mise en ligne fixe pour tous les produits
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				dateMiseEnVente = sdf.parse("01-01-2015");
			} catch (ParseException e) {
				log.debug("Erreur SimpleDateFormat.");
				e.printStackTrace();
			}

			log.info("Début de la génération de la base MongoDB : " + mongoDBname);
			Chrono c = new Chrono();
			c.start();
			//La premiere commande est datée du début de la mise en vente
			Date dateCommande = new Date(dateMiseEnVente.getTime());

			intervalDateCommande = (long)(((new Date()).getTime() - dateMiseEnVente.getTime()) / nombreDocumentsAgenerer);
			if (intervalDateCommande == 0) {
				log.info("Intervale entre deux dates de commandes est nul.");
			} else {
				log.info("Intervalle entre deux dates de commandes (ms) : " + intervalDateCommande);
			}

			for (long i = 0; i < nombreDocumentsAgenerer; i++) {
				Document doc = creerDocumentCommande(dateCommande);
				insererDocumentCommande(collection, doc);
				dateCommande.setTime(dateCommande.getTime() + intervalDateCommande);
			}
			c.stop();
			log.info("Fin de la génération de la base MongoDB : " + mongoDBname);
			log.info("Temps ecoulé (ms):" + c.tempsEcoule());
		}
	}
	/**
	 * 
	 * @param collection
	 * @param interval
	 */
	public static void genererTempsReelCommandes(MongoCollection<Document> collection, long interval) {
		long intervalMSentreDeuxCommandes = interval;

		while (true) {

			try {
				Document doc = creerDocumentCommande(new Date());
				insererDocumentCommande(collection, doc);
				Thread.sleep(intervalMSentreDeuxCommandes);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (nombreDocumentsAgenerer <= 0 ){
			log.info("Le nombre de documents à générer doit être > 0");
			return ;
		}

		//Lecture de la liste de produits à partir de la base de données SQL
		listeProductInMySQL = proxyProduct.getAllProduct();

		log.info("Connection au serveur MongoDB");

		try {
			mongoClient = new MongoClient("localhost", 27017);
			afficheListeBases(mongoClient);
			MongoDatabase db = mongoClient.getDatabase(mongoDBname);
			if (db == null) {
				log.info("La base de données MongoDB : " + mongoDBname + " n'existe pas");
			}

			MongoCollection<Document> collection = db.getCollection(mongoDBCollectionName);

			if (genererHistorique) {
				genererHistoriqueCommandes(collection);
			}

			log.info("Debut de génération temps réel des commandes");
			genererTempsReelCommandes(collection, 1000);
			log.info("Fin de génération temps réel des commandes");


		} catch (MongoException e) {
			// TODO Auto-generated catch block
			log.debug("Erreur de connexion au serveur MongoDB");
			log.debug(e.getMessage());
			//e.printStackTrace();
		} finally {
			log.info("Fermeture de la connexion à la base MongoDB.");
			mongoClient.close();
		}
		log.info("Fin du programme " + Thread.currentThread().getName());
	}
}
