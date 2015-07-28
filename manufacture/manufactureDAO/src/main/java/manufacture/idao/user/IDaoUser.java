package manufacture.idao.user;

import java.util.List;

import manufacture.entity.user.Administrator;
import manufacture.entity.user.Address;
import manufacture.entity.user.Artisan;
import manufacture.entity.user.ProfessionnalCustomer;
import manufacture.entity.user.SpecificCustomer;
import manufacture.entity.user.User;


public interface IDaoUser {

	List<String> getAllLogin();
	
	List<User> getUserByLogin(String login);
	
	List<String> getAllEmail();
	
	List<User> getUserByEmail(String email);
	
	void getPasswordByLogin(String login);
	
	void getPasswordByEmail(String email);
	
	void createAdministrator(Administrator admin);
	
	void createArtisan(Artisan artisan);
	
	void createProfessionalCustomer(ProfessionnalCustomer professionalCustomer);
	
	void createSpecificCustomer(SpecificCustomer specificCustomer);
	
	void closeAccount(User user);

	User openAccount(User user);

	User getUserLogin(User user);

	List<User> getUserByUserName(String userName);
}
