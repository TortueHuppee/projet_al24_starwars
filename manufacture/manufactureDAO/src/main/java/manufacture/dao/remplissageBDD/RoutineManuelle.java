package manufacture.dao.remplissageBDD;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.idao.product.IDaoCategory;
import manufacture.idao.product.IDaoColor;
import manufacture.idao.product.IDaoConstructor;
import manufacture.idao.product.IDaoMaterial;
import manufacture.idao.product.IDaoProduct;
import manufacture.idao.product.IDaoProductRef;
import manufacture.idao.product.IDaoSpaceShipRef;
import manufacture.idao.user.IDaoAdress;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RoutineManuelle {

	//Logger
	Logger log = Logger.getLogger(RoutineManuelle.class);

	//Initialisation des bean
	BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
	IDaoColor proxyColor = (IDaoColor) bf.getBean(IDaoColor.class);
	IDaoCategory proxyCategory = (IDaoCategory) bf.getBean(IDaoCategory.class);
	IDaoConstructor proxyConstructor = (IDaoConstructor) bf.getBean(IDaoConstructor.class);
	IDaoMaterial proxyMaterial = (IDaoMaterial) bf.getBean(IDaoMaterial.class);
	IDaoProductRef proxyProductRef = (IDaoProductRef) bf.getBean(IDaoProductRef.class);
	IDaoSpaceShipRef proxySpaceShipRef = (IDaoSpaceShipRef) bf.getBean(IDaoSpaceShipRef.class);
	IDaoProduct proxyProduct = (IDaoProduct) bf.getBean(IDaoProduct.class);
	IDaoAdress proxyAdresse = (IDaoAdress) bf.getBean(IDaoAdress.class);
	
	//Méthodes 
	
	public void genererProduct()
	{
		log.info("========================================[ Init ]=====================================");
		
		//Product Ref 1 à 664
		for (int i = 1; i <= 664; i++)
		{			
			log.info("ID ProduitRef : " + i);
			
			ProductRef productRef = new ProductRef();
			productRef.setIdProductRef(i);
			
			//Constructeur 1 à 44
			for (int j = 1; j <= 44; j++)
			{
				Random randConstructor = new Random();
				int randomConstructor = randConstructor.nextInt(100);
				
				if (randomConstructor > 95)
				{
					Constructor constructeur = new Constructor();
					constructeur.setIdConstructor(j);
					
					//Matériaux 1 à 24
					for (int k = 1; k <= 24; k++)
					{
						Random randMaterial = new Random();
						int randomMaterial = randMaterial.nextInt(100);
						
						if (randomMaterial > 95)
						{
							Material materiau = new Material();
							materiau.setIdMaterial(k);
							
							//Couleur 1 à 15
							for (int l = 1; l <= 15; l++)
							{
								Random randColor = new Random();
								int randomColor = randColor.nextInt(100);
								
								if (randomColor > 95)
								{
									Color couleur = new Color();
									couleur.setIdColor(l);
									
									Product produit = new Product();
									produit.setProductRef(productRef);
									produit.setColor(couleur);
									produit.setConstructor(constructeur);
									produit.setMaterial(materiau);
									
									double prixMin = 20;
									double prixMax = 600;
									Random rPrice = new Random();
									double randomValue = prixMin + (prixMax - prixMin) * rPrice.nextDouble();
									produit.setPrice(randomValue);
									produit.setOnLine(true);
									
									Random rStock = new Random();
									int stock = rPrice.nextInt(500000 - 0 + 1) + 0;//rand.nextInt(max - min + 1) + min;
									produit.setStock(stock);

									proxyProduct.addProduct(produit);
									
									log.info("ID Produit : " + produit.getIdProduct());
								}
							}
						}
					}
				}
			}
		}
		log.info("========================================[ Success ]=====================================");	
	}
	
	public void genererMaterial()
	{
		List<String> listeMateriaux = new ArrayList<String>();
		listeMateriaux.add("Carbonite");
		listeMateriaux.add("Transparacier");
		listeMateriaux.add("Plastacier");
		listeMateriaux.add("Beskar");
		listeMateriaux.add("Duracier");
		listeMateriaux.add("Trillium");
		listeMateriaux.add("Halurium");
		listeMateriaux.add("Ectofer");
		listeMateriaux.add("Titane");
		listeMateriaux.add("Saronite");
		listeMateriaux.add("Cobalt");
		listeMateriaux.add("Khorium");
		listeMateriaux.add("Eternium");
		listeMateriaux.add("Argent");
		listeMateriaux.add("Mithril");
		listeMateriaux.add("Etain");
		listeMateriaux.add("Marilite");
		listeMateriaux.add("Or");
		listeMateriaux.add("Permabéton");
		listeMateriaux.add("Fer");
		listeMateriaux.add("Cuivre");
		listeMateriaux.add("Thorilide");
		listeMateriaux.add("Permaplex");
		listeMateriaux.add("Filmsiplast");
		
		for (String s : listeMateriaux)
		{
			Material materiau = new Material();
			materiau.setMaterialName(s);
			proxyMaterial.addMaterial(materiau);
		}
	}
	
	public void genererColor()
	{
		List<String> listeCouleurs = new ArrayList<String>();
		listeCouleurs.add("Bleu");
		listeCouleurs.add("Vert");
		listeCouleurs.add("Jaune");
		listeCouleurs.add("Gris");
		listeCouleurs.add("Rose");
		listeCouleurs.add("Noir");
		listeCouleurs.add("Marron");
		listeCouleurs.add("Violet");
		listeCouleurs.add("Blanc");
		listeCouleurs.add("Orange");
		listeCouleurs.add("Mauve");
//		listeCouleurs.add("Rouge");
		listeCouleurs.add("Or");
		listeCouleurs.add("Argent");
		listeCouleurs.add("Transparent");
		
		for (String s : listeCouleurs)
		{
			Color color = new Color();
			color.setColorName(s);
			proxyColor.addColor(color);
		}
	}
	
	public void genererSpaceShipProduct()
	{
		log.info("========================================[ Init ]=====================================");
		
		List<ProductRef> listeProduit = proxyProductRef.getAllProductRef();
		List<SpaceshipRef> listeVaisseau = proxySpaceShipRef.getAllSpaceshipRef();
		
		for (SpaceshipRef vaisseau : listeVaisseau)
		{
			for (ProductRef produit : listeProduit)
			{
				Random rand = new Random();
				int random = rand.nextInt(100);
				
				if (random > 98)
				{
					SpaceshipProduct vaisseauProduit = new SpaceshipProduct();
					vaisseauProduit.setProductRef(produit);
					vaisseauProduit.setSpaceshipRef(vaisseau);
					proxyProductRef.addSpaceShipProduct(vaisseauProduit);
				}
			}
		}
		log.info("========================================[ Success ]=====================================");
	}
	
	public void correctionCategory()
	{
		Category categorie = new Category();
		categorie.setIdCategory(1);
		List<ProductRef> listeProduit = proxyProductRef.getAllProductRef();
		for (ProductRef pr : listeProduit)
		{
			pr.setCategory(categorie);
			proxyProductRef.updateProductRef(pr);
		}
	}

	public void ajoutCategorie()
	{
		Category piece = new Category();
		piece.setCategoryName("Pièces détachées");

		Category accessoires = new Category();
		accessoires.setCategoryName("Accessoires");

		Category fournitures = new Category();
		fournitures.setCategoryName("Fournitures");

		Category armes = new Category();
		armes.setCategoryName("Armes et technologies");

		proxyCategory.addCategory(armes);
		proxyCategory.addCategory(piece);
		proxyCategory.addCategory(fournitures);
		proxyCategory.addCategory(accessoires);

	}
	
	//Getters et Setters
	
	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public IDaoColor getProxyColor() {
		return proxyColor;
	}

	public void setProxyColor(IDaoColor proxyColor) {
		this.proxyColor = proxyColor;
	}

	public IDaoCategory getProxyCategory() {
		return proxyCategory;
	}

	public void setProxyCategory(IDaoCategory proxyCategory) {
		this.proxyCategory = proxyCategory;
	}

	public IDaoConstructor getProxyConstructor() {
		return proxyConstructor;
	}

	public void setProxyConstructor(IDaoConstructor proxyConstructor) {
		this.proxyConstructor = proxyConstructor;
	}

	public IDaoMaterial getProxyMaterial() {
		return proxyMaterial;
	}

	public void setProxyMaterial(IDaoMaterial proxyMaterial) {
		this.proxyMaterial = proxyMaterial;
	}

	public IDaoProductRef getProxyProductRef() {
		return proxyProductRef;
	}

	public void setProxyProductRef(IDaoProductRef proxyProductRef) {
		this.proxyProductRef = proxyProductRef;
	}

	public IDaoSpaceShipRef getProxySpaceShipRef() {
		return proxySpaceShipRef;
	}

	public void setProxySpaceShipRef(IDaoSpaceShipRef proxySpaceShipRef) {
		this.proxySpaceShipRef = proxySpaceShipRef;
	}

	public IDaoProduct getProxyProduct() {
		return proxyProduct;
	}

	public void setProxyProduct(IDaoProduct proxyProduct) {
		this.proxyProduct = proxyProduct;
	}
}
