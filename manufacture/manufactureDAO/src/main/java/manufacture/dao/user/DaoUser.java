package manufacture.dao.user;

import java.util.ArrayList;
import java.util.List;

import manufacture.entity.user.Civility;
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
		Session session = sf.getCurrentSession();
		String requete = "SELECT u.login FROM User u";
		Query hql = session.createQuery(requete);
		List<String> resultat = hql.list();
		return resultat;
	}
	
	@Override
	public List<String> getAllEmail() {
		Session session = sf.getCurrentSession();
		String requete = "SELECT u.email FROM User u";
		Query hql = session.createQuery(requete);
		List<String> resultat = hql.list();
		return resultat;
	}
	
	@Override
	public List<User> getUserByLogin(String login) {
		Session session = sf.getCurrentSession();
		String requete = "FROM User u WHERE u.login = :login";
		Query hql = session.createQuery(requete);
		hql.setParameter("login",login);
		List<User> resultat = hql.list();
		return resultat;
	}
	
	@Override
	public List<User> getUserByEmail(String email) {
		Session session = sf.getCurrentSession();
		String requete = "FROM User u WHERE u.email = :email";
		Query hql = session.createQuery(requete);
		hql.setParameter("email",email);
		List<User> resultat = hql.list();
		LOGGER.info("*********** getUserByEmail dans dao fonctionne : user = "+ resultat.get(0).getUserName()+" ***********");
		return resultat;
	}
	
	@Override
	public User getUserLogin(User user){
		Session session = sf.getCurrentSession();
		String requete = "FROM User u WHERE u.password = :password AND u.login = :username";
		Query hql = session.createQuery(requete).setParameter("password", user.getPassword()).setParameter("username", user.getLogin());
		List<User> resultat = hql.list();
		if(resultat.size() == 0 || resultat.size() >= 2){
			return null;
		}
		return resultat.get(0);
	}
	
	@Override
	public String getPasswordByLogin(String login) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT u.password FROM User u WHERE u.login = :paramLogin";
		Query hql = session.createQuery(requete).setParameter("paramLogin", login);
		List<String> resultat = hql.list();
		if(resultat.size() == 0 || resultat.size() >= 2){
			return null;
		}
		return resultat.get(0);
	}
	
	@Override
	public void getPasswordByEmail(String email) {
		// TODO Auto-generated method stub
	}

	@Override
	public void closeAccount(User user) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public User openAccount(User user) {
		Session session = sf.getCurrentSession();
		session.save(user);
		return user;		
	}
	
	@Override
	public List<User> getUserByUserName(String userName) {
		Session session = sf.getCurrentSession();
		String requete = "FROM User u WHERE u.userName = :username";
		Query hql = session.createQuery(requete).setParameter("username",userName);
		List<User> resultat = hql.list();
		return resultat;
	}
	
	@Override
	public User editUser(User user) {
		Session session = sf.getCurrentSession();
		session.update(user);
		return user;
	}
	
	@Override
	public List<User> getAllArtisan() {
		Session session = sf.getCurrentSession();
		String requete = "FROM User u WHERE u.userRole.idUserRole = 3";
		Query hql = session.createQuery(requete);
		List<User> resultat = hql.list();
		
		if (resultat.size() == 0 )
		{
			return new ArrayList<User>();
		}
		return resultat;
	}
	
	@Override
	public List<Civility> getAllCivility() {
		Session session = sf.getCurrentSession();
		String requete = "FROM Civility u";
		Query hql = session.createQuery(requete);
		List<Civility> resultat = hql.list();
		
		if (resultat.size() == 0 )
		{
			return new ArrayList<Civility>();
		}
		return resultat;
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
