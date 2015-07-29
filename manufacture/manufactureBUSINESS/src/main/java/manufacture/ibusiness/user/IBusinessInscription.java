package manufacture.ibusiness.user;

import manufacture.entity.user.Administrator;
import manufacture.entity.user.Address;
import manufacture.entity.user.ProfessionnalCustomer;
import manufacture.entity.user.Artisan;
import manufacture.entity.user.SpecificCustomer;
import manufacture.entity.user.User; 

public interface IBusinessInscription {

	//Vérification des identifiants
	boolean loginAlreadyExisting(String login);
	
	boolean emailAlreadyExisting(String email);
	
	//Création
	Address createAdress(Address adress);

	boolean userExists(User user);
	

	User createAccount(User user);
	
	User createAccount(Administrator admin);
    
    User createAccount(Artisan artisan);
    
    User createAccount(ProfessionnalCustomer professionalCustomer);
    
    User createAccount(SpecificCustomer specificCustomer);
}
