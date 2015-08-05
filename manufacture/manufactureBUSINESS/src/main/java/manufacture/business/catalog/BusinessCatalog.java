package manufacture.business.catalog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import manufacture.business.util.ClassPathLoader;
import manufacture.entity.product.ArtisanProduct;
import manufacture.entity.product.Category;
import manufacture.entity.product.Color;
import manufacture.entity.product.Constructor;
import manufacture.entity.product.ConstructorProduct;
import manufacture.entity.product.Material;
import manufacture.entity.product.Product;
import manufacture.entity.product.ProductRef;
import manufacture.entity.product.SpaceshipProduct;
import manufacture.entity.product.SpaceshipRef;
import manufacture.entity.product.UsedProduct;
import manufacture.ibusiness.catalog.IBusinessCatalog;
import manufacture.idao.product.IDaoCategory;
import manufacture.idao.product.IDaoColor;
import manufacture.idao.product.IDaoConstructor;
import manufacture.idao.product.IDaoMaterial;
import manufacture.idao.product.IDaoProduct;
import manufacture.idao.product.IDaoProductRef;
import manufacture.idao.product.IDaoSpaceShipRef;

@Service
public class BusinessCatalog implements IBusinessCatalog {

	private IDaoColor proxyColor ;
	private IDaoCategory proxyCategory;
	private IDaoConstructor proxyConstructor;
	private IDaoMaterial proxyMaterial;
	private IDaoProduct proxyProduct;
	private IDaoProductRef proxyProductRef;
	private IDaoSpaceShipRef proxySpaceShip;
	
	@Override
	public List<ProductRef> getAllProductRef() {
		return proxyProductRef.getAllProductRef();
	}

	@Override
	public List<ProductRef> getAllConstructorProductRef() {
		return proxyProductRef.getAllConstructorProductRef();
	}

	@Override
	public List<ProductRef> getAllUsedProductRef() {
		return proxyProductRef.getAllUsedProductRef();
	}

	@Override
	public List<ProductRef> getAllArtisanProductRef() {
		return proxyProductRef.getAllArtisanProductRef();
	}

	@Override
	public List<ProductRef> getProductRefByName(String name) {
		return proxyProductRef.getProductRefByName(name);
	}

	@Override
	public List<ProductRef> getConstructorProductRefBySpaceShip(
			SpaceshipRef spaceShipRef) {
		return proxyProductRef.getConstructorProductRefBySpaceShip(spaceShipRef);
	}

	@Override
	public List<Category> getAllCategory() {
		return proxyCategory.getAllCategory();
	}

	@Override
	public List<Color> getAllColor() {
		return proxyColor.getAllColor();
	}

	@Override
	public List<SpaceshipRef> getAllSpaceShipRef() {
		return proxySpaceShip.getAllSpaceshipRef();
	}

	@Override
	public List<Material> getAllMaterial() {
		return proxyMaterial.getAllMaterial();
	}

	@Override
	public List<Constructor> getAllConstructor() {
		return proxyConstructor.getAllConstructor();
	}

	@Override
	public List<ConstructorProduct> getAllProductByProductRef(int idProducRef) {
		return proxyProduct.getAllProductByProductRef(idProducRef);
	}
	
	@Override
	public List<Product> getAllProduct() {
		return proxyProduct.getAllProduct();
	}

	@Override
	public List<ConstructorProduct> getAllConstructorProduct() {
		//Récupère tous les produits sans exceptions
		//return proxyProduct.getAllConstructorProduct();
		
		//Récupère seulement les produits en stock
		List<ConstructorProduct> listeComplete = proxyProduct.getAllConstructorProduct();
		List<ConstructorProduct> listeProduitsEnStock = new ArrayList<ConstructorProduct>();
		for (ConstructorProduct product : listeComplete)
		{
			if (product.getStock() != 0)
			{
				listeProduitsEnStock.add(product);
			}
		}
		return listeProduitsEnStock;
	}
	
	@Override
	public List<SpaceshipProduct> getSpaceShipProductByProduct(
			ProductRef productRef) {
		return proxyProductRef.getSpaceShipProductByProduct(productRef);
	}
	
	@Override
	public ProductRef getProductRefById(int idProductRef) {
		return proxyProductRef.getProductRefById(idProductRef);
	}
	
	public IDaoColor getProxyColor() {
		return proxyColor;
	}
	

    @Override
    public List<ArtisanProduct> getAllArtisanProduct() {
      //Récupère seulement les produits en stock
        List<ArtisanProduct> listeComplete = proxyProduct.getAllArtisanProduct();
        List<ArtisanProduct> listeProduitsEnStock = new ArrayList<ArtisanProduct>();
        for (ArtisanProduct product : listeComplete)
        {
            if (product.getStock() != 0)
            {
                listeProduitsEnStock.add(product);
            }
        }
        return listeProduitsEnStock;
    }

    @Override
    public List<UsedProduct> getAllUsedProduct() {
      //Récupère seulement les produits en stock
        List<UsedProduct> listeComplete = proxyProduct.getAllUsedProduct();
        List<UsedProduct> listeProduitsEnStock = new ArrayList<UsedProduct>();
        for (UsedProduct product : listeComplete)
        {
            if (product.getStock() != 0)
            {
                listeProduitsEnStock.add(product);
            }
        }
        return listeProduitsEnStock;
    }
    
    //Proxy

	@Autowired
	public void setProxyColor(IDaoColor proxyColor) {
		this.proxyColor = proxyColor;
	}

	public IDaoCategory getProxyCategory() {
		return proxyCategory;
	}
	
	@Autowired
	public void setProxyCategory(IDaoCategory proxyCategory) {
		this.proxyCategory = proxyCategory;
	}

	public IDaoConstructor getProxyConstructor() {
		return proxyConstructor;
	}
	
	@Autowired
	public void setProxyConstructor(IDaoConstructor proxyConstructor) {
		this.proxyConstructor = proxyConstructor;
	}

	public IDaoMaterial getProxyMaterial() {
		return proxyMaterial;
	}
	
	@Autowired
	public void setProxyMaterial(IDaoMaterial proxyMaterial) {
		this.proxyMaterial = proxyMaterial;
	}
	
	public IDaoProduct getProxyProduct() {
		return proxyProduct;
	}
	
	@Autowired
	public void setProxyProduct(IDaoProduct proxyProduct) {
		this.proxyProduct = proxyProduct;
	}

	public IDaoProductRef getProxyProductRef() {
		return proxyProductRef;
	}

	@Autowired
	public void setProxyProductRef(IDaoProductRef proxyProductRef) {
		this.proxyProductRef = proxyProductRef;
	}

	public IDaoSpaceShipRef getProxySpaceShip() {
		return proxySpaceShip;
	}

	@Autowired
	public void setProxySpaceShip(IDaoSpaceShipRef proxySpaceShip) {
		this.proxySpaceShip = proxySpaceShip;
	}

}
