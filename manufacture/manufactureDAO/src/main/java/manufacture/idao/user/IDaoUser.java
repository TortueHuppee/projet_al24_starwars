package manufacture.idao.user;

import java.util.List;

import manufacture.entity.user.User;


public interface IDaoUser {

	List<String> getAllLogin();
	
	List<User> getUserByLogin(String login);
	
	List<String> getAllEmail();
	
	List<User> getUserByEmail(String email);
	
	String getPasswordByLogin(String login);
	
	void getPasswordByEmail(String email);
	
	
	User openAccount(User user);	
	
	void closeAccount(User user);
	

	User getUserLogin(User user);

	List<User> getUserByUserName(String userName);

	User editUser(User user);
	

	List<User> getAllArtisan();
}
