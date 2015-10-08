package fr.afcepf.al24.web.administrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.mongodb.CategoryProduct;
import manufacture.entity.mongodb.TypeProductProduct;
import manufacture.ifacade.mongodb.IMongoDB;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name="mbAdmin")
@SessionScoped
public class AdministratorBean {

	private static Logger log = Logger.getLogger(AdministratorBean.class);

	private String rubriqueChoisie;

	@ManagedProperty(value="#{mongoDB}")
	private IMongoDB proxyMongo;

	private List<CategoryProduct> listeProduitsVendusSurLeMoisParCategorie = new ArrayList<>();
	private List<CategoryProduct> listeProduitsVendusUnMoisAvantParCategorie = new ArrayList<>();
	private List<CategoryProduct> listeProduitsVendusDeuxMoisAvantParCategorie = new ArrayList<>();
	private List<CategoryProduct> listeProduitsVendusTroisMoisAvantParCategorie = new ArrayList<>();

	private List<CategoryProduct> categorieArme = new ArrayList<>();
	private List<CategoryProduct> categorieAccessoire = new ArrayList<>();
	private List<CategoryProduct> categoriePieces = new ArrayList<>();
	private List<CategoryProduct> categorieFournitures = new ArrayList<>();

	private List<TypeProductProduct> listeProduitsVendusSurLeMoisParType = new ArrayList<>();

	private PieChartModel pieModelCategory;
	private PieChartModel pieModelTypeProduct;
	private BarChartModel barModel;

	@PostConstruct
	public void init() {
		listeProduitsVendusSurLeMoisParCategorie = proxyMongo.productsSellByCategoryAndMonth();
		listeProduitsVendusUnMoisAvantParCategorie = proxyMongo.productsSellByCategoryAndOneMonthAgo();
		listeProduitsVendusDeuxMoisAvantParCategorie = proxyMongo.productsSellByCategoryAndTwoMonthAgo();
		listeProduitsVendusTroisMoisAvantParCategorie = proxyMongo.productsSellByCategoryAndThreeMonthAgo();
		createPieModel();
		createBarModel();
	}

	//Méthodes

	public void chooseRubrique(String rubrique)
	{
		rubriqueChoisie = rubrique;
	}

	public void createPieModel() {
		pieModelCategory = new PieChartModel();
		pieModelTypeProduct = new PieChartModel();

//		for (CategoryProduct cp : listeProduitsVendusSurLeMoisParCategorie)
//		{
//			pieModelCategory.set(cp.getCategory(), cp.getQuantity());
//		}

		for (int i = 0; i < 4; i++)
		{
			Random rand = new Random();
			int nombreAleatoire = rand.nextInt(200 - 10 + 1) + 10;
			Random rand2 = new Random();
			int nombreAleatoire2 = rand2.nextInt(200 - 10 + 1) + 10;
			pieModelCategory.set("Catégorie" + i, nombreAleatoire);
			pieModelTypeProduct.set("Type" + i, nombreAleatoire2);
			log.info("Pie model 1 : " + nombreAleatoire);
		}

		pieModelCategory.setTitle("Par catégorie de produit");
		pieModelCategory.setFill(true);
		pieModelCategory.setLegendPosition("w");
		pieModelCategory.setShowDataLabels(true);

//		for (TypeProductProduct tpp : listeProduitsVendusSurLeMoisParType)
//		{
//			pieModelTypeProduct.set(tpp.getTypeProduct(), tpp.getQuantity());
//		}
		pieModelTypeProduct.setTitle("Par type de fournisseur");
		pieModelTypeProduct.setFill(true);
		pieModelTypeProduct.setLegendPosition("w");
		pieModelTypeProduct.setShowDataLabels(true);
	}

	public void createBarModel() {
		barModel = new BarChartModel();

		//		Calendar cal = Calendar.getInstance();
		//		
		//		SimpleDateFormat sdf = new SimpleDateFormat("MMMMMMMMMMMMMMMM");
		//		
		//		cal.add(cal.MONTH, -1);
		//		String dateUne = sdf.format(cal.getTime());
		//		dateUne = dateUne.substring(0, 1).toUpperCase() + dateUne.substring(1);
		//		
		//		cal.add(cal.MONTH, -1);
		//		String dateDeux = sdf.format(cal.getTime());
		//		dateDeux = dateDeux.substring(0, 1).toUpperCase() + dateDeux.substring(1);
		//		
		//		cal.add(cal.MONTH, -1);
		//		String dateTrois = sdf.format(cal.getTime());
		//		dateTrois = dateTrois.substring(0, 1).toUpperCase() + dateTrois.substring(1);
		//		
		//		List<String> listeMois = new ArrayList<>();
		//		listeMois.add(dateUne);
		//		listeMois.add(dateDeux);
		//		listeMois.add(dateTrois);
		//		
		//		ChartSeries serieArme = new ChartSeries();
		//		serieArme.setLabel("Armes et technologies");
		//		ChartSeries serieAccessoire = new ChartSeries();
		//		serieAccessoire.setLabel("Accessoires");
		//		ChartSeries seriePiece = new ChartSeries();
		//		seriePiece.setLabel("Pièces détachées");
		//		ChartSeries serieFourniture = new ChartSeries();
		//		serieFourniture.setLabel("Fournitures");
		//
		//		repartitProduitsDansCategorie();
		//		for (int i = 0; i < 3; i++)
		//		{
		//			serieArme.set(listeMois.get(i), categorieArme.get(i).getQuantity());
		//			serieAccessoire.set(listeMois.get(i), categorieAccessoire.get(i).getQuantity());
		//			seriePiece.set(listeMois.get(i), categoriePieces.get(i).getQuantity());
		//			serieFourniture.set(listeMois.get(i), categorieFournitures.get(i).getQuantity());
		//		}
		//		
		//		barModel.addSeries(serieArme);
		//		barModel.addSeries(serieAccessoire);
		//		barModel.addSeries(seriePiece);
		//		barModel.addSeries(serieFourniture);

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Boys");
		boys.set("2004", 120);
		boys.set("2005", 100);
		boys.set("2006", 44);
		boys.set("2007", 150);
		boys.set("2008", 25);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Girls");
		girls.set("2004", 52);
		girls.set("2005", 60);
		girls.set("2006", 110);
		girls.set("2007", 135);
		girls.set("2008", 120);

		barModel.addSeries(boys);
		barModel.addSeries(girls);

		barModel.setTitle("");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Gender");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Births");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	public void repartitProduitsDansCategorie() 
	{
		for (CategoryProduct cp : listeProduitsVendusUnMoisAvantParCategorie)
		{
			ajouterProduitDansCategorie(cp);
		}

		for (CategoryProduct cp : listeProduitsVendusDeuxMoisAvantParCategorie)
		{
			ajouterProduitDansCategorie(cp);
		}

		for (CategoryProduct cp : listeProduitsVendusTroisMoisAvantParCategorie)
		{
			ajouterProduitDansCategorie(cp);
		}
	}

	public void ajouterProduitDansCategorie(CategoryProduct cp)
	{
		if (cp.getCategory().equals("Armes et technologies"))
		{
			categorieArme.add(cp);
		} else if (cp.getCategory().equals("Accessoires"))
		{
			categorieAccessoire.add(cp);
		} else if (cp.getCategory().equals("Fournitures"))
		{
			categorieFournitures.add(cp);
		} else
		{
			categoriePieces.add(cp);
		}
	}

	//Getters et Setters

	public String getRubriqueChoisie() {
		return rubriqueChoisie;
	}

	public void setRubriqueChoisie(String rubriqueChoisie) {
		this.rubriqueChoisie = rubriqueChoisie;
	}

	public static void setLog(Logger log) {
		AdministratorBean.log = log;
	}

	public IMongoDB getProxyMongo() {
		return proxyMongo;
	}

	public void setProxyMongo(IMongoDB proxyMongo) {
		this.proxyMongo = proxyMongo;
	}

	public List<CategoryProduct> getListeProduitsVendusSurLeMoisParCategorie() {
		return listeProduitsVendusSurLeMoisParCategorie;
	}

	public void setListeProduitsVendusSurLeMoisParCategorie(
			List<CategoryProduct> listeProduitsVendusSurLeMoisParCategorie) {
		this.listeProduitsVendusSurLeMoisParCategorie = listeProduitsVendusSurLeMoisParCategorie;
	}

	public List<CategoryProduct> getListeProduitsVendusUnMoisAvantParCategorie() {
		return listeProduitsVendusUnMoisAvantParCategorie;
	}

	public void setListeProduitsVendusUnMoisAvantParCategorie(
			List<CategoryProduct> listeProduitsVendusUnMoisAvantParCategorie) {
		this.listeProduitsVendusUnMoisAvantParCategorie = listeProduitsVendusUnMoisAvantParCategorie;
	}

	public List<CategoryProduct> getListeProduitsVendusDeuxMoisAvantParCategorie() {
		return listeProduitsVendusDeuxMoisAvantParCategorie;
	}

	public void setListeProduitsVendusDeuxMoisAvantParCategorie(
			List<CategoryProduct> listeProduitsVendusDeuxMoisAvantParCategorie) {
		this.listeProduitsVendusDeuxMoisAvantParCategorie = listeProduitsVendusDeuxMoisAvantParCategorie;
	}

	public List<CategoryProduct> getListeProduitsVendusTroisMoisAvantParCategorie() {
		return listeProduitsVendusTroisMoisAvantParCategorie;
	}

	public void setListeProduitsVendusTroisMoisAvantParCategorie(
			List<CategoryProduct> listeProduitsVendusTroisMoisAvantParCategorie) {
		this.listeProduitsVendusTroisMoisAvantParCategorie = listeProduitsVendusTroisMoisAvantParCategorie;
	}

	public List<CategoryProduct> getCategorieArme() {
		return categorieArme;
	}

	public void setCategorieArme(List<CategoryProduct> categorieArme) {
		this.categorieArme = categorieArme;
	}

	public List<CategoryProduct> getCategorieAccessoire() {
		return categorieAccessoire;
	}

	public void setCategorieAccessoire(List<CategoryProduct> categorieAccessoire) {
		this.categorieAccessoire = categorieAccessoire;
	}

	public List<CategoryProduct> getCategoriePieces() {
		return categoriePieces;
	}

	public void setCategoriePieces(List<CategoryProduct> categoriePieces) {
		this.categoriePieces = categoriePieces;
	}

	public List<CategoryProduct> getCategorieFournitures() {
		return categorieFournitures;
	}

	public void setCategorieFournitures(List<CategoryProduct> categorieFournitures) {
		this.categorieFournitures = categorieFournitures;
	}

	public List<TypeProductProduct> getListeProduitsVendusSurLeMoisParType() {
		return listeProduitsVendusSurLeMoisParType;
	}

	public void setListeProduitsVendusSurLeMoisParType(
			List<TypeProductProduct> listeProduitsVendusSurLeMoisParType) {
		this.listeProduitsVendusSurLeMoisParType = listeProduitsVendusSurLeMoisParType;
	}

	public PieChartModel getPieModelCategory() {
		return pieModelCategory;
	}

	public void setPieModelCategory(PieChartModel pieModelCategory) {
		this.pieModelCategory = pieModelCategory;
	}

	public PieChartModel getPieModelTypeProduct() {
		return pieModelTypeProduct;
	}

	public void setPieModelTypeProduct(PieChartModel pieModelTypeProduct) {
		this.pieModelTypeProduct = pieModelTypeProduct;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public static Logger getLog() {
		return log;
	}
}
