package manufacture.dao.address;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.entity.product.Color;
import manufacture.entity.user.Address;
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.idao.dataloading.IDaoCity;
import manufacture.idao.dataloading.IDaoCountry;

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
    public void createAdress(Address paramAdress) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Country> getAllCountries() {
        Session session = getSf().getCurrentSession();
        String requete = "FROM Country a";
        Query hql = session.createQuery(requete);
        List<Country> resultat = hql.list();
        return resultat;
    }

    @Override
    public List<Country> getAllCountryByPlanet(int paramIdPlanet) {
        // TODO Auto-generated method stub
        return null;
    }
    
	public SessionFactory getSf() {
		return sf;
	}
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
}
