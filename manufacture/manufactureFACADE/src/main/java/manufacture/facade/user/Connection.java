package manufacture.facade.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manufacture.business.user.BusinessConnection;
import manufacture.entity.user.User;
import manufacture.ibusiness.user.IBusinessConnection;
import manufacture.ifacade.user.IConnection;

@Service
public class Connection implements IConnection{
	
	private static Logger log = Logger.getLogger(BusinessConnection.class);

	private IBusinessConnection proxyConnection;
	
	@Override
	public boolean resertPassword(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getLogin(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User connectUser(User user) {
		return proxyConnection.logUser(user);
	}

	public IBusinessConnection getProxyConnection() {
		return proxyConnection;
	}

	@Override
	public User getUserByEmail(String email) {
		log.info("*********** getUserByEmail dans facade fonctionne : user = "+ proxyConnection.getUserByEmail(email)+" ***********");
		return proxyConnection.getUserByEmail(email);
	}

	@Autowired
	public void setProxyConnection(IBusinessConnection proxyConnection) {
		this.proxyConnection = proxyConnection;
	}
}
