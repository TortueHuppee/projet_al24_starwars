package manufacture.ibusiness.user;

import manufacture.entity.user.Administrator;
import manufacture.entity.user.Address;
import manufacture.entity.user.ProfessionnalCustomer;
import manufacture.entity.user.Artisan;
import manufacture.entity.user.SpecificCustomer;
import manufacture.entity.user.User;

public interface IBusinessInscription {

	//V�rification des identifiants
	boolean loginAlreadyExisting(String login);
	
	boolean emailAlreadyExisting(String email);
	
	//Cr�ation
	void createAccount(User user, Address adress);
	
	void createAdministrator(Administrator admin);
	
	void createArtisan(Artisan artisan);
	
	void createProfessionalCustomer(ProfessionnalCustomer professionalCustomer);
	
	void createSpecificCustomer(SpecificCustomer specificCustomer);
	
	void createAdress(Address adress);
}
