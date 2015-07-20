package manufacture.business.user;

import manufacture.entity.user.Administrator;
import manufacture.entity.user.Adress;
import manufacture.entity.user.ProfessionnalCustomer;
import manufacture.entity.user.Artisan;
import manufacture.entity.user.SpecificCustomer;
import manufacture.entity.user.User;

public interface IBusinessInscription {

	//Vérification des identifiants
	boolean loginAlreadyExisting(String login);
	
	boolean emailAlreadyExisting(String email);
	
	//Création
	void createAccount(User user, Adress adress);
	
	void createAdministrator(Administrator admin);
	
	void createArtisan(Artisan artisan);
	
	void createProfessionalCustomer(ProfessionnalCustomer professionalCustomer);
	
	void createSpecificCustomer(SpecificCustomer specificCustomer);
	
	void createAdress(Adress adress);
}
