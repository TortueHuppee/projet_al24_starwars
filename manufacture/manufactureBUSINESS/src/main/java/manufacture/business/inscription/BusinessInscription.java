package manufacture.business.inscription;

import java.util.List;

import manufacture.entity.user.Address;
import manufacture.entity.user.User;
import manufacture.ibusiness.user.IBusinessInscription;
import manufacture.idao.user.IDaoUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessInscription implements IBusinessInscription {

    IDaoUser proxyUser;

    @Override
    public User createAccount(User user) {
        return proxyUser.openAccount(user);
    }

    @Override
    public Address createAdress(Address adress) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean loginAlreadyExisting(String login) {
        List<User> userFound = proxyUser.getUserByLogin(login);
        return userFound.size() == 0 ? false:true;
    }

    @Override
    public boolean userExists(User user) {
        List<User> userFound = proxyUser.getUserByUserName(user.getUserName());
        return userFound.size() == 0 ? false:true;
    }

    @Override
    public boolean emailAlreadyExisting(String email) {
        List<User> userFound = proxyUser.getUserByEmail(email);
        return userFound.size() == 0 ? false:true;
    }

    @Autowired
    public void setProxyUser(IDaoUser proxyUser) {
        this.proxyUser = proxyUser;
    }
}
