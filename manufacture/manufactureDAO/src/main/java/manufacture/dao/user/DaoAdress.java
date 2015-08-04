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
import manufacture.entity.product.Color;
import manufacture.entity.user.Address;
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.entity.user.User;
import manufacture.idao.dataloading.IDaoCity;
import manufacture.idao.user.IDaoAdress;

@Service
@Transactional
public class DaoAdress implements IDaoAdress {
	
	private Logger log = Logger.getLogger(DaoColor.class);
	private SessionFactory sf;

	@Override
	public void createAddress(Address address) {
		Session session = sf.getCurrentSession();
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
		List<Address> resultat = new ArrayList<Address>();
		resultat = hql.list();
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
