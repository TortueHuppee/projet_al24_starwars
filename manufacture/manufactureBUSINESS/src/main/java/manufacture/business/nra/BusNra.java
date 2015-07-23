package manufacture.business.nra;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.dao.nra.IDaoNra;
import manufacture.entity.user.User;

@Service
public class BusNra implements IBusNra {
	@Autowired
	IDaoNra proxyDao;

	@Override
	public User getUser(String l, String p) {
		return proxyDao.getUser(l, p);
	}

	public void setProxyDao(IDaoNra proxyDao) {
		this.proxyDao = proxyDao;
	}

}
