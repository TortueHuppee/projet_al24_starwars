package manufacture.facade.catalog;

import java.util.List;

import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.user.User;
import manufacture.ibusiness.catalog.IBusinessCatalog;
import manufacture.ifacade.catalog.ICatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Catalog implements ICatalog {

	@Autowired
	private IBusinessCatalog proxyCatalog;
	
	public void setProxyCatalog(IBusinessCatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}

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

	@Override
	public List<Product> getAllProduct() {
		return proxyCatalog.getAllProduct();
	}

	@Override
	public List<SpaceshipProduct> getSpaceShipProductByProduct(ProductRef productRef) {
		return proxyCatalog.getSpaceShipProductByProduct(productRef);
	}
	
	@Override
	public ProductRef getProductRefById(int idProductRef) {
		return proxyCatalog.getProductRefById(idProductRef);
	}

	@Override
	public List<SpaceshipProduct> getAllSpaceShipProduct() {
		return proxyCatalog.getAllSpaceShipProduct();
	}
	
	@Override
	public List<User> getAllArtisan() {
		return proxyCatalog.getAllArtisan();
	}
	
	@Override
	public List<Product> getAllProductBySpaceShipRef(int idSpaceShipSelected) {
		return proxyCatalog.getAllProductBySpaceShipRef(idSpaceShipSelected);
	}

	public IBusinessCatalog getProxyColor() {
		return proxyCatalog;
	}

	@Autowired
	public void setProxyColor(IBusinessCatalog proxyColor) {
		this.proxyCatalog = proxyColor;
	}
}
