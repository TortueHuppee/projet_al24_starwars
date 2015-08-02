package manufacture.ibusiness.user;

import manufacture.entity.user.Address;
import manufacture.entity.user.User;

public interface IBusinessInscription {

	//Vérification des identifiants
	boolean loginAlreadyExisting(String login);
	
	boolean emailAlreadyExisting(String email);
	
	//Création
	Address createAdress(Address adress);

	boolean userExists(User user);

	User createAccount(User user);
	
}
