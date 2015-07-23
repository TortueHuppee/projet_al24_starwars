package manufacture.business.inscription;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import manufacture.business.util.ClassPathLoader;
import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.user.Address;
import manufacture.entity.user.Administrator;
import manufacture.entity.user.Artisan;
import manufacture.entity.user.ProfessionnalCustomer;
import manufacture.entity.user.SpecificCustomer;
import manufacture.entity.user.User;
import manufacture.ibusiness.catalog.IBusinessCatalog;
import manufacture.ibusiness.user.IBusinessInscription;
import manufacture.idao.user.IDaoUser;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BusinessInscription implements IBusinessInscription {

	BeanFactory bf = ClassPathLoader.getDaoBeanFactory();	
	IDaoUser proxyUser = (IDaoUser) bf.getBean(IDaoUser.class);

	@Override
	public User createAccount(User user) {
		return proxyUser.openAccount(user);
	}

	@Override
	public Administrator createAdministrator(Administrator admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artisan createArtisan(Artisan artisan) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ProfessionnalCustomer createProfessionalCustomer(
			ProfessionnalCustomer professionalCustomer) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SpecificCustomer createSpecificCustomer(
			SpecificCustomer specificCustomer) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Address createAdress(Address adress) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean loginAlreadyExisting(String login) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean userExists(User user) {
		List<User> userFound = proxyUser.getUserByUserName(user.getUserName());
		return userFound.size() == 0 ? false:true;
	}

	@Override
	public boolean emailAlreadyExisting(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createAccount(User user, Address adress) {
		// TODO Auto-generated method stub
		
	}
}
