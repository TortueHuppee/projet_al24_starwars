package manufacture.dao.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.entity.product.Color;
import manufacture.idao.product.IDaoColor;

@Service
@Transactional
public class DaoColor implements IDaoColor {

	private Logger log = Logger.getLogger(DaoColor.class);
	private SessionFactory sf;

//	public Animal getAnimalsById(int idAnimal)
//	{
//		Session session = sf.getCurrentSession();
//		String requete = "SELECT a FROM Animal a WHERE a.idAnimal = :paramId";
//		Query hql = session.createQuery(requete);
//		hql.setParameter("paramId", idAnimal);
//		List<Animal> resultat = hql.list();
//		return resultat.get(0);
//	}
	
	@Override
	public List<Color> getAllColor() {
		Session session = sf.getCurrentSession();
		String requete = "FROM Color a";
		Query hql = session.createQuery(requete);
		List<Color> resultat = hql.list();
		return resultat;
	}

	@Override
	public void addColor(Color color) {
		Session session = sf.getCurrentSession();
		session.save(color);
	}

	@Override
	public void deleteColor(Color color) {
		Session session = sf.getCurrentSession();
		session.delete(color);
	}

	@Override
	public void updateColor(Color color) {
		Session session = sf.getCurrentSession();
		session.update(color);
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
