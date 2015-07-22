package manufacture.web.catalogBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.ifacade.catalog.ICatalog;

import manufacture.web.util.ClassPathLoader;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ManagedBean(name="mbCatalog")
@SessionScoped
public class ManagedBeanCatalog {
	
	private BeanFactory bf = ClassPathLoader.getFacadeBeanFactory();
	private ICatalog proxyCatalog = (ICatalog) bf.getBean(ICatalog.class);
	
	private List<ProductRef> listeConstructorProductRef;
	private List<ProductRef> listeUsedProductRef;
	private List<ProductRef> listeArtisanProductRef;
	
	@PostConstruct
	void init()
	{
		listeConstructorProductRef = proxyCatalog.getAllConstructorProductRef();
		listeArtisanProductRef = proxyCatalog.getAllArtisanProductRef();
		listeUsedProductRef = proxyCatalog.getAllUsedProductRef();
	}

	//Getters et Setters
	public List<ProductRef> getListeConstructorProductRef() {
		return listeConstructorProductRef;
	}

	public void setListeConstructorProductRef(
			List<ProductRef> listeConstructorProductRef) {
		this.listeConstructorProductRef = listeConstructorProductRef;
	}

	public List<ProductRef> getListeUsedProductRef() {
		return listeUsedProductRef;
	}

	public void setListeUsedProductRef(List<ProductRef> listeUsedProductRef) {
		this.listeUsedProductRef = listeUsedProductRef;
	}

	public List<ProductRef> getListeArtisanProductRef() {
		return listeArtisanProductRef;
	}

	public void setListeArtisanProductRef(List<ProductRef> listeArtisanProductRef) {
		this.listeArtisanProductRef = listeArtisanProductRef;
	}
}
