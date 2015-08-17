package manufacture.test.facade;

import java.util.List;

import manufacture.ifacade.catalog.ICatalog;
import manufacture.entity.product.Color;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConfigSpringHibernate {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
        BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springFacade.xml");
		ICatalog proxyCatalog = (ICatalog) bf.getBean(ICatalog.class);

		List<Color> listeCouleur = proxyCatalog.getAllColor();
		for (Color color : listeCouleur)
		{
			System.out.println(color.getColorName());
		}
	}

}
