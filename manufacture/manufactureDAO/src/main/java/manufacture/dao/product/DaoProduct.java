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

import manufacture.entity.product.Product;
import manufacture.entity.user.User;
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
	public List<Product> getAllProductByProductRef(int idProducRef) {
		Session session = sf.getCurrentSession();
		String requete = "SELECT p FROM Product p WHERE p.productRef.idProductRef = :paramId AND p.stock > 0 ORDER BY p.price";
		Query hql = session.createQuery(requete);
		hql.setParameter("paramId", idProducRef);
		log.info("id du produit ref passé en argument :" + idProducRef);
		List<Product> resultat = hql.list();
		if (resultat.isEmpty())
		{
			return new ArrayList<Product>();
		}
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
		String requete = "SELECT a FROM Product a WHERE a.stock > 0";
		Query hql = session.createQuery(requete);
		List<Product> resultat = hql.list();
		return resultat;
	}
	public List<Product> getAllProductConstructor(){
		Session session = sf.getCurrentSession();
		String requete = "SELECT a FROM Product a WHERE a.stock > 0 AND a.typeProduct.idTypeProduct = 1";
		Query hql = session.createQuery(requete);
		List<Product> resultat = hql.list();
		return resultat;
	}
	
	@Override
	public List<Product> getProductSendByUser(User user) {
		Session session = sf.getCurrentSession();       
        String requete = "SELECT p.product FROM CartProduct p WHERE p.product.user.idUser = :paramId";
        Query hql = session.createQuery(requete);        
        hql.setParameter("paramId", user.getIdUser());
        List<Product> resultat = hql.list();
        
        if (resultat.size() == 0)
        {
            return new ArrayList<Product>();
        }
        else
        {
            return resultat;
        }
	}
	
	@Override
	public List<Product> getProductNotSendByUser(User user) {
		Session session = sf.getCurrentSession();       
        String requete = "SELECT p FROM Product p WHERE p.user.idUser = :paramId AND p.stock > 0";
        Query hql = session.createQuery(requete);        
        hql.setParameter("paramId", user.getIdUser());
        List<Product> resultat = hql.list();
        
        if (resultat.size() == 0)
        {
            return new ArrayList<Product>();
        }
        else
        {
            return resultat;
        }
	}
	
	@Override
	public List<Product> getAllProductBySpaceShipRefAndName(int idSpaceShipSelected, String name) {
		Session session = sf.getCurrentSession();       
        //String requete = "SELECT p FROM Product p INNER JOIN SpaceshipProduct ssp WHERE p.productRef.idProductRef = ssp.productRef.idProductRef AND ssp.spaceshipRef.idSpaceshipRef = :paramId";
        String requete = "SELECT p FROM Product p, SpaceshipProduct ssp "
                +"WHERE p.productRef.idProductRef = ssp.productRef.idProductRef "
                +"AND ssp.spaceshipRef.idSpaceshipRef = :paramId "
                +"AND LOWER(p.productRef.productName) LIKE :paramName";
        Query hql = session.createQuery(requete);        
        hql.setParameter("paramId", idSpaceShipSelected);
        hql.setParameter("paramName", "%" + name + "%");
        List<Product> resultat = hql.list();
        
        if (resultat.size() == 0)
        {
            return new ArrayList<Product>();
        }
        else
        {
            return resultat;
        }
	}
	
    @Override
    public List<Product> getAllProductByName(String name) {
        Session session = sf.getCurrentSession();       
        String requete = "SELECT p FROM Product p WHERE LOWER(p.productRef.productName) LIKE :paramName";
        Query hql = session.createQuery(requete);        
        hql.setParameter("paramName", "%" + name + "%");
        List<Product> resultat = hql.list();
        
        if (resultat.size() == 0)
        {
            return new ArrayList<Product>();
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
