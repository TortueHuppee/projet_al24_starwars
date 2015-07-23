package manufacture.facade.nra;

import org.springframework.beans.factory.BeanFactory;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.business.nra.IBusNra;
import manufacture.entity.user.User;
import manufacture.facade.util.ClassPathLoader;
import manufacture.ifacade.nra.IFacNra;


@Service
public class FacNra implements IFacNra {

	@Autowired
	IBusNra proxyBus;
	
	@Override
	public User getUserByLoginAndPassword(String login, String password) {
		return proxyBus.getUser(login, password);
	}

	public void setProxyBus(IBusNra proxyBus) {
		this.proxyBus = proxyBus;
	}

}
