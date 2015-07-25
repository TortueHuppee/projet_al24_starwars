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
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.idao.product.IDaoColor;
import manufacture.idao.product.IDaoProductRef;

@Service
@Transactional
public class DaoProductRef implements IDaoProductRef {

	private Logger log = Logger.getLogger(DaoProductRef.class);
	private SessionFactory sf;
	
	@Override
	public List<ProductRef> getAllProductRef() {
		Session session = sf.getCurrentSession();
		String requete = "FROM ProductRef a";
		Query hql = session.createQuery(requete);
		List<ProductRef> resultat = hql.list();
		return resultat;
	}

	@Override
	public List<ProductRef> getAllConstructorProductRef() {
		Session session = sf.getCurrentSession();		
		//Requete à partir de la valeur discriminatrice

		//String requete = "SELECT DISTINCT p.productRef FROM Product p WHERE p.class='constructor_product'";
		String requete = "SELECT p.productRef FROM Product p JOIN p.productRef pr WHERE p.class='constructor_product'";
		Query hql = session.createQuery(requete);
		List<ProductRef> resultat = hql.list();
		return resultat;
	}

	@Override
	public List<ProductRef> getAllUsedProductRef() {
		Session session = sf.getCurrentSession();		
		//Requete à partir de la valeur discriminatrice
		String requete = "SELECT p.productRef FROM Product p WHERE p.class='used_product'";
		Query hql = session.createQuery(requete);
		List<ProductRef> resultat = hql.list();
		return resultat;
	}

	@Override
	public List<ProductRef> getAllArtisanProductRef() {
		Session session = sf.getCurrentSession();		
		//Requete à partir de la valeur discriminatrice
		String requete = "SELECT p.productRef FROM Product p WHERE p.class='artisan_product'";
		Query hql = session.createQuery(requete);
		List<ProductRef> resultat = hql.list();
		return resultat;
	}

	@Override
	public List<ProductRef> getProductRefByName(String name) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT p FROM ProductRef p WHERE LOWER(p.productName) like LOWER(:paramId)";
		Query hql = session.createQuery(requete);
		hql.setParameter("paramId", "%" + name + "%");
		List<ProductRef> resultat = hql.list();
		return resultat;
	}

	@Override
	public List<ProductRef> getConstructorProductRefBySpaceShip(
			SpaceshipRef spaceShipRef) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT p.productRef FROM SpaceshipProduct p WHERE p.spaceshipRef.idSpaceshipRef =:paramId";
		Query hql = session.createQuery(requete);
		hql.setParameter("paramId", spaceShipRef);
		List<ProductRef> resultat = hql.list();
		return resultat;
	}
	
	@Override
	public List<SpaceshipProduct> getSpaceShipProductByProduct(
			ProductRef productRef) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT p FROM SpaceshipProduct p WHERE p.productRef.idProductRef =:paramId";
		Query hql = session.createQuery(requete);
		hql.setParameter("paramId", productRef.getIdProductRef());
		List<SpaceshipProduct> resultat = hql.list();
		return resultat;
	}

	@Override
	public void addProductRef(ProductRef productRef) {
		Session session = sf.getCurrentSession();
		session.save(productRef);
	}

	@Override
	public void deleteProductRef(ProductRef productRef) {
		Session session = sf.getCurrentSession();
		session.delete(productRef);
	}

	@Override
	public void updateProductRef(ProductRef productRef) {
		Session session = sf.getCurrentSession();
		session.update(productRef);
	}
	
	@Override
	public void addSpaceShipProduct(SpaceshipProduct spaceShipProduct) {
		Session session = sf.getCurrentSession();
		session.save(spaceShipProduct);
	}
	
	@Override
	public ProductRef getProductRefById(int idProduct) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT p.productRef FROM Product p WHERE p.idProduct= :paramId";
		Query hql = session.createQuery(requete);
		hql.setParameter("paramId", idProduct);
		List<ProductRef> resultat = hql.list();
		if(resultat.size() == 0){
			//Rustine
			return new ProductRef();
		}else{			
			return resultat.get(0);
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
