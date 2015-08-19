package manufacture.test.dao;

import manufacture.idao.product.IDaoColor;
import manufacture.entity.product.Color;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConfigSpringHibernate {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
        BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
		IDaoColor proxyColor = (IDaoColor) bf.getBean(IDaoColor.class);
		
		Color color = new Color();
		color.setColorName("Rouge");
		
		proxyColor.addColor(color);
	}

}
