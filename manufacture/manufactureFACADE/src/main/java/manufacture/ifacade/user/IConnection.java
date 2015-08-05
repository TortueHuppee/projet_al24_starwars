package manufacture.ifacade.user;

import java.util.List;

import manufacture.entity.user.Address;
import manufacture.entity.user.User;

public interface IConnection {

	//Connexion / déconnexion
	User getSignInUser(String login, String password);
	
	void getSignOutUser(User user);
	
	//Oubli des identifiants
	boolean resertPassword(String login);
	
	boolean getLogin(String email);

	User connectUser(User user);
}
