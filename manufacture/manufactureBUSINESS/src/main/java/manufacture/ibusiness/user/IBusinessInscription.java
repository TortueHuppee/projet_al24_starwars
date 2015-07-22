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
	void createAccount(User user, Address adress);
	
	Administrator createAdministrator(Administrator admin);
	
	Artisan createArtisan(Artisan artisan);
	
	ProfessionnalCustomer createProfessionalCustomer(ProfessionnalCustomer professionalCustomer);
	
	SpecificCustomer createSpecificCustomer(SpecificCustomer specificCustomer);
	
	Address createAdress(Address adress);

	boolean userExists(User user);

	User createAccount(User user);
}
