package manufacture.dao.address;

import java.util.List;

import manufacture.entity.user.Country;
import manufacture.idao.dataloading.IDaoCountry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DaoCountry implements IDaoCountry {
	
	@Autowired
	private SessionFactory sf;
	
    @Override
    public void addCountry(Country country) {
        Session session = sf.getCurrentSession();
        session.save(country);
    }

    @Override
    public List<Country> getAllCountries() {
        Session session = getSf().getCurrentSession();
        String requete = "SELECT a FROM Country a";
        Query hql = session.createQuery(requete);
        List<Country> resultat = hql.list();
        return resultat;
    }

    @Override
    public List<Country> getAllCountryByPlanet(int IdPlanet) {
        Session session = sf.getCurrentSession();
        String requete = "SELECT p FROM Country p WHERE p.planet.idPlanet = :paramId";
        Query hql = session.createQuery(requete);
        hql.setParameter("paramId", IdPlanet);
        List<Country> resultat = hql.list();
        return resultat;
    }
    
	public SessionFactory getSf() {
		return sf;
	}
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
}
