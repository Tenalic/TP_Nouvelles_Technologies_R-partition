package banque.main;

import banque.bdd.BDD;
import banque.services.User;
import banque.services.UserService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserService userService = new UserService();
		User user = BDD.getInstance().getMapUser().get(1);
		System.out.println("Avant : " + user.getSolde());
		user = userService.creditUser(1, 8000);
		System.out.print("après : " + user.getSolde());
	}

}
