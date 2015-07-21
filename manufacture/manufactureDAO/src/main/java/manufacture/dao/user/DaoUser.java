package manufacture.dao.user;

import java.util.List;

import manufacture.entity.product.Color;
import manufacture.entity.user.Administrator;
import manufacture.entity.user.Artisan;
import manufacture.entity.user.ProfessionnalCustomer;
import manufacture.entity.user.SpecificCustomer;
import manufacture.entity.user.User;
import manufacture.idao.user.IDaoUser;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DaoUser implements IDaoUser {

	private Logger LOGGER = Logger.getLogger(DaoUser.class);
	private SessionFactory sf;
	
	@Override
	public List<String> getAllLogin() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> getAllEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUserLogin(User user){
		Session session = sf.getCurrentSession();
		String requete = "FROM User u WHERE u.password = :password AND u.userName = :username";
		Query hql = session.createQuery(requete).setParameter("password", user.getPassword()).setParameter("username", user.getUserName());
		List<User> resultat = hql.list();
		if(resultat.size() == 0 || resultat.size() >= 2){
			return null;
		}
		return resultat.get(0);
	}
	
	@Override
	public void getPasswordByLogin(String login) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createAdministrator(Administrator admin) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createArtisan(Artisan artisan) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createProfessionalCustomer(
			ProfessionnalCustomer professionalCustomer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createSpecificCustomer(SpecificCustomer specificCustomer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void closeAccount(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public User openAccount(User user) {
		Session session = sf.getCurrentSession();
		LOGGER.info("user pre save : "+user.getIdUser());
		session.save(user);
		LOGGER.info("user saved : "+user.getIdUser());
		return user;		
	}
	//Getters et Setters
	public SessionFactory getSf() {
		return sf;
	}

	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
}
