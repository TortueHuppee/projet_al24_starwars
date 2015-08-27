package manufacture.web.util;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="stringUtil")
@RequestScoped
public class StringUtil {
	public String trimText(String text,int maxSize){
		if(text.length() > maxSize){
			text = text.substring(0, maxSize);
			return text+"...";
		}
		return text; 
	}
}
