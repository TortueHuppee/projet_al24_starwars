package manufacture.ifacade.nra;

import manufacture.entity.user.User;

public interface IFacNra {
public User getUserByLoginAndPassword(String login, String password);
}
