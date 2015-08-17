package manufacture.dao.remplissageBDD;

import manufacture.entity.product.Category;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.user.Planet;
import manufacture.idao.dataloading.IDaoPlanet;
import manufacture.idao.product.IDaoCategory;
import manufacture.idao.product.IDaoColor;
import manufacture.idao.product.IDaoConstructor;
import manufacture.idao.product.IDaoMaterial;
import manufacture.idao.product.IDaoProductRef;
import manufacture.idao.product.IDaoSpaceShipRef;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Routine {

	//Logger
	Logger log = Logger.getLogger(Routine.class);
	
	//Initialisation des bean
	BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
	IDaoColor proxyColor = (IDaoColor) bf.getBean(IDaoColor.class);
	IDaoCategory proxyCategory = (IDaoCategory) bf.getBean(IDaoCategory.class);
	IDaoConstructor proxyConstructor = (IDaoConstructor) bf.getBean(IDaoConstructor.class);
	IDaoMaterial proxyMaterial = (IDaoMaterial) bf.getBean(IDaoMaterial.class);
	IDaoProductRef proxyProductRef = (IDaoProductRef) bf.getBean(IDaoProductRef.class);
	IDaoSpaceShipRef proxySapceShipRef = (IDaoSpaceShipRef) bf.getBean(IDaoSpaceShipRef.class);
	IDaoPlanet proxyAdresse = (IDaoPlanet) bf.getBean(IDaoPlanet.class);
	
	
	//Site ASP (pièces détachées avions)
	private static final String URL_PIECE_SITE_ASP = "http://www.airshopparts.com/pieces-detachees-pour-avions-c9.html";
	private static final String URL_ACCESSOIRE_SITE_ASP = "http://www.airshopparts.com/accessoires-c10.html";
	private static final String URL_FOURNITURE_SITE_ASP = "http://www.airshopparts.com/fournitures-c11.html";

	//Site StarWars-Universe (armes)
	private static final String URL_ARMES_STARWARS_UNIVERSE = "http://www.starwars-universe.com/encyclopedie/armes_technologie.php";

	//Site Anakinworld (constructeurs, planètes, vaisseaux)
	private static final String URL_CONSTRUCTEURS_ANAKINWORLD = "http://www.anakinworld.com/encyclopedie/listes/constructeurs-aero-spatiaux";
	private static final String URL_VAISSEAUX_ANAKINWORLD = "http://www.anakinworld.com/encyclopedie/listes/vaisseaux?letter=";
	private static final String URL_PLANETES_ANAKINWORLD = "http://www.anakinworld.com/encyclopedie/listes/planetes-et-lunes?letter=";

	private int ind;
	private char alphabet;

	public Routine(){
		ind = 0;
		alphabet = 'A';
	}

	//Site Anakinworld (constructeurs, planètes, vaisseaux)
	public void recupPlanetes()
	{
		Document doc;
		log.info("========================================[ Init ]=====================================");


		try
		{
			for (char lettre = alphabet; lettre <= 'Z'; lettre++)
			{

				alphabet = lettre;

				doc = Jsoup.connect("http://www.anakinworld.com/encyclopedie/listes/planetes-et-lunes?letter="+alphabet).get();
				Elements lienDetails = doc.select("a.name");

				System.out.println(lienDetails.size());

				for (int i = ind ; i < lienDetails.size() ; i +=100)
				{
					ind = i;

					Element element = lienDetails.get(ind);
					String url = "http://www.anakinworld.com"+element.attr("href");

					Document pageDetail = Jsoup.connect(url).get();

					Planet planete = new Planet();

					String nom = "";//OK
					String regionGalactique = "";//OK
					String systemeStellaire = "";//OK
					String secteurGalactique = "";//OK

					try
					{
						nom = pageDetail.select("#general > article > header > h1").get(0).text();
					}catch(Exception e){

					}

					Elements labelInfos = pageDetail.select("span.attribute");
					Elements valeurInfos = pageDetail.select("span.value");

					for (int j = 0; j < labelInfos.size(); j++)
					{
						if (labelInfos.get(j).text().equals("Région Galactique"))
						{
							regionGalactique = valeurInfos.get(j).text();
						}

						if (labelInfos.get(j).text().equals("Système stellaire"))
						{
							systemeStellaire = valeurInfos.get(j).text();
						}

						if (labelInfos.get(j).text().equals("Secteur galactique"))
						{
							secteurGalactique = valeurInfos.get(j).text();
						}
					}

//					planete.setPlanete(nom);
//					planete.setRegionGalactique(regionGalactique);
//					planete.setSecteurGalactique(secteurGalactique);
//					planete.setSystemeStellaire(systemeStellaire);
					
					planete.setPlanetName(nom);

					proxyAdresse.addPlanet(planete);
					Thread.sleep(20);
				}
				ind = 0;
			}
			alphabet = 'A';

		} catch (Exception e) {
			log.info("========================================[ Failed ]=====================================");

			recupPlanetes();
		}
		log.info("========================================[ Success ]=====================================");
	}

	public void recupVaisseaux()
	{
		Document doc;
		log.info("========================================[ Init ]=====================================");

		try
		{
			for (char lettre = alphabet; lettre <= 'Z'; lettre++)
			{

				alphabet = lettre;

				doc = Jsoup.connect(URL_VAISSEAUX_ANAKINWORLD+alphabet).get();
				Elements lienDetails = doc.select("a.name");

				for (int i = ind ; i < lienDetails.size() ; i++)
				{
					ind = i;

					Element element = lienDetails.get(ind);
					String url = "http://www.anakinworld.com"+element.attr("href");

					Document pageDetail = Jsoup.connect(url).get();

//					Vaisseau vaisseau = new Vaisseau();
					SpaceshipRef vaisseau = new SpaceshipRef();

					String nom = "";//OK
					String type = "";//OK
					String taille = "";//OK
//					Set<Constructeur> listeConstructeurs = new HashSet<>();//OK
					String armement = "";//OK
					String capacite = "";//OK
					String equipage = "";//OK
					String blindage = "";//OK

					String nbBoucliersString = "";//OK
					int nbBoucliers = 0;//OK

					String vitesseSpatiale = "";//OK
					String vitesseAtmospherique = "";//OK
					String senseurs = "";//OK
					String autonomie = "";//OK
					String maniabilite = "";//OK

					String prixString = "";//OK

					String urlPhoto = "";//OK
					String description = "";//OK
					String modeleOuClasse = "";//OK
					String disponibilite = "";//OK

					try
					{
						nom = pageDetail.select("#general > article > header > h1").get(0).text();
					}catch(Exception e){

					}
/*
					try
					{
						description = pageDetail.select("#general > article > header > p").get(0).text();
					}catch(Exception e){

					}

					try
					{
						urlPhoto = pageDetail.select("#general > article > section > aside > div:nth-child(2) > section.widget.cover.glue > a > img").get(0).attr("src");
					}catch(Exception e){

					}*/

					Elements labelInfos = pageDetail.select("span.attribute");

					for (int j = 0; j < labelInfos.size(); j++)
					{/*
						if (labelInfos.get(j).text().equals("Type de vaisseau"))
						{
							if (pageDetail.select("li.raw-60 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-60 > ul > li");

								for (Element el : constructeurs)
								{
									type += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-60 span.value").size() != 0)
								{
									type = pageDetail.select("li.raw-60 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Constructeur/Producteur"))
						{							
							if (pageDetail.select("li.raw-24 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-24 > ul > li");
								
								for (Element el : constructeurs)
								{
									
//									Constructeur constructeur = new Constructeur(null,el.text());
//									System.out.println(el.text());
//									per.save(constructeur);								
//									listeConstructeurs.add(constructeur);
								}
							}
							else
							{
								if (pageDetail.select("li.raw-24 span.value").size() != 0)
								{
//									Constructeur constructeur = new Constructeur(null,pageDetail.select("li.raw-24 span.value").get(0).text());
//									per.save(constructeur);							
//									listeConstructeurs.add(constructeur);
								}
							}
						}
/*
						if (labelInfos.get(j).text().equals("Taille"))
						{
							if (pageDetail.select("li.raw-14 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-14 > ul > li");

								for (Element el : constructeurs)
								{
									taille += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-14 span.value").size() != 0)
								{
									taille = pageDetail.select("li.raw-14 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Armement"))
						{
							if (pageDetail.select("li.raw-26 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-26 > ul > li");

								for (Element el : constructeurs)
								{
									armement += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-26 span.value").size() != 0)
								{
									armement = pageDetail.select("li.raw-26 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Capacité"))
						{
							if (pageDetail.select("li.raw-54 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-54 > ul > li");

								for (Element el : constructeurs)
								{
									capacite += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-54 span.value").size() != 0)
								{
									capacite = pageDetail.select("li.raw-54 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Equipage"))
						{
							if (pageDetail.select("li.raw-56 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-56 > ul > li");

								for (Element el : constructeurs)
								{
									equipage += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-56 span.value").size() != 0)
								{
									equipage = pageDetail.select("li.raw-56 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Blindage"))
						{
							if (pageDetail.select("li.raw-58 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-58 > ul > li");

								for (Element el : constructeurs)
								{
									blindage += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-58 span.value").size() != 0)
								{
									blindage = pageDetail.select("li.raw-58 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Boucliers"))
						{
							nbBoucliersString = pageDetail.select("li.raw-59 span.value").get(0).text();
							try {
								nbBoucliers = Integer.parseInt(nbBoucliersString);
							} catch (Exception e ){

							}
						}

						if (labelInfos.get(j).text().equals("Vitesse spatiale"))
						{
							if (pageDetail.select("li.raw-62 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-62 > ul > li");

								for (Element el : constructeurs)
								{
									vitesseSpatiale += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-62 span.value").size() != 0)
								{
									vitesseSpatiale = pageDetail.select("li.raw-62 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Vitesse atmosphérique"))
						{
							if (pageDetail.select("li.raw-63 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-63 > ul > li");

								for (Element el : constructeurs)
								{
									vitesseAtmospherique += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-63 span.value").size() != 0)
								{
									vitesseAtmospherique = pageDetail.select("li.raw-63 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Senseurs"))
						{
							if (pageDetail.select("li.raw-66 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-66 > ul > li");

								for (Element el : constructeurs)
								{
									senseurs += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-66 span.value").size() != 0)
								{
									senseurs = pageDetail.select("li.raw-66 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Autonomie"))
						{
							if (pageDetail.select("li.raw-67 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-67 > ul > li");

								for (Element el : constructeurs)
								{
									autonomie += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-67 span.value").size() != 0)
								{
									autonomie = pageDetail.select("li.raw-67 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Maniabilité"))
						{
							if (pageDetail.select("li.raw-68 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-68 > ul > li");

								for (Element el : constructeurs)
								{
									maniabilite += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-68 span.value").size() != 0)
								{
									maniabilite = pageDetail.select("li.raw-68 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Prix"))
						{
							if (pageDetail.select("li.raw-6 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-6 > ul > li");

								for (Element el : constructeurs)
								{
									prixString += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-6 span.value").size() != 0)
								{
									prixString = pageDetail.select("li.raw-6 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Modèle/Classe"))
						{
							if (pageDetail.select("li.raw-23 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-23 > ul > li");

								for (Element el : constructeurs)
								{
									capacite += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-23 span.value").size() != 0)
								{
									capacite = pageDetail.select("li.raw-23 span.value").get(0).text();
								}
							}
						}

						if (labelInfos.get(j).text().equals("Disponibilité"))
						{
							if (pageDetail.select("li.raw-7 ul.value li").size() != 0)
							{
								Elements constructeurs = pageDetail.select("li.raw-7 > ul > li");

								for (Element el : constructeurs)
								{
									disponibilite += el.text() + ", ";
								}
							}
							else
							{
								if (pageDetail.select("li.raw-7 span.value").size() != 0)
								{
									disponibilite = pageDetail.select("li.raw-7 span.value").get(0).text();
								}
							}
						}*/
					}

					vaisseau.setSpaceshipName(nom);
//					vaisseau.setType(type);
//					vaisseau.setTaille(taille);
//					vaisseau.setListeConstructeurs(listeConstructeurs);
//					vaisseau.setArmement(armement);
//					vaisseau.setCapacite(capacite);
//					vaisseau.setEquipage(equipage);
//					vaisseau.setBlindage(blindage);
//					vaisseau.setNbBoucliers(nbBoucliers);
//					vaisseau.setVitesseSpatiale(vitesseSpatiale);
//					vaisseau.setVitesseAtmospherique(vitesseAtmospherique);
//					vaisseau.setSenseurs(senseurs);
//					vaisseau.setAutonomie(autonomie);
//					vaisseau.setManiabilite(maniabilite);
//					vaisseau.setPrixCredits(prixString);
//					vaisseau.setUrlPhoto(urlPhoto);
//					vaisseau.setDescription(description);
//					vaisseau.setModeleOuClasse(modeleOuClasse);
//					vaisseau.setDisponibilite(disponibilite);
//
//					System.out.println(nom);
//					per.save(vaisseau);
					proxySapceShipRef.addSpaceShipRef(vaisseau);

					Thread.sleep(30);
				}
				ind = 0;
			}
			alphabet = 'A';

		} catch (Exception e) {
			log.info("========================================[ Failed ]=====================================");

			e.printStackTrace();
			recupVaisseaux();
		}
		log.info("========================================[ Success ]=====================================");

	}

	public void recupConstructeurs()
	{
		Document doc;
		log.info("========================================[ Init ]=====================================");


		try
		{
			doc = Jsoup.connect(URL_CONSTRUCTEURS_ANAKINWORLD).get();
			Elements noms = doc.select("a.name");

			for (int i = ind;i<noms.size();i++)
			{
				ind = i;
				Constructor constructeur = new Constructor();
//				Constructeur constructeur = new Constructeur();

				String nom = noms.get(ind).text();
				constructeur.setConstructorName(nom);
				proxyConstructor.addConstructor(constructeur);
//				constructeur.setNom(nom);

//				per.save(constructeur);

				Thread.sleep(20);
			}

		} catch (Exception e) {
			log.info("========================================[ Failed ]=====================================");
			e.printStackTrace();
			recupConstructeurs();
		}		
		log.info("========================================[ Success ]=====================================");
	}

	//Site StarWars-Universe (armes)
	public void recupDonnesArmes()
	{
		Document doc;
		log.info("========================================[ Init ]=====================================");

		try
		{
			doc = Jsoup.connect(URL_ARMES_STARWARS_UNIVERSE).get();
			Elements lienDetails = doc.select("div.nom a");

			for (int i = ind;i<lienDetails.size();i++)
			{
				ind = i;
				
				
				Element element = lienDetails.get(ind);
				
				String url = "http://www.starwars-universe.com"+element.attr("href");

				Document pageDetail = Jsoup.connect(url).get();

//				EntiteASP entite = new EntiteASP();
				ProductRef produit = new ProductRef();
				Category categorie = new Category();
				categorie.setIdCategory(4);
				produit.setCategory(categorie);
				
				String titre = "";//OK
				String sousEnsemble = "";//OK
				String reference = "";//OK
				String description = "";//OK
				String dimension = "";//OK

				String poidstring = "";
				double poids = 0;

				String specification = "";//OK				
				String urlPhoto = "";//OK

				String prixstring = "";
				float prix = 0;

				try
				{
					description = pageDetail.select("div.paragraphe").get(0).text();
				}catch(Exception e){

				}

				try
				{
					titre = pageDetail.select("div.titre").get(0).text();
				}catch(Exception e){

				}

				try
				{
					urlPhoto = "http://www.starwars-universe.com" + pageDetail.select(".picto img").get(0).attr("src");
					produit.setUrlImage(urlPhoto);
				}catch(Exception e){

				}

				Elements labelInfos = pageDetail.select("span.info");
				Elements valeurInfos = pageDetail.select("span.valeur");

				for (int j = 0; j < labelInfos.size(); j++)
				{
					if (labelInfos.get(j).text().equals("Catégorie"))
					{
						sousEnsemble = valeurInfos.get(j).text();
					}
					if (labelInfos.get(j).text().equals("Type"))
					{
						specification = valeurInfos.get(j).text();
					}
					if (labelInfos.get(j).text().equals("Taille"))
					{
						dimension = valeurInfos.get(j).text();
					}
					if (labelInfos.get(j).text().equals("Nom original"))
					{
						reference = valeurInfos.get(j).text();
					}
				}

//				entite.setDescription(description);
//				entite.setReference(reference);
//				entite.setDimension(dimension);
//				entite.setPoids(poids);
//				entite.setSpecification(specification);
//				entite.setNom(titre);
//				entite.setSousEnsemble(sousEnsemble);
//				entite.setCategorie("Armes et technologies");
//				entite.setPrix(prix);
				
//				per.save(entite);
				
				produit.setProductName(titre);
				produit.setDescription(description);
				
				proxyProductRef.addProductRef(produit);

				Thread.sleep(30);
			}
			ind = 0;
		} catch (Exception e) {
			log.info("========================================[ Failed ]=====================================");
			e.printStackTrace();
			recupDonnesArmes();
		}	
		log.info("========================================[ Success ]=====================================");
	}

	//Site ASP (pièces détachées avions)
	public void recupDonnnesSiteASP()
	{
		Document doc;
		log.info("========================================[ Init ]=====================================");

		try {
			doc = Jsoup.connect(URL_FOURNITURE_SITE_ASP).get();
			Elements lienDetails = doc.select("#liste-produits .details a");

			for (int i = ind;i<lienDetails.size();i++)
			{
				ind = i;

				Element el = lienDetails.get(ind);

				String url = "http://www.airshopparts.com/"+el.attr("href");

				Document pageDetail = Jsoup.connect(url).get();
				
				ProductRef produit = new ProductRef();
				Category categorie = new Category();
				categorie.setIdCategory(3);
				produit.setCategory(categorie);
				
//				EntiteASP entite = new EntiteASP();
//				System.out.println(url);
				Elements ficheBottom = pageDetail.select(".fiche-produit-bottom p");

				String titre = "";
				String sousEnsemble = "";
				String reference = "";
				String description = "";
				String dimension = "";
				String poidstring = "";
				String specification = "";
				double poids = 0;

				description = ficheBottom.get(1).text();
				try{

					if(ficheBottom.size() >= 3){
						specification = ficheBottom.get(3).text();
					}
				}catch(Exception e){ 

				}
				Elements details = pageDetail.select("html body div#container div#body div#middle div#fiche-produit div.fiche-produit-right div.info span");

				try{
					titre = pageDetail.select("html body div#container div#body div#middle div#fiche-produit div.fiche-produit-left h2").get(0).text();
				}catch(Exception e){
					e.printStackTrace();
				}
				try{
					sousEnsemble = details.get(1).text();
				}catch(Exception e){
					e.printStackTrace();
				}
				try{

					reference = details.get(2).text();
					if(reference.contains(" mm")){
						dimension = reference;
						reference = "";
					}
				}catch(Exception e){
				}
				try{

					dimension = details.get(3).text();
					if(dimension.contains(" kg")){
						dimension = "";
					}
				}catch(Exception e){
				}
				try{
					poidstring = details.get(4).text(); 
					if(poidstring.contains(" kg")){
						poids = Double.parseDouble(poidstring.replace(" kg", ""));
					}else if(reference.contains(" kg")){
						poids = Double.parseDouble(reference.replace(" kg", ""));
					}else if(dimension.contains(" kg")){
						poids = Double.parseDouble(dimension.replace(" kg", ""));
					}

				}catch(Exception e){

				}

//				entite.setDescription(description);
//				entite.setReference(reference);
//				entite.setDimension(dimension);
//				entite.setPoids(poids);
//				entite.setSpecification(specification);
//				entite.setNom(titre);
//				entite.setSousEnsemble(sousEnsemble);

				try{

					String urlPhoto = "http://www.airshopparts.com/" + pageDetail.select("html body div#container div#body div#middle div#fiche-produit div.fiche-produit-left div#fiche-produit-photos a#img_0.display-block img").get(0).attr("src");
					produit.setUrlImage(urlPhoto);
//					entite.setUrlPhoto(urlPhoto);
				}catch(Exception e){
				}

				try{ 

					String prixstring = pageDetail.select("html body div#container div#body div#middle div#fiche-produit div.fiche-produit-right div#fiche-produit-prix div.prix p.prix.prix-ttc span#fiche-produit-prix-ttc-javascript").get(0).text();
					float prix = Float.parseFloat(prixstring.replace(",", "."));
//					entite.setPrix(prix);
				}catch(Exception e){
				}
//				entite.setCategorie("Pièces détachées");
//
//				per.save(entite);
				produit.setProductName(titre);
				produit.setDescription(description);
				
				proxyProductRef.addProductRef(produit);

				Thread.sleep(30);
			}	
			
			ind = 0;
			
			System.out.println(lienDetails.size());
		} catch (Exception e) {
			log.info("========================================[ Failed ]=====================================");
			e.printStackTrace();
			recupDonnnesSiteASP();
		} 
		log.info("========================================[ Success ]=====================================");

	}
	
	//Getters et Setters

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public BeanFactory getBf() {
		return bf;
	}

	public void setBf(BeanFactory bf) {
		this.bf = bf;
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

	public IDaoSpaceShipRef getProxySapceShipRef() {
		return proxySapceShipRef;
	}

	public void setProxySapceShipRef(IDaoSpaceShipRef proxySapceShipRef) {
		this.proxySapceShipRef = proxySapceShipRef;
	}

	public int getInd() {
		return ind;
	}

	public void setInd(int ind) {
		this.ind = ind;
	}

	public char getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(char alphabet) {
		this.alphabet = alphabet;
	}

	public static String getUrlPieceSiteAsp() {
		return URL_PIECE_SITE_ASP;
	}

	public static String getUrlAccessoireSiteAsp() {
		return URL_ACCESSOIRE_SITE_ASP;
	}

	public static String getUrlFournitureSiteAsp() {
		return URL_FOURNITURE_SITE_ASP;
	}

	public static String getUrlArmesStarwarsUniverse() {
		return URL_ARMES_STARWARS_UNIVERSE;
	}

	public static String getUrlConstructeursAnakinworld() {
		return URL_CONSTRUCTEURS_ANAKINWORLD;
	}

	public static String getUrlVaisseauxAnakinworld() {
		return URL_VAISSEAUX_ANAKINWORLD;
	}

	public static String getUrlPlanetesAnakinworld() {
		return URL_PLANETES_ANAKINWORLD;
	}
}
