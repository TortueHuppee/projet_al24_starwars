package manufacture.test.dao;

import manufacture.dao.product.DaoColor;
import manufacture.entity.product.Color;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConfigSpringHibernate {

	public static void main(String[] args) {
		
		BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
		DaoColor proxyColor = (DaoColor) bf.getBean("daoColor");
		
		Color color = new Color();
		color.setColorName("Rouge");
		
		proxyColor.addColor(color);
	}

}
