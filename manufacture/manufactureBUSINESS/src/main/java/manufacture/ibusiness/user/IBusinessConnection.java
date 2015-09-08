package manufacture.ibusiness.user;

import manufacture.entity.user.User;


public interface IBusinessConnection {

	//Oubli des identifiants
	boolean resertPassword(String login);
	
	boolean getLogin(String email);
	
	User getUserByEmail (String email);

	User logUser(User user);

}
