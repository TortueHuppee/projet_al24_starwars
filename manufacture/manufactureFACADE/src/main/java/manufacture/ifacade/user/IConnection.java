package manufacture.ifacade.user;

import manufacture.entity.user.User;

public interface IConnection {

    //Oubli des identifiants
	boolean resertPassword(String login);
	
	boolean getLogin(String email);
	
	User getUserByEmail(String email);

	User connectUser(User user);
}
