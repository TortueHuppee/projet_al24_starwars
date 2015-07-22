package test;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import manufacture.entity.product.Color;
import manufacture.ifacade.catalog.ICatalog;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ManagedBean(name="toto")
@SessionScoped
public class TestBean {
	private String toto;
	
private List<Color> listeCouleur;	
	
	private BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springFacade.xml");
	
	private ICatalog proxyCatalog = (ICatalog) bf.getBean(ICatalog.class);
	
	public List<Color> getListeCouleur() {
		return proxyCatalog.getAllColor();
	}

	public void setListeCouleur(List<Color> listeCouleur) {
		this.listeCouleur = listeCouleur;
	}
	
	public String getToto() {
		return "hello";
	}

	public void setToto(String toto) {
		this.toto = toto;
	}
	
}
