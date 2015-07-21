package manufacture.business.inscription;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BusinessInscription implements IBusinessInscription {

	BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");	
	IDaoUser proxyUser = (IDaoUser) bf.getBean(IDaoUser.class);

	@Override
	public User createAccount(User user) {
		user.setPassword(sha256(user.getPassword()));
		return proxyUser.openAccount(user);
	}

	@Override
	public Administrator createAdministrator(Administrator admin) {
		// TODO Auto-generated method stub
		return null;
	}
	public static String sha256(String input) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA256");
			byte[] result = mDigest.digest(input.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();	
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return input;
		}

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
	public boolean userExists(User user) {
		return false;
	}

}
