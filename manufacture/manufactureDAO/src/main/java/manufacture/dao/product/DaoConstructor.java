package manufacture.dao.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.entity.product.Constructor;
import manufacture.idao.product.IDaoConstructor;

@Service
@Transactional
public class DaoConstructor implements IDaoConstructor {

	private Logger log = Logger.getLogger(DaoConstructor.class);
	private SessionFactory sf;
	
	@Override
	public List<Constructor> getAllConstructor() {
		Session session = sf.getCurrentSession();
		String requete = "FROM Constructor a";
		Query hql = session.createQuery(requete);
		List<Constructor> resultat = hql.list();
		return resultat;
	}

	@Override
	public void addConstructor(Constructor constructor) {
		Session session = sf.getCurrentSession();
		session.save(constructor);
	}

	@Override
	public void deleteConstructor(Constructor constructor) {
		Session session = sf.getCurrentSession();
		session.delete(constructor);
	}

	@Override
	public void updateConstructor(Constructor constructor) {
		Session session = sf.getCurrentSession();
		session.update(constructor);
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
