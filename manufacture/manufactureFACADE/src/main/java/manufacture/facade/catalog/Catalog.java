package manufacture.facade.catalog;

import java.util.List;

<<<<<<< HEAD
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

=======
>>>>>>> 20fe4fcee1a78b8da24ace9cd91c1f86f6d44620
import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipRef;
<<<<<<< HEAD
import manufacture.ibusiness.catalog.IBusinessCatalog;
import manufacture.idao.product.IDaoColor;
import manufacture.ifacade.catalog.ICatalog;

@Service
public class Catalog implements ICatalog {

	BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springBusiness.xml");
=======
import manufacture.facade.util.ClassPathLoader;
import manufacture.ibusiness.catalog.IBusinessCatalog;
import manufacture.ifacade.catalog.ICatalog;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Catalog implements ICatalog {

	BeanFactory bf = ClassPathLoader.getBusinessBeanFactory();
>>>>>>> 20fe4fcee1a78b8da24ace9cd91c1f86f6d44620
	IBusinessCatalog proxyCatalog = (IBusinessCatalog) bf.getBean(IBusinessCatalog.class);
	
	@Override
	public List<ProductRef> getAllProductRef() {
		return proxyCatalog.getAllProductRef();
	}

	@Override
	public List<ProductRef> getAllConstructorProductRef() {
		return proxyCatalog.getAllConstructorProductRef();
	}

	@Override
	public List<ProductRef> getAllUsedProductRef() {
		return proxyCatalog.getAllUsedProductRef();
	}

	@Override
	public List<ProductRef> getAllArtisanProductRef() {
		return proxyCatalog.getAllArtisanProductRef();
	}

	@Override
	public List<ProductRef> getProductRefByName(String name) {
		return proxyCatalog.getProductRefByName(name);
	}

	@Override
	public List<ProductRef> getConstructorProductRefBySpaceShip(
			SpaceshipRef spaceShipRef) {
		return proxyCatalog.getConstructorProductRefBySpaceShip(spaceShipRef);
	}

	@Override
	public List<Category> getAllCategory() {
		return proxyCatalog.getAllCategory();
	}

	@Override
	public List<Color> getAllColor() {
		return proxyCatalog.getAllColor();
	}

	@Override
	public List<SpaceshipRef> getAllSpaceShipRef() {
		return proxyCatalog.getAllSpaceShipRef();
	}

	@Override
	public List<Material> getAllMaterial() {
		return proxyCatalog.getAllMaterial();
	}

	@Override
	public List<Constructor> getAllConstructor() {
		return proxyCatalog.getAllConstructor();
	}

	@Override
	public List<Product> getAllProductByProductRef(int idProducRef) {
		return proxyCatalog.getAllProductByProductRef(idProducRef);
	}

	public IBusinessCatalog getProxyColor() {
		return proxyCatalog;
	}

	@Autowired
	public void setProxyColor(IBusinessCatalog proxyColor) {
		this.proxyCatalog = proxyColor;
	}
}
