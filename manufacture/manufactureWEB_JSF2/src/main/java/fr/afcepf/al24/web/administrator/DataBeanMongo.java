package fr.afcepf.al24.web.administrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import manufacture.entity.mongodb.CategoryProduct;
import manufacture.entity.mongodb.TypeProductProduct;
import manufacture.ifacade.mongodb.IMongoDB;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name="mbMongo")
@SessionScoped
public class DataBeanMongo {

private static Logger log = Logger.getLogger(DataBeanMongo.class);
	
	@ManagedProperty(value="#{mongoDB}")
	private IMongoDB proxyMongo;
	
	private List<CategoryProduct> listeProduitsVendusSurLeMoisParCategorie = new ArrayList<>();
	private List<CategoryProduct> listeProduitsVendusSurLeJour = new ArrayList<>();
	private List<TypeProductProduct> listeProduitsVendusSurLeMoisParType = new ArrayList<>();

	private PieChartModel pieModelCategory;
	private PieChartModel pieModelTypeProduct;
	private BarChartModel barModel;

	private SelectItemGroup articlesVendus;

	private final int MAX_WIDTH_CIRCLE = 100;
	private final int MIN_WIDTH_CIRCLE = 20;
	private int sommeArticlesVendus;

	@PostConstruct
	public void init()
	{
		log.info("Init()");
		listeProduitsVendusSurLeMoisParCategorie = proxyMongo.productsSellByCategoryAndMonth();
//		createPieModel(listeProduitsVendusSurLeMoisParCategorie, listeProduitsVendusSurLeMoisParType);
		createPieModel();
		createBarModel();

		sommeArticlesVendus = 0;

//		listeProduitsVendusSurLeJour = proxyMongo.productsSellByCategoryAndDay();
		listeProduitsVendusSurLeJour = new ArrayList<>();
		articlesVendus = new SelectItemGroup();
		SelectItem[] tableauArticleMisEnLigne = new SelectItem[4];
		for (int i = 0; i < 4; i++)
		{
			Random rand = new Random();
			int nombreAleatoire = rand.nextInt(200 - 10 + 1) + 10;
			tableauArticleMisEnLigne[i] = new SelectItem(nombreAleatoire, "test", "descrition" + i);
			sommeArticlesVendus += nombreAleatoire;
			log.info("Liste articlesVendus : " + nombreAleatoire);
		}
		
		for (int i = 0; i < listeProduitsVendusSurLeJour.size(); i++)
		{
			CategoryProduct cp = listeProduitsVendusSurLeJour.get(i);
			tableauArticleMisEnLigne[i] = new SelectItem(cp.getQuantity(), cp.getCategory());
			sommeArticlesVendus += cp.getQuantity();
			
		}
		articlesVendus.setSelectItems(tableauArticleMisEnLigne);
	}

	//Méthodes
	//small (20px), smaller (35px), medium (50px), mediumLarge (75px), large (100px)
	public void updateData()
	{
		init();
	}

	public String getClassForCircle(int width)
	{
		if (width == MIN_WIDTH_CIRCLE)
		{
			return "small";
		} else if (width > MIN_WIDTH_CIRCLE && width <= ((MAX_WIDTH_CIRCLE + MIN_WIDTH_CIRCLE)/4))
		{
			return "smaller";
		} else if (width > ((MAX_WIDTH_CIRCLE + MIN_WIDTH_CIRCLE)/4) && width <= (MAX_WIDTH_CIRCLE/2))
		{
			return "medium";
		} else if (width > (MAX_WIDTH_CIRCLE/2) && width <= (MAX_WIDTH_CIRCLE*3/4))
		{
			return "mediumLarge";
		} else if (width > (MAX_WIDTH_CIRCLE*3/4) && width <= MAX_WIDTH_CIRCLE)
		{
			return "large";
		}
		return "";
	}

	public int articleVendusCalculateWidthCircle(int nombreArticles)
	{
		int width = nombreArticles * MAX_WIDTH_CIRCLE / sommeArticlesVendus;
		return (width < MIN_WIDTH_CIRCLE) ? MIN_WIDTH_CIRCLE : width;
	}

//	public void createPieModel(List<CategoryProduct> listeCategorie, List<TypeProductProduct> listeType) {
	public void createPieModel() {
		pieModelCategory = new PieChartModel();
		pieModelTypeProduct = new PieChartModel();

//		for (CategoryProduct cp : listeCategorie)
//		{
//			pieModelCategory.set(cp.getCategory(), cp.getQuantity());
//		}
//		
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

//		for (TypeProductProduct tpp : listeType)
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

		barModel.setTitle("Bar Chart");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Gender");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Births");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}
	
	//Getters et Setters

	public int getSommeArticlesVendus() {
		return sommeArticlesVendus;
	}

	public void setSommeArticlesVendus(int sommeArticlesVendus) {
		this.sommeArticlesVendus = sommeArticlesVendus;
	}

	public int getMAX_WIDTH_CIRCLE() {
		return MAX_WIDTH_CIRCLE;
	}

	public int getMIN_WIDTH_CIRCLE() {
		return MIN_WIDTH_CIRCLE;
	}

	public SelectItemGroup getArticlesVendus() {
		return articlesVendus;
	}

	public void setArticlesVendus(SelectItemGroup articlesVendus) {
		this.articlesVendus = articlesVendus;
	}

	public IMongoDB getProxyMongo() {
		return proxyMongo;
	}

	public void setProxyMongo(IMongoDB proxyMongo) {
		this.proxyMongo = proxyMongo;
	}

	public List<CategoryProduct> getListeProduitsVendusSurLeJour() {
		return listeProduitsVendusSurLeJour;
	}

	public void setListeProduitsVendusSurLeJour(
			List<CategoryProduct> listeProduitsVendusSurLeJour) {
		this.listeProduitsVendusSurLeJour = listeProduitsVendusSurLeJour;
	}

	public PieChartModel getPieModelCategory() {
		return pieModelCategory;
	}

	public void setPieModelCategory(PieChartModel pieModelCategory) {
		this.pieModelCategory = pieModelCategory;
	}

	public PieChartModel getPieModelTypeProduct() {
		log.info("getter de pieModelTypeProduct" + pieModelTypeProduct);
		return pieModelTypeProduct;
	}

	public void setPieModelTypeProduct(PieChartModel pieModelTypeProduct) {
		this.pieModelTypeProduct = pieModelTypeProduct;
	}

	public List<CategoryProduct> getListeProduitsVendusSurLeMoisParCategorie() {
		return listeProduitsVendusSurLeMoisParCategorie;
	}

	public void setListeProduitsVendusSurLeMoisParCategorie(
			List<CategoryProduct> listeProduitsVendusSurLeMoisParCategorie) {
		this.listeProduitsVendusSurLeMoisParCategorie = listeProduitsVendusSurLeMoisParCategorie;
	}

	public List<TypeProductProduct> getListeProduitsVendusSurLeMoisParType() {
		return listeProduitsVendusSurLeMoisParType;
	}

	public void setListeProduitsVendusSurLeMoisParType(
			List<TypeProductProduct> listeProduitsVendusSurLeMoisParType) {
		this.listeProduitsVendusSurLeMoisParType = listeProduitsVendusSurLeMoisParType;
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

	public static void setLog(Logger log) {
		DataBeanMongo.log = log;
	}
}
