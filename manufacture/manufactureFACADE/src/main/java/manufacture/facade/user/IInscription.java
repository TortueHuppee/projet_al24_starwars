package manufacture.facade.user;

import manufacture.entity.user.Adress;
import manufacture.entity.user.User;

public interface IInscription {

	//Vérification des identifiants
	boolean loginAlreadyExisting(String login);
	
	boolean emailAlreadyExisting(String email);
	
	//Création
	void createAccount(User user, Adress adress);
}
