package fr.afcepf.al24.web.administrator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name="mbAdmin")
public class AdministratorBean {

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
		createPieModel();
		
		articlesMisEnLigne = new SelectItemGroup();        
        SelectItem[] tableuArticleMisEnLigne = new SelectItem[3];
        tableuArticleMisEnLigne[0] = new SelectItem(32, "Produit neuf", "first");
        tableuArticleMisEnLigne[1] = new SelectItem(14, "Produit artisanaux", "second");
        tableuArticleMisEnLigne[2] = new SelectItem(25, "Produit d'occasion", "third");
        articlesMisEnLigne.setSelectItems(tableuArticleMisEnLigne);
        
        articlesVendus = new SelectItemGroup();
        SelectItem[] tableauArticleVendu = new SelectItem[4];
        tableauArticleVendu[0] = new SelectItem(142, "Armes", "first");
        tableauArticleVendu[1] = new SelectItem(18, "Accessoires", "second");
        tableauArticleVendu[2] = new SelectItem(67, "Pièces détachées", "third");
        tableauArticleVendu[3] = new SelectItem(103, "Technologies", "fourth");
        articlesVendus.setSelectItems(tableauArticleVendu);
				
		sommeArticlesVendus = 142 + 18 + 67 + 103;
		
		sommeArticlesMisEnLigne = 32 + 14 + 25;
	}

	//Méthodes
	//small (20px), smaller (35px), medium (50px), mediumLarge (75px), large (100px)
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
	
	private void createPieModel() {
		//Implémentation à faire avec le BigData
		pieModel = new PieChartModel();

		pieModel.set("Armes", 540);
		pieModel.set("Pièces détachées", 325);
		pieModel.set("Accessoires", 702);
		pieModel.set("Technologie", 421);

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
}
