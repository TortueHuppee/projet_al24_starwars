package manufacture.dao.cart;

import java.util.ArrayList;
import java.util.List;

import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.Product;
import manufacture.entity.user.User;
import manufacture.idao.cart.IDaoProductCart;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DaoProductCart implements IDaoProductCart {
	
	private Logger log = Logger.getLogger(DaoProductCart.class);
	private SessionFactory sf;
	
	private String requestUpdateOptionsProduct = "UPDATE CartProduct cp set cp.product = :newProduct where cp.idCartProduct = :idCartProduct";
	private String requestUpdateQuantityProduct = "UPDATE CartProduct cp set cp.quantity = :newQuantity where cp.idCartProduct = :idCartProduct";
	private String requestGetAllProductByCart = "SELECT cp.product FROM CartProduct cp WHERE cp.cart.idCart = :idCart";
	private String requestGetAllUsedProductByCart = "SELECT cp.product FROM CartProduct cp WHERE cp.cart.idCart = :idCart AND cp.cart.class = 'used_product'";
	private String requestGetAllConstructorProductByCart = "SELECT cp.product FROM CartProduct cp WHERE cp.cart.idCart = :idCart AND cp.cart.class = 'constructor_product'";
	private String requestgetSubTotalPrice = "SELECT cp FROM CartProduct cp WHERE cp.idCartProduct = :idCartProduct";
	
	
	@Override
	public void addProductToCart(CartProduct cartProduct) {
		
		Session session = sf.getCurrentSession();
		session.save(cartProduct);
	}

	@Override
	public void deleteProductFromCart(CartProduct cartProduct) {
		
		Session session = sf.getCurrentSession();
		session.delete(cartProduct);
	}


	@Override
	public void updateOptionsProduct(int idCartProduct, Product newProduct) {

		Session session = sf.getCurrentSession();
		
//		Query hql = session.createQuery(requestUpdateOptionsProduct);
//		hql.setParameter("newProduct", newProduct);
//		hql.setParameter("idCartProduct", idCartProduct);
//		hql.executeUpdate();
		
		session.update(newProduct); // à tester
	}

	@Override
	public void updateQuantityProduct(int idCartProduct, int newQuantity) {

		Session session = sf.getCurrentSession();
		
//		Query hql = session.createQuery(requestUpdateQuantityProduct);
//		hql.setParameter("newQuantity", newQuantity);
//		hql.setParameter("idCartProduct", idCartProduct);
//		hql.executeUpdate();
		
		session.update(newQuantity);
	}

	@Override
	public List<Product> getAllProductByCart(int idCart) {
		
		Session session = sf.getCurrentSession();
		Query hql = session.createQuery(requestGetAllProductByCart);
		hql.setParameter("idCart", idCart);
		List<Product> resultat = hql.list();
		return resultat;
	}

	@Override
	public List<Product> getAllArtisanProductByCart(int idCart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllUsedProductByCart(int idCart) {
		
		Session session = sf.getCurrentSession();
		Query hql = session.createQuery(requestGetAllUsedProductByCart);
		hql.setParameter("idCart", idCart);
		List<Product> resultat = hql.list();
		
		return resultat;
	}

	@Override
	public List<Product> getAllConstructorProductByCart(int idCart) {
		
		Session session = sf.getCurrentSession();
		Query hql = session.createQuery(requestGetAllConstructorProductByCart);
		hql.setParameter("idCart", idCart);
		List<Product> resultat = hql.list();
		
		return resultat;
	}
	
	
	@Override
	public List<CartProduct> getCartSendByUser(User user) {
		Session session = sf.getCurrentSession();
        Query hql = session.createQuery("SELECT c FROM CartProduct c WHERE c.product.user.idUser = :paramId");
        hql.setParameter("paramId", user.getIdUser());
        List<CartProduct> resultat = hql.list();
        
        if (resultat.size() == 0)
        {
            return new ArrayList<CartProduct>();
        }
        else
        {
            return resultat;
        }
	}

	@Override
	public double getSubTotalPrice(int idCartProduct) {
		Session session = sf.getCurrentSession();
		Query hql = session.createQuery(requestgetSubTotalPrice);
		hql.setParameter("idCartProduct", idCartProduct);
		List<CartProduct> listResult = hql.list();
		double result = 0;
		for (CartProduct cartProduct : listResult) {
			result = cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
		}
		
		return result;
	}

	public SessionFactory getSf() {
		return sf;
	}

	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
}
