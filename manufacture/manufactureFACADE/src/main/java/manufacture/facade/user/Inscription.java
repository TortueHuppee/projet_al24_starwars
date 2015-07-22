package manufacture.facade.user;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.user.Address;
import manufacture.entity.user.User;
import manufacture.facade.util.ClassPathLoader;
import manufacture.ibusiness.catalog.IBusinessCatalog;
import manufacture.ibusiness.user.IBusinessInscription;
import manufacture.idao.product.IDaoColor;
import manufacture.ifacade.catalog.ICatalog;
import manufacture.ifacade.user.IInscription;

@Service
public class Inscription implements IInscription {

	BeanFactory bf = ClassPathLoader.getBusinessBeanFactory();
	IBusinessInscription proxyInscription = (IBusinessInscription) bf.getBean(IBusinessInscription.class);
	
	@Override
	public User createAccount(User user) {
		if(!proxyInscription.userExists(user)){
			proxyInscription.createAccount(user);
		}else{
			user.setIdUser(null);
		}
		return user;
	}
	
}
