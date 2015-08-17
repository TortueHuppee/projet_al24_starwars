package manufacture.facade.advert;

import manufacture.entity.product.Product;
import manufacture.facade.cart.GestionCart;
import manufacture.ibusiness.advert.IBusinessAdvert;
import manufacture.ibusiness.catalog.IBusinessCatalog;
import manufacture.ifacade.advert.IAdvert;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Advert implements IAdvert {

	@Autowired
	private IBusinessCatalog proxyCatalog;
	
	@Autowired
	private IBusinessAdvert proxyAdvert;
	
	private static Logger log = Logger.getLogger(GestionCart.class);
  
	@Override
	public void createAdvert(Product product) {
		proxyAdvert.createAdvert(product);
	}
	
	@Autowired
	public void setProxyCatalog(IBusinessCatalog proxyCatalog) {
		this.proxyCatalog = proxyCatalog;
	}

	public IBusinessAdvert getProxyAdvert() {
		return proxyAdvert;
	}
	
	@Autowired
	public void setProxyAdvert(IBusinessAdvert proxyAdvert) {
		this.proxyAdvert = proxyAdvert;
	}

	public IBusinessCatalog getProxyCatalog() {
		return proxyCatalog;
	}

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger paramLog) {
        log = paramLog;
    }
}
