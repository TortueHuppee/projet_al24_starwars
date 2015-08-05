package manufacture.business.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import manufacture.entity.user.Address;
import manufacture.entity.user.User;
import manufacture.ibusiness.user.IBusinessInscription;
import manufacture.idao.user.IDaoUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessInscription implements IBusinessInscription {

    private IDaoUser proxyUser;

    @Override
    public User createAccount(User user) {
    	user.setPassword(sha256(user.getPassword()));
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
    
    public static String sha256(String input) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA-256");
			byte[] result = mDigest.digest(input.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
				System.out.println(sb.toString());
			}
			return sb.toString();	
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return input;
		}
	}

    @Autowired
    public void setProxyUser(IDaoUser proxyUser) {
        this.proxyUser = proxyUser;
    }
}
