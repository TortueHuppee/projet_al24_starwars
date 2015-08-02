package manufacture.ifacade.user;

import manufacture.entity.user.Address;
import manufacture.entity.user.User;

public interface IInscription {

	//V�rification des identifiants
	boolean loginAlreadyExisting(String login);
	
	boolean emailAlreadyExisting(String email);
	
	//Cr�ation
	User createAccount(User user);

}
