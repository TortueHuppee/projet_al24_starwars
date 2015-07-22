package manufacture.dao.cart;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import manufacture.dao.product.DaoColor;
import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.cart.Delivery;
import manufacture.entity.cart.PaymentType;
import manufacture.entity.user.ProfessionnalCustomer;
import manufacture.entity.user.User;
import manufacture.idao.cart.IDaoCart;

public class DaoCart implements IDaoCart {
	
	private Logger log = Logger.getLogger(DaoColor.class);
	private SessionFactory sf;
	
	private String requestValidatePayment = "FROM Cart c WHERE c.idCart = :idCart";
	private String requestSendRecall = "FROM User u WHERE u.idUser = :idUser and AND u.class = 'constructor_product'";

//	@Override
//	public double getTotalPrice(int idCart) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public void validatePayment(int idCart) {

		Session session = sf.getCurrentSession();
		Query hql = session.createQuery(requestValidatePayment);
		hql.setParameter("idCart", idCart);
		List<Cart> listResult = hql.list();
		for (Cart cart : listResult) {
			cart.setDatePayment(new Date());
			cart.setIsPaid((byte) 1);
			Random rand = new Random();
			int transactionNumber = rand.nextInt(999999999 - 100000000 + 1) + 100000000;
			cart.setTransactionNumber(transactionNumber);
			session.update(cart);
		}
	}

	@Override
	public int createNewCart(int idUser) {
		Session session = sf.getCurrentSession();
		Cart cart = new Cart();
		User user = new User();
		user.setIdUser(idUser);
		cart.setUser(user);
		cart.setIsPaid((byte) 0);
		cart.setIsValidated((byte) 0);
		session.save(cart);
		return cart.getIdCart();
	}

	@Override
	public void sendRecall(int idUser) {
		Session session = sf.getCurrentSession();
		Query hql = session.createQuery(requestSendRecall);
		hql.setParameter("idUser", idUser);
		List<ProfessionnalCustomer> listResult = hql.list();
		for (ProfessionnalCustomer profCust : listResult) {
			profCust.setNbRecall(profCust.getNbRecall() + 1);
			session.update((User)profCust); // a verifier si c'est necessaire
		}
	
	}	

	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}



}
