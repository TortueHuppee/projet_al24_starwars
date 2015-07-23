package manufacture.web.cart;

import manufacture.web.user.UserBeanTmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")
public class CartBean {
	@Autowired
	private UserBeanTmp userBean;
	
}
