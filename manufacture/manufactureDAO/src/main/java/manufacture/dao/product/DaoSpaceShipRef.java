package manufacture.dao.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.idao.product.IDaoSpaceShipRef;

@Service
@Transactional
public class DaoSpaceShipRef implements IDaoSpaceShipRef {

	private Logger log = Logger.getLogger(DaoSpaceShipRef.class);
	private SessionFactory sf;
	
	@Override
	public List<SpaceshipRef> getAllSpaceshipRef() {
		Session session = sf.getCurrentSession();
		String requete = "FROM SpaceshipRef a";
		Query hql = session.createQuery(requete);
		List<SpaceshipRef> resultat = hql.list();
		return resultat;
	}

	@Override
	public void addSpaceShipRef(SpaceshipRef spaceShipRef) {
		Session session = sf.getCurrentSession();
		session.save(spaceShipRef);
	}

	@Override
	public void deleteSpaceShipRef(SpaceshipRef spaceShipRef) {
		Session session = sf.getCurrentSession();
		session.delete(spaceShipRef);
	}

	@Override
	public void updateSpaceShipRef(SpaceshipRef spaceShipRef) {
		Session session = sf.getCurrentSession();
		session.update(spaceShipRef);
	}
	
	@Override
	public List<SpaceshipProduct> getAllSpaceShipProduct() {
		Session session = sf.getCurrentSession();
		String requete = "FROM SpaceshipProduct a";
		Query hql = session.createQuery(requete);
		List<SpaceshipProduct> resultat = hql.list();
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
