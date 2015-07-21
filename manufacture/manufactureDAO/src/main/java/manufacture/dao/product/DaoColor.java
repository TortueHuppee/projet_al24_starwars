package manufacture.dao.product;

import java.util.List;



import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.entity.product.Color;

@Service
@Transactional
public class DaoColor implements IDaoColor {

	private Logger log = Logger.getLogger(DaoColor.class);
	private SessionFactory sf;

	@Override
	public List<Color> getAllColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addColor(Color color) {
		Session session = sf.getCurrentSession();
		session.save(color);
		log.info("Ajout réussi ==> " + color.getIdColor());
	}

	@Override
	public void deleteColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateColor(Color color) {
		// TODO Auto-generated method stub
		
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
