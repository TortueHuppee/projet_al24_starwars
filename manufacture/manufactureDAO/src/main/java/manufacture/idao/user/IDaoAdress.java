package manufacture.idao.user;

import java.util.List;

import manufacture.entity.user.Address;
import manufacture.entity.user.User;


public interface IDaoAdress {
	
	void createAddress(Address adress);
	
	void updateAddress(Address address);
	
	void deleteAddress(Address address);

	List<Address> getAllAdressByUser(User user);

	List<Address> getDeliveryAddressByUser(User user);

	List<Address> getBillingAddressByUser(User user);
}
