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
import manufacture.idao.dataloading.IDaoCity;

@Transactional
@Service
public class DaoCity implements IDaoCity {

    @Autowired
    private SessionFactory sf;

    @Override
    public void addCity(City city) {
        Session session = sf.getCurrentSession();
        session.save(city);
    }
    
    @Override
    public List<City> getAllCities(){
        Session session = getSf().getCurrentSession();
        String requete = "FROM City a";
        Query hql = session.createQuery(requete);
        List<City> resultat = hql.list();
        return resultat;
    }

    @Override
    public List<City> getAllCityByCountry(int idCountry) {
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
