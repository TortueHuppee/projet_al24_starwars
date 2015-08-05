package manufacture.ifacade.user;

import java.util.List;

import manufacture.entity.user.Address;
import manufacture.entity.user.User;

public interface IConnection {

	//Connexion / d�connexion
	User getSignInUser(String login, String password);
	
	void getSignOutUser(User user);
	
	//Oubli des identifiants
	boolean resertPassword(String login);
	
	boolean getLogin(String email);

	User connectUser(User user);
	
	//Modification des informations / Consultation du profil
	User editUser(User user);
	
	List<Address> getAllAdressByUser(User user);

	void editAddress(Address addresse);

	void saveAddress(Address nouvelleAdresse);
}
