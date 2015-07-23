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
import manufacture.ibusiness.user.IBusinessConnection;
import manufacture.ibusiness.user.IBusinessInscription;
import manufacture.idao.product.IDaoColor;
import manufacture.ifacade.catalog.ICatalog;
import manufacture.ifacade.user.IConnection;
import manufacture.ifacade.user.IInscription;

@Service
public class Connection implements IConnection{

	IBusinessConnection proxyConnection;
	
	@Override
	public User getSignInUser(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getSignOutUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean resertPassword(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getLogin(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User connectUser(User user) {
		return proxyConnection.logUser(user);
	}

	public IBusinessConnection getProxyConnection() {
		return proxyConnection;
	}

	@Autowired
	public void setProxyConnection(IBusinessConnection proxyConnection) {
		this.proxyConnection = proxyConnection;
	}
}
