package manufacture.business.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathLoader {
	private static BeanFactory bf;
	
	public static BeanFactory getDaoBeanFactory(){
		if(bf == null){
			bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
		}
		return bf;
	}
}
