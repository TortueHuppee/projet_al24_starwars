package manufacture.facade.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.entity.user.User;
import manufacture.ibusiness.user.IBusinessInscription;
import manufacture.ifacade.user.IInscription;

@Service
public class Inscription implements IInscription {

	private IBusinessInscription proxyInscription;
	
	@Override
	public User createAccount(User user) {
		if(!proxyInscription.userExists(user)){
			proxyInscription.createAccount(user);
		}else{
			user.setIdUser(null);
		}
		return user;
	}

	@Override
	public boolean loginAlreadyExisting(String login) {
		return proxyInscription.loginAlreadyExisting(login);
	}

	@Override
	public boolean emailAlreadyExisting(String email) {
		return proxyInscription.emailAlreadyExisting(email);
	}

	public IBusinessInscription getProxyInscription() {
		return proxyInscription;
	}

	@Autowired
	public void setProxyInscription(IBusinessInscription proxyInscription) {
		this.proxyInscription = proxyInscription;
	}
}
