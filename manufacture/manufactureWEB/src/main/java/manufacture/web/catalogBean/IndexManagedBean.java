package manufacture.web.catalogBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.ifacade.catalog.ICatalog;

import org.apache.log4j.Logger;

import manufacture.web.util.ClassPathLoader;

import org.springframework.beans.factory.BeanFactory;

@ManagedBean(name="mbIndex")
@SessionScoped
public class IndexManagedBean {

	private Logger log = Logger.getLogger(IndexManagedBean.class);

	@ManagedProperty(value="#{catalog}")
	private ICatalog proxyCatalog;

	public void setProxyCatalog(ICatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}

	private List<ConstructorProduct> listeProduitBrute;
	private List<Product> listeProduitRandom;

	@PostConstruct
	void init()
	{
		listeProduitBrute = proxyCatalog.getAllConstructorProduct();
		log.info(listeProduitBrute.size());
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
		log.info(listeProduitRandom.size());
	}

	public ICatalog getProxyCatalog() {
		return proxyCatalog;
	}

	public List<ConstructorProduct> getListeProduitBrute() {
		return listeProduitBrute;
	}

	public void setListeProduitBrute(List<ConstructorProduct> listeProduitBrute) {
		this.listeProduitBrute = listeProduitBrute;
	}

	public List<Product> getListeProduitRandom() {
		return listeProduitRandom;
	}

	public void setListeProduitRandom(List<Product> listeProduitRandom) {
		this.listeProduitRandom = listeProduitRandom;
	}

}