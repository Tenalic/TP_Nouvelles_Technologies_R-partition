package banque.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import banque.bdd.BDD;
import banque.services.User;
import banque.services.UserService;

class JunitTest {
	
	@Test
	void testCredit() {
		UserService userService = new UserService();
		double somme = 8000;
		int id = 1;
		User user = BDD.getInstance().getMapUser().get(id);
		double soldeAvant = user.getSolde();
		user = userService.creditUser(id, somme);
		double soldeApres = user.getSolde();
		assertEquals(soldeAvant + somme, soldeApres);
	}

	@Test
	void testCreditImpossible() {
		UserService userService = new UserService();
		double somme = 8000;
		int id = 4;
		User user = userService.creditUser(id, somme);
		assertNull(user);
	}

	@Test
	void testCreditNegatif() {
		UserService userService = new UserService();
		double somme = -8000;
		int id = 1;
		User user = userService.creditUser(id, somme);
		assertNull(user);
	}

	@Test
	void testDebit() {
		UserService userService = new UserService();
		double somme = 10;
		int id = 1;
		User user = BDD.getInstance().getMapUser().get(id);
		double soldeAvant = user.getSolde();
		user = userService.debitUser(id, somme);
		double soldeApres = user.getSolde();
		assertEquals(soldeAvant - somme, soldeApres);
	}

	@Test
	void testDebitImpossible() {
		UserService userService = new UserService();
		double somme = 8000;
		int id = 4;
		User user = userService.debitUser(id, somme);
		assertNull(user);
	}

	@Test
	void testDebitNegatif() {
		UserService userService = new UserService();
		double somme = -8000;
		int id = 1;
		User user = userService.debitUser(id, somme);
		assertNull(user);
	}

	@Test
	void testDebitUsers() {
		UserService userService = new UserService();
		double somme = 10;
		int idDebit = 1;
		int idCredit = 2;
		User userDebit = BDD.getInstance().getMapUser().get(idDebit);
		User userCredit = BDD.getInstance().getMapUser().get(idCredit);
		double soldeAvantDebit = userDebit.getSolde();
		double soldeAvantCredit = userCredit.getSolde();
		userDebit = userService.debitUsersGET(idDebit, idCredit, somme);
		userCredit = BDD.getInstance().getMapUser().get(idCredit);
		double soldeApresdebit = userDebit.getSolde();
		double soldeApresCredit = userCredit.getSolde();
		assertEquals(soldeAvantDebit - somme, soldeApresdebit);
		assertEquals(soldeAvantCredit + somme, soldeApresCredit);
	}
	
	@Test
	void testDebitUsersImpossible() {
		UserService userService = new UserService();
		double somme = 999999999;
		int idDebit = 1;
		int idCredit = 2;
		User userCredit = BDD.getInstance().getMapUser().get(idCredit);
		double soldeAvantCredit = userCredit.getSolde();
		User userDebit = userService.debitUsersGET(idDebit, idCredit, somme);
		userCredit = BDD.getInstance().getMapUser().get(idCredit);
		double soldeApresCredit = userCredit.getSolde();
		assertNull(userDebit);
		assertEquals(soldeAvantCredit, soldeApresCredit);
	}

}
