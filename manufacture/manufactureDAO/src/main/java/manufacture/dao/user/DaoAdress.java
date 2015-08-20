package manufacture.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.dao.product.DaoColor;
import manufacture.entity.user.Address;
import manufacture.entity.user.User;
import manufacture.idao.user.IDaoAdress;

@Service
@Transactional
public class DaoAdress implements IDaoAdress {
	
	private Logger log = Logger.getLogger(DaoColor.class);
	private SessionFactory sf;

	@Override
	public void createAddress(Address address) {
		Session session = sf.getCurrentSession();
		log.info("Adresse enregistrée");
		session.save(address);	
	}

	@Override
	public void updateAddress(Address address) {
		Session session = sf.getCurrentSession();
		session.update(address);
	}

	@Override
	public void deleteAddress(Address address) {
		Session session = sf.getCurrentSession();
		session.delete(address);
	}
	
	@Override
	public List<Address> getAllAdressByUser(User user) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT u FROM Address u WHERE u.user.idUser = :paramId";
		Query hql = session.createQuery(requete).setParameter("paramId",user.getIdUser());
		List<Address> resultat = hql.list();
		
		if (resultat.size() == 0)
		{
			return new ArrayList<Address>();
		}
		return resultat;
	}
	
	@Override
	public List<Address> getDeliveryAddressByUser(User user) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT u FROM Address u WHERE u.user.idUser = :paramId AND u.isDeliveryaddress = :paramBoolean";
		Query hql = session.createQuery(requete);
		hql.setParameter("paramId",user.getIdUser());
		hql.setParameter("paramBoolean",Boolean.TRUE);
		List<Address> resultat = hql.list();
		
		if (resultat.size() == 0)
		{
			return new ArrayList<Address>();
		}
		return resultat;
	}

	@Override
	public List<Address> getBillingAddressByUser(User user) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT u FROM Address u WHERE u.user.idUser = :paramId AND u.isBillingaddress = :paramBoolean";
		Query hql = session.createQuery(requete);
		hql.setParameter("paramId",user.getIdUser());
		hql.setParameter("paramBoolean",Boolean.TRUE);
		List<Address> resultat = hql.list();
		
		if (resultat.size() == 0)
		{
			return new ArrayList<Address>();
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
