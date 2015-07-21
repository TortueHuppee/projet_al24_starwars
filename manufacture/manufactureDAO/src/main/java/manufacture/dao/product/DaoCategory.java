package manufacture.dao.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.entity.product.Category;
import manufacture.idao.product.IDaoCategory;

@Service
@Transactional
public class DaoCategory implements IDaoCategory {

	private Logger log = Logger.getLogger(DaoCategory.class);
	private SessionFactory sf;

	@Override
	public List<Category> getAllCategory() {
		Session session = sf.getCurrentSession();
		String requete = "FROM Category a";
		Query hql = session.createQuery(requete);
		List<Category> resultat = hql.list();
		return resultat;
	}

	@Override
	public void addCategory(Category category) {
		Session session = sf.getCurrentSession();
		session.save(category);
	}

	@Override
	public void deleteCategory(Category category) {
		Session session = sf.getCurrentSession();
		session.delete(category);
	}

	@Override
	public void updateCategory(Category category) {
		Session session = sf.getCurrentSession();
		session.update(category);
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
