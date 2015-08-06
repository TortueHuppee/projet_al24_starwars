package manufacture.web.catalogBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import manufacture.entity.product.Product;
import manufacture.ifacade.catalog.ICatalog;

import org.apache.log4j.Logger;

@ManagedBean(name="mbIndex")
@ApplicationScoped
public class IndexManagedBean {

	private Logger log = Logger.getLogger(IndexManagedBean.class);

	@ManagedProperty(value="#{catalog}")
	private ICatalog proxyCatalog;

	public void setProxyCatalog(ICatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}

	private List<Product> listeProduitBrute;
	private List<Product> listeProduitRandom;

	@PostConstruct
	void init()
	{
		listeProduitBrute = proxyCatalog.getAllProduct();
		listeProduitRandom = new ArrayList<Product>();
		
		for (int i = 0; i < 5; i++)
		{
			Random rand = new Random();
			int randomIdProduct = rand.nextInt(listeProduitBrute.size()) + 1;
			
			for (Product produit : listeProduitBrute)
			{
				if (produit.getIdProduct() == randomIdProduct)
				{
					listeProduitRandom.add(produit);
				}
			}
		}
	}

	public ICatalog getProxyCatalog() {
		return proxyCatalog;
	}

	public List<Product> getListeProduitRandom() {
		return listeProduitRandom;
	}

	public void setListeProduitRandom(List<Product> listeProduitRandom) {
		this.listeProduitRandom = listeProduitRandom;
	}

	public List<Product> getListeProduitBrute() {
		return listeProduitBrute;
	}

	public void setListeProduitBrute(List<Product> listeProduitBrute) {
		this.listeProduitBrute = listeProduitBrute;
	}

}