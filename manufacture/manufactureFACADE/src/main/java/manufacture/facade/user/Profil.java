package manufacture.facade.user;

import java.util.List;

import manufacture.entity.cart.Cart;
import manufacture.entity.cart.CartProduct;
import manufacture.entity.product.Product;
import manufacture.entity.user.Address;
import manufacture.entity.user.User;
import manufacture.ibusiness.user.IBusinessConnection;
import manufacture.ibusiness.user.IBusinessProfil;
import manufacture.ifacade.user.IProfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Profil implements IProfil{

	private IBusinessConnection proxyConnection;
	
	private IBusinessProfil proxyProfil;
	
	@Override
	public User editUser(User user) {
		return proxyProfil.editUser(user);
	}
	
	@Override
	public List<Address> getAllAdressByUser(User user) {
		return proxyProfil.getAllAdressByUser(user);
	}
	
	@Override
	public void editAddress(Address addresse) {
		proxyProfil.editAddress(addresse);
	}
	
	@Override
	public void saveAddress(Address nouvelleAdresse) {
		proxyProfil.saveAddress(nouvelleAdresse);
	}
	
	@Override
	public List<Product> getProductSendByUser(User user) {
		return proxyProfil.getProductSendByUser(user);
	}

	@Override
	public List<Cart> getCartByUser(User user) {
		return proxyProfil.getCartByUser(user);
	}
	
	@Override
	public List<CartProduct> getCartSendByUser(User user) {
		return proxyProfil.getCartSendByUser(user);
	}
	
	@Override
	public List<Product> getProductNotSendByUser(User user) {
		return proxyProfil.getProductNotSendByUser(user);
	}
	
	@Override
	public List<Address> getBillingAddressByUser(User user) {
		return proxyProfil.getBillingAddressByUser(user);
	}

	@Override
	public List<Address> getDeliveryAddressByUser(User user) {
		return proxyProfil.getDeliveryAddressByUser(user);
	}
	
    @Override
    public List<CartProduct> getCartProductByCart(Cart cart) {
        return proxyProfil.getCartProductByCart(cart);
    }
	
	//Proxy

	public IBusinessConnection getProxyConnection() {
		return proxyConnection;
	}

	@Autowired
	public void setProxyConnection(IBusinessConnection proxyConnection) {
		this.proxyConnection = proxyConnection;
	}

	public IBusinessProfil getProxyProfil() {
		return proxyProfil;
	}

	@Autowired
	public void setProxyProfil(IBusinessProfil proxyProfil) {
		this.proxyProfil = proxyProfil;
	}
}
