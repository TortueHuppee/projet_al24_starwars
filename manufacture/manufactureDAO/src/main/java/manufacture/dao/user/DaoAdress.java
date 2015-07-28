package manufacture.dao.user;

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
import manufacture.idao.dataloading.IDaoCity;
import manufacture.idao.user.IDaoAdress;

@Transactional
@Service
public class DaoAdress implements IDaoAdress {
	
	private Logger log = Logger.getLogger(DaoColor.class);
	private SessionFactory sf;

	@Override
	public void createAdress(Address adress) {
		Session session = sf.getCurrentSession();
		session.save(adress);	
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
