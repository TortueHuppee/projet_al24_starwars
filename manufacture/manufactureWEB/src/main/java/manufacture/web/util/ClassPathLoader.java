package manufacture.web.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathLoader {
	private static BeanFactory bf;
	
	public static BeanFactory getFacadeBeanFactory(){
		if(bf == null){
			bf = new ClassPathXmlApplicationContext("classpath:springFacade.xml");
		}
		return bf;
	}
}
