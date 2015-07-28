package manufacture.dao.cart;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.dao.product.DaoColor;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.product.Product;
import manufacture.idao.cart.IDaoPaymentAndDelivery;

@Service
@Transactional
public class DaoPaymentAndDelivery implements IDaoPaymentAndDelivery {
	
	private Logger log = Logger.getLogger(DaoPaymentAndDelivery.class);
	private SessionFactory sf;

	@Override
	public List<PaymentType> getAllPaymentType() {
		Session session = sf.getCurrentSession();
		Query hql = session.createQuery("FROM PaymentType pt");
		List<PaymentType> resultat = hql.list();
		return resultat;
	}

	@Override
	public List<Delivery> getAllDeliveryType() {
		Session session = sf.getCurrentSession();
		Query hql = session.createQuery("FROM Delivery d");
		List<Delivery> resultat = hql.list();
		return resultat;
	}

	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
}
