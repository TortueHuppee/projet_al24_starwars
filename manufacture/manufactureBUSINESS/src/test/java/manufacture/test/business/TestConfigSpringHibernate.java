package manufacture.test.business;

import java.util.List;

import manufacture.entity.product.Color;
import manufacture.ibusiness.catalog.IBusinessCatalog;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConfigSpringHibernate {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
        BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springBusiness.xml");
		IBusinessCatalog proxyCatalog = (IBusinessCatalog) bf.getBean(IBusinessCatalog.class);

		List<Color> listeCouleur = proxyCatalog.getAllColor();
		for (Color color : listeCouleur)
		{
			System.out.println(color.getColorName());
		}
	}

}
