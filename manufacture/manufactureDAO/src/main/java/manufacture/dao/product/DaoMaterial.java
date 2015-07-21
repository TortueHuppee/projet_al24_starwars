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
import manufacture.entity.product.Material;
import manufacture.idao.product.IDaoColor;
import manufacture.idao.product.IDaoMaterial;

@Service
@Transactional
public class DaoMaterial implements IDaoMaterial {

	private Logger log = Logger.getLogger(DaoMaterial.class);
	private SessionFactory sf;
	
	@Override
	public List<Material> getAllMaterial() {
		Session session = sf.getCurrentSession();
		String requete = "FROM Material a";
		Query hql = session.createQuery(requete);
		List<Material> resultat = hql.list();
		return resultat;
	}

	@Override
	public void addMaterial(Material material) {
		Session session = sf.getCurrentSession();
		session.save(material);
	}

	@Override
	public void deleteMaterial(Material material) {
		Session session = sf.getCurrentSession();
		session.delete(material);
	}

	@Override
	public void updateMaterial(Material material) {
		Session session = sf.getCurrentSession();
		session.update(material);
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
