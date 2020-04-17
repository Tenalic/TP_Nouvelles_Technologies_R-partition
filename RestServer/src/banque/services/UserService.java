package banque.services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import banque.bdd.BDD;

// fonctionne sur tomcat
@Path("/users")
public class UserService {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") int id) {
		User user = BDD.getInstance().getMapUser().get(id);
		return user;
	}

	@POST
	@Path("/debit/{id}&{somme}")
	@Produces(MediaType.APPLICATION_XML)
	public User debitUser(@PathParam("id") int id, @PathParam("somme") double somme) {
		if (somme > 0) {
			User user = BDD.getInstance().getMapUser().get(id);
			if (user != null && user.getSolde() - somme >= 0) {
				user.setSolde(user.getSolde() - somme);
				user.getListeTransaction().add(new Transaction(id, -somme));
				BDD.getInstance().getMapUser().put(id, user);
				return user;
			}
		}
		return null;
	}

	@GET
	@Path("/debit/{id}&{somme}")
	@Produces(MediaType.APPLICATION_XML)
	public User debitUserGet(@PathParam("id") int id, @PathParam("somme") double somme) {
		User user = BDD.getInstance().getMapUser().get(id);
		if (user != null && user.getSolde() - somme >= 0) {
			user.setSolde(user.getSolde() - somme);
			user.getListeTransaction().add(new Transaction(id, -somme));
			BDD.getInstance().getMapUser().put(id, user);
			return user;
		}
		return null;
	}

	@POST
	@Path("/credit/{id}&{somme}")
	@Produces(MediaType.APPLICATION_XML)
	public User creditUser(@PathParam("id") int id, @PathParam("somme") double somme) {
		if (somme > 0) {
			User user = BDD.getInstance().getMapUser().get(id);
			if (user != null) {
				user.setSolde(user.getSolde() + somme);
				user.getListeTransaction().add(new Transaction(id, somme));
				BDD.getInstance().getMapUser().put(id, user);
				return user;
			}
		}
		return null;
	}

	@GET
	@Path("/credit/{id}&{somme}")
	@Produces(MediaType.APPLICATION_XML)
	public User creditUserGET(@PathParam("id") int id, @PathParam("somme") double somme) {
		if (somme > 0) {
			User user = BDD.getInstance().getMapUser().get(id);
			if (user != null) {
				user.setSolde(user.getSolde() + somme);
				user.getListeTransaction().add(new Transaction(id, somme));
				BDD.getInstance().getMapUser().put(id, user);
				return user;
			}
		}
		return null;
	}

	@GET
	@Path("/transaction/{idDebit}&{idCredit}&{somme}")
	@Produces(MediaType.APPLICATION_XML)
	public User debitUsersGET(@PathParam("idDebit") int idDebit, @PathParam("idCredit") int idCredit,
			@PathParam("somme") double somme) {
		if (somme > 0) {
			User userDebit = BDD.getInstance().getMapUser().get(idDebit);
			User userCredit = BDD.getInstance().getMapUser().get(idCredit);
			if (userDebit != null && userDebit.getSolde() - somme >= 0 && userCredit != null) {
				userDebit.setSolde(userDebit.getSolde() - somme);
				userDebit.getListeTransaction().add(new Transaction(idDebit, -somme, idCredit));
				BDD.getInstance().getMapUser().put(idDebit, userDebit);
				userCredit.setSolde(userCredit.getSolde() + somme);
				userCredit.getListeTransaction().add(new Transaction(idDebit, somme, idCredit));
				BDD.getInstance().getMapUser().put(idCredit, userCredit);
				return userDebit;
			}
		}
		return null;
	}

	@POST
	@Path("/debit/{idDebit}&{idCredit}&{somme}")
	@Produces(MediaType.APPLICATION_XML)
	public User debitUsersPOST(@PathParam("idDebit") int idDebit, @PathParam("idCredit") int idCredit,
			@PathParam("somme") double somme) {
		if (somme > 0) {
			User userDebit = BDD.getInstance().getMapUser().get(idDebit);
			User userCredit = BDD.getInstance().getMapUser().get(idCredit);
			if (userDebit != null && userDebit.getSolde() - somme >= 0 && userCredit != null) {
				userDebit.setSolde(userDebit.getSolde() - somme);
				userDebit.getListeTransaction().add(new Transaction(idDebit, -somme, idCredit));
				BDD.getInstance().getMapUser().put(idDebit, userDebit);
				userCredit.setSolde(userCredit.getSolde() + somme);
				userDebit.getListeTransaction().add(new Transaction(idCredit, somme, idDebit));
				BDD.getInstance().getMapUser().put(idCredit, userCredit);
				return userDebit;
			}
		}
		return null;
	}

	@GET
	@Path("/listeTransaction/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Transaction> listeTransaction(@PathParam("id") int id) {
		User user = BDD.getInstance().getMapUser().get(id);
		if (user != null) {
			return user.getListeTransaction();
		}
		return null;
	}

}