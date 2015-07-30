package manufacture.dao.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.entity.cart.Cart;
import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.idao.product.IDaoColor;
import manufacture.idao.product.IDaoProduct;

@Service
@Transactional
public class DaoProduct implements IDaoProduct {

	private Logger log = Logger.getLogger(DaoProduct.class);
	private SessionFactory sf;
	

	@Override
	public Product getProductByIdProduct(int idProduct) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT p FROM Product p WHERE p.idProduct = :idProduct";
		Query hql = session.createQuery(requete);
		hql.setParameter("idProduct", idProduct);
		return (Product) hql.list().get(0);
	}
	
	@Override
	public List<ConstructorProduct> getAllProductByProductRef(int idProducRef) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT p FROM Product p WHERE p.productRef.idProductRef = :paramId ORDER BY p.price";
		Query hql = session.createQuery(requete);
		hql.setParameter("paramId", idProducRef);
		List<ConstructorProduct> resultat = hql.list();
		return resultat;
	}

	@Override
	public void updateProductStock(int idProduct, int quantitySend) {
		Session session = sf.getCurrentSession();
		Product product = getProductByIdProduct(idProduct);
		product.setStock(product.getStock() - quantitySend);
		session.save(product);
	}

	@Override
	public void checkProductStock(int idProduct) {
		Session session = sf.getCurrentSession();
		Product product = getProductByIdProduct(idProduct);
		product.getStock();
	}

	@Override
	public void addProduct(Product product) {
		Session session = sf.getCurrentSession();
		session.save(product);
	}

	@Override
	public void deleteProduct(Product product) {
		Session session = sf.getCurrentSession();
		session.delete(product);
	}

	@Override
	public void updateProduct(Product product) {
		Session session = sf.getCurrentSession();
		session.update(product);
	}
	
	@Override
	public List<Product> getAllProduct() {
		Session session = sf.getCurrentSession();
		String requete = "FROM Product a JOIN a.productRef pr";
		Query hql = session.createQuery(requete);
		List<Product> resultat = hql.list();
		return resultat;
	}
	
	@Override
	public List<ConstructorProduct> getAllConstructorProduct() {
		Session session = sf.getCurrentSession();		
		//Requete à partir de la valeur discriminatrice
		//String requete = "SELECT DISTINCT p.productRef FROM Product p WHERE p.class='constructor_product'";
		String requete = "SELECT p FROM Product p WHERE p.class='constructor_product'";
		Query hql = session.createQuery(requete);
		List<ConstructorProduct> resultat = hql.list();
		
		if (resultat.size() == 0)
		{
		    return new ArrayList<ConstructorProduct>();
		}
		else
		{
		    return resultat;
		}
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
