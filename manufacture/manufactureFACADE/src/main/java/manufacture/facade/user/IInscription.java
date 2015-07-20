package manufacture.facade.user;

import manufacture.entity.user.Adress;
import manufacture.entity.user.User;

public interface IInscription {

	//V�rification des identifiants
	boolean loginAlreadyExisting(String login);
	
	boolean emailAlreadyExisting(String email);
	
	//Cr�ation
	void createAccount(User user, Adress adress);
}
