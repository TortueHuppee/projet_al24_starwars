package manufacture.facade.catalog;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipRef;
import manufacture.ibusiness.catalog.IBusinessCatalog;
import manufacture.idao.product.IDaoColor;
import manufacture.ifacade.catalog.ICatalog;

@Service
public class Catalog implements ICatalog {

	BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springBusiness.xml");
	IBusinessCatalog proxyColor = (IBusinessCatalog) bf.getBean(IBusinessCatalog.class);
	
	@Override
	public List<ProductRef> getAllProductRef() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductRef> getAllConstructorProductRef() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductRef> getAllUsedProductRef() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductRef> getAllArtisanProductRef() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductRef> getProductRefByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductRef> getConstructorProductRefBySpaceShip(
			SpaceshipRef spaceShipRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Color> getAllColor() {
		return proxyColor.getAllColor();
	}

	@Override
	public List<SpaceshipRef> getAllSpaceShipRef() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Material> getAllMaterial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Constructor> getAllConstructor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProductByProductRef(int idProducRef) {
		// TODO Auto-generated method stub
		return null;
	}

	public IBusinessCatalog getProxyColor() {
		return proxyColor;
	}

	@Autowired
	public void setProxyColor(IBusinessCatalog proxyColor) {
		this.proxyColor = proxyColor;
	}
}
