package manufacture.dao.address;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.entity.product.Color;
import manufacture.entity.user.City;
import manufacture.entity.user.Planet;
import manufacture.idao.dataloading.IDaoCity;
import manufacture.idao.dataloading.IDaoPlanet;

@Transactional
@Service
public class DaoPlanet implements IDaoPlanet {
	
	@Autowired
	private SessionFactory sf;
	
    @Override
    public void addPlanet(Planet planet) {
        Session session = sf.getCurrentSession();
        session.save(planet);
    }
    
    @Override
    public List<Planet> getAllPlanet() {
        Session session = getSf().getCurrentSession();
        String requete = "FROM Planet a";
        Query hql = session.createQuery(requete);
        List<Planet> resultat = hql.list();
        return resultat;
    }
    
	public SessionFactory getSf() {
		return sf;
	}
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
}
