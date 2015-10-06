package fr.afcepf.al24.web.administrator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import manufacture.entity.mongodb.CategoryProduct;
import manufacture.ifacade.mongodb.IMongoDB;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name="mbAdmin")
public class AdministratorBean {

	@ManagedProperty(value="#{mongoDB}")
    private IMongoDB proxyMongo;
	
	private List<CategoryProduct> listeProduitsVendusSurLeMois = new ArrayList<>();
	private List<CategoryProduct> listeProduitsVendusSurLeJour = new ArrayList<>();
	private List<CategoryProduct> listeProduitsMisEnVenteSurLeJour = new ArrayList<>();
	
	private String rubriqueChoisie;
	
	private PieChartModel pieModel;
	
	private SelectItemGroup articlesMisEnLigne;
	private SelectItemGroup articlesVendus;
	
	private final int MAX_WIDTH_CIRCLE = 200;
	private final int MIN_WIDTH_CIRCLE = 20;
	private int sommeArticlesVendus;
	private int sommeArticlesMisEnLigne;
	
	@PostConstruct
	void init()
	{
		listeProduitsVendusSurLeMois = proxyMongo.productsSellByCategoryAndMonth();
		createPieModel(listeProduitsVendusSurLeMois);
		
		sommeArticlesVendus = 0;
		sommeArticlesMisEnLigne = 0;
		
		listeProduitsVendusSurLeJour = proxyMongo.productsSellByCategoryAndDay();
		articlesVendus = new SelectItemGroup();
		SelectItem[] tableauArticleMisEnLigne = new SelectItem[4];
		for (int i = 0; i < listeProduitsVendusSurLeJour.size(); i++)
		{
			CategoryProduct cp = listeProduitsVendusSurLeJour.get(i);
			tableauArticleMisEnLigne[i] = new SelectItem(cp.getQuantity(), cp.getCategory());
			sommeArticlesVendus += cp.getQuantity();
		}
		articlesMisEnLigne.setSelectItems(tableauArticleMisEnLigne);
		
		listeProduitsMisEnVenteSurLeJour = proxyMongo.productsPublishedByCategoryAndDay();
		articlesMisEnLigne = new SelectItemGroup();
		SelectItem[] tableauArticleVendu = new SelectItem[4];
		for (int i = 0; i < listeProduitsMisEnVenteSurLeJour.size(); i++)
		{
			CategoryProduct cp = listeProduitsMisEnVenteSurLeJour.get(i);
			tableauArticleVendu[i] = new SelectItem(cp.getQuantity(), cp.getCategory());
			sommeArticlesMisEnLigne += cp.getQuantity();
		}
		articlesMisEnLigne.setSelectItems(tableauArticleVendu);
	}

	//Méthodes
	//small (20px), smaller (35px), medium (50px), mediumLarge (75px), large (100px)
	public void chooseRubrique(String rubrique)
	{
		rubriqueChoisie = rubrique;
	}
	
	public String getClassForCircle(int width)
	{
		if (width == MIN_WIDTH_CIRCLE)
		{
			return "small";
		} else if (width > MIN_WIDTH_CIRCLE && width <= (MAX_WIDTH_CIRCLE + MIN_WIDTH_CIRCLE/4))
		{
			return "smaller";
		} else if (width > (MAX_WIDTH_CIRCLE + MIN_WIDTH_CIRCLE/4) && width <= (MAX_WIDTH_CIRCLE/2))
		{
			return "medium";
		} else if (width > (MAX_WIDTH_CIRCLE/2) && width <= (MAX_WIDTH_CIRCLE*3/4))
		{
			return "mediumLarge";
		} else if (width > (MAX_WIDTH_CIRCLE*3/4) && width <= 100)
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
	
	public int articleMisEnLigneCalculateWidthCircle(int nombreArticles)
	{
		int width = nombreArticles * MAX_WIDTH_CIRCLE / sommeArticlesMisEnLigne;
		return (width < MIN_WIDTH_CIRCLE) ? MIN_WIDTH_CIRCLE : width;
	}
	
	private void createPieModel(List<CategoryProduct> liste) {
		pieModel = new PieChartModel();
		
		for (CategoryProduct cp : liste)
		{
			pieModel.set(cp.getCategory(), cp.getQuantity());
		}
		pieModel.setTitle("");
		pieModel.setFill(true);
		pieModel.setLegendPosition("w");
	}
	
	//Getters et Setters
	
	public String getRubriqueChoisie() {
		return rubriqueChoisie;
	}

	public void setRubriqueChoisie(String rubriqueChoisie) {
		this.rubriqueChoisie = rubriqueChoisie;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public int getSommeArticlesVendus() {
		return sommeArticlesVendus;
	}

	public void setSommeArticlesVendus(int sommeArticlesVendus) {
		this.sommeArticlesVendus = sommeArticlesVendus;
	}

	public int getSommeArticlesMisEnLigne() {
		return sommeArticlesMisEnLigne;
	}

	public void setSommeArticlesMisEnLigne(int sommeArticlesMisEnLigne) {
		this.sommeArticlesMisEnLigne = sommeArticlesMisEnLigne;
	}

	public int getMAX_WIDTH_CIRCLE() {
		return MAX_WIDTH_CIRCLE;
	}

	public int getMIN_WIDTH_CIRCLE() {
		return MIN_WIDTH_CIRCLE;
	}

	public SelectItemGroup getArticlesMisEnLigne() {
		return articlesMisEnLigne;
	}

	public void setArticlesMisEnLigne(SelectItemGroup articlesMisEnLigne) {
		this.articlesMisEnLigne = articlesMisEnLigne;
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

	public List<CategoryProduct> getListeProduitsVendusSurLeMois() {
		return listeProduitsVendusSurLeMois;
	}

	public void setListeProduitsVendusSurLeMois(
			List<CategoryProduct> listeProduitsVendusSurLeMois) {
		this.listeProduitsVendusSurLeMois = listeProduitsVendusSurLeMois;
	}

	public List<CategoryProduct> getListeProduitsVendusSurLeJour() {
		return listeProduitsVendusSurLeJour;
	}

	public void setListeProduitsVendusSurLeJour(
			List<CategoryProduct> listeProduitsVendusSurLeJour) {
		this.listeProduitsVendusSurLeJour = listeProduitsVendusSurLeJour;
	}

	public List<CategoryProduct> getListeProduitsMisEnVenteSurLeJour() {
		return listeProduitsMisEnVenteSurLeJour;
	}

	public void setListeProduitsMisEnVenteSurLeJour(
			List<CategoryProduct> listeProduitsMisEnVenteSurLeJour) {
		this.listeProduitsMisEnVenteSurLeJour = listeProduitsMisEnVenteSurLeJour;
	}
}
