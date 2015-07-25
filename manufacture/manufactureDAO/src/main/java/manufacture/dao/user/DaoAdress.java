package manufacture.dao.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.dao.product.DaoColor;
import manufacture.entity.product.Color;
import manufacture.entity.user.Address;
import manufacture.entity.user.City;
import manufacture.entity.user.Country;
import manufacture.entity.user.Planet;
import manufacture.idao.dataloading.IDaoCity;
import manufacture.idao.user.IDaoAdress;

@Transactional
@Service
public class DaoAdress implements IDaoAdress {
	
	private Logger log = Logger.getLogger(DaoColor.class);
	private SessionFactory sf;
	
	public List<City> getAllCities(){
		Session session = getSf().getCurrentSession();
		String requete = "FROM City a";
		Query hql = session.createQuery(requete);
		List<City> resultat = hql.list();
		return resultat;
	}
	
	@Override
	public void createAdress(Address adress) {
		Session session = sf.getCurrentSession();
		session.save(adress);	
	}

	@Override
	public List<Planet> getAllPlanet() {
		Session session = getSf().getCurrentSession();
		String requete = "FROM Planet a";
		Query hql = session.createQuery(requete);
		List<Planet> resultat = hql.list();
		return resultat;
	}

	@Override
	public List<Country> getAllCountryByPlanet(int idPlanet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getAllCityByCountry(int idCountry) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void addCity(City city) {
		Session session = sf.getCurrentSession();
		session.save(city);
	}

	@Override
	public void addCountry(Country country) {
		Session session = sf.getCurrentSession();
		session.save(country);
	}

	@Override
	public void addPlanet(Planet planet) {
		Session session = sf.getCurrentSession();
		session.save(planet);
	}

	@Override
	public List<Country> getAllCountries() {
		Session session = getSf().getCurrentSession();
		String requete = "FROM Country a";
		Query hql = session.createQuery(requete);
		List<Country> resultat = hql.list();
		return resultat;
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
