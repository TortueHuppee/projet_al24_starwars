package manufacture.business.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import manufacture.entity.user.User;
import manufacture.ibusiness.user.IBusinessConnection;
import manufacture.idao.user.IDaoAdress;
import manufacture.idao.user.IDaoUser;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessConnection implements IBusinessConnection {

	private static Logger log = Logger.getLogger(BusinessConnection.class);
	
	private IDaoUser proxyUser;
	
	private IDaoAdress proxyAddress;
	
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
	public User logUser(User user) {
		user.setPassword(sha256(user.getPassword()));
		return proxyUser.getUserLogin(user);
	}
	
	public static String sha256(String input) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA-256");
			byte[] result = mDigest.digest(input.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();	
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return input;
		}
	}
	
	@Override
	public User getUserByEmail(String email) {
		List<User> listeUser = proxyUser.getUserByEmail(email);
		return listeUser.get(0);
	}
	
	public IDaoUser getProxyUser() {
		return proxyUser;
	}
	
	@Autowired
	public void setProxyUser(IDaoUser proxyUser) {
		this.proxyUser = proxyUser;
	}
	
	public Logger getLog() {
		return log;
	}
	
	public void setLog(Logger log) {
		BusinessConnection.log = log;
	}
	
	public IDaoAdress getProxyAddress() {
		return proxyAddress;
	}
	
	@Autowired
	public void setProxyAddress(IDaoAdress proxyAddress) {
		this.proxyAddress = proxyAddress;
	}
}
