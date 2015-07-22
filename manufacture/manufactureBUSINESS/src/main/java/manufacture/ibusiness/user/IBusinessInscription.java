package manufacture.ibusiness.user;

import manufacture.entity.user.Administrator;
import manufacture.entity.user.Address;
import manufacture.entity.user.ProfessionnalCustomer;
import manufacture.entity.user.Artisan;
import manufacture.entity.user.SpecificCustomer;
import manufacture.entity.user.User;

public interface IBusinessInscription {

	//Création
	User createAccount(User user);
	
	Administrator createAdministrator(Administrator admin);
	
	Artisan createArtisan(Artisan artisan);
	
	ProfessionnalCustomer createProfessionalCustomer(ProfessionnalCustomer professionalCustomer);
	
	SpecificCustomer createSpecificCustomer(SpecificCustomer specificCustomer);
	
	Address createAdress(Address adress);

	boolean userExists(User user);
}
