package banque.bdd;

import java.util.HashMap;
import java.util.Map;

import banque.services.User;

public class BDD {
	
	private Map<Integer, User> mapUser;

	private BDD() {
		mapUser = new HashMap<Integer, User>();
		this.mapUser.put(1, new User(1,"cocquebert", 7000));
		this.mapUser.put(2, new User(2, "larcy", 4000));
		this.mapUser.put(3, new User(3, "naruto", 11000));
	}
	
	private static BDD INSTANCE = new BDD();
	
	public static BDD getInstance() {
		return INSTANCE;
	}

	public Map<Integer, User> getMapUser() {
		return mapUser;
	}
	
}
