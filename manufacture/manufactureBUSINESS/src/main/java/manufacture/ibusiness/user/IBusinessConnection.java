package manufacture.ibusiness.user;

import manufacture.entity.user.User;


public interface IBusinessConnection {

	//Connexion / déconnexion
	User getSignInUser(String login, String password);
	
	void getSignOutUser(User user);
	
	//Oubli des identifiants
	boolean resertPassword(String login);
	
	boolean getLogin(String email);
}
