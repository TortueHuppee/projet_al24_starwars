package manufacture.facade.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathLoader {
	private static BeanFactory bf;
	
	public static BeanFactory getBusinessBeanFactory(){
		if(bf == null){
			bf = new ClassPathXmlApplicationContext("classpath:springBusiness.xml");
		}
		return bf;
	}
}
