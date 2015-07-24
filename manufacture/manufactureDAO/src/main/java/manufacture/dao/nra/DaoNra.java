package manufacture.dao.nra;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manufacture.entity.user.User;


@Service
@Transactional
public class DaoNra implements IDaoNra {

	@Override
	public User getUser(String l, String p) {
		System.out.println("Tu est dans le DAO mon Grand");
		System.out.println("Dommage qu'il ne soit pas fonctionnel, surtout qu'un DAO User existe deja");
		return new User();
	}

}
