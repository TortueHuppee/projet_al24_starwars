package manufacture.ibusiness.user;

import java.util.List;

import manufacture.entity.user.Address;
import manufacture.entity.user.User;


public interface IBusinessConnection {

	//Connexion / déconnexion
	User getSignInUser(String login, String password);
	
	void getSignOutUser(User user);
	
	//Oubli des identifiants
	boolean resertPassword(String login);
	
	boolean getLogin(String email);

	User logUser(User user);

}
