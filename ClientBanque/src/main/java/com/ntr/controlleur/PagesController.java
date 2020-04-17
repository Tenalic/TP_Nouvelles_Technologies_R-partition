package com.ntr.controlleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntr.beans.Personne;
import com.ntr.beans.Transaction;
import com.ntr.ws.CallWebService;

@Controller
public class PagesController {

	private CallWebService callWebService = new CallWebService();

	@GetMapping({ "/", "/connection" })
	public String connectionGet() {
		return "Connection";
	}

	@GetMapping("/compte")
	public String compteGet() {
		return "Moncompte";
	}

	@PostMapping("/compte")
	public String connectionPost(@RequestParam(defaultValue = "0") int numCompte,
			@RequestParam(defaultValue = " ") String mdp) {
		if (callWebService.connection(numCompte, mdp) == true) {
			return ("Moncompte");
		}
		return ("Connection");
	}

	@GetMapping("/getPersonne")
	@ResponseBody
	public String testGet(@RequestParam(defaultValue = "-9999999") int idPersonne) {
		if (idPersonne != -9999999) {
			Personne personne = callWebService.getPersonneWS(idPersonne);
			if (personne != null) {

				String response = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
						+ "<title>getPersonne</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "	<div>\r\n"
						+ "	Resultat = <div>" + " id : " + personne.getId() + " | nom : " + personne.getNom() + " "
						+ " | solde : " + personne.getSolde() + "</div>\r\n"
						+ "	</div>\r\n <h1> liste des transactions : </h1>\n";
				for (Transaction transaction : personne.getListeTransaction()) {
					if (transaction.getIdDestinataire() != -1) {
						response = response + "<div> origin : " + transaction.getIdOrigine() + " | somme : "
								+ transaction.getSomme() + " | destinataire : " + transaction.getIdDestinataire()
								+ "</div>";
					} else {
						response = response + "<div> origin : " + transaction.getIdOrigine() + " | somme : "
								+ transaction.getSomme() + " |</div>";

					}
				}
				return (response + "	<form action=\"/compte\" method=\"get\">\r\n"
						+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
						+ "</html>");
			} else {
				return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
						+ "<title>Debit</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
						+ "	<h1> id introuvable </h1>" + "	</div>\r\n"
						+ "	<form action=\"/compte\" method=\"get\">\r\n"
						+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
						+ "</html>");
			}
		}
		return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Debit</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "	<h1> id incorecte </h1>"
				+ "	</div>\r\n" + "	<form action=\"/compte\" method=\"get\">\r\n"
				+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n" + "</html>");
	}

	// debit, credit, transaction

	@PostMapping("/debit")
	@ResponseBody
	public String debitPost(@RequestParam(defaultValue = "-9999999") int idPersonne,
			@RequestParam(defaultValue = "0") double somme) {
		if (somme != 0 && idPersonne != -9999999) {
			Personne personneAvantDebit = callWebService.getPersonneWS(idPersonne);
			if (personneAvantDebit != null) {
				Personne personneApresDebit = callWebService.debiterSoldePersonneWS(idPersonne, somme);
				if (personneApresDebit != null) {
					return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
							+ "<title>Debit</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "	<div>\r\n"
							+ "		<h1>>Avant Debit :</h1>\r\n" + "		<div> Id :" + personneAvantDebit.getId()
							+ " | nom : " + personneAvantDebit.getNom() + " | solde : " + personneAvantDebit.getSolde()
							+ "</div>\r\n" + "	</div>\r\n" + "	<div>\r\n" + "		<h1>>Apres Debit :</h1>\r\n"
							+ "		<div> Id :" + personneApresDebit.getId() + " | nom : " + personneApresDebit.getNom()
							+ " | solde : " + personneApresDebit.getSolde() + "</div>\r\n" + "	</div>\r\n"
							+ "	<form action=\"/compte\" method=\"get\">\r\n"
							+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
							+ "</html>");
				} else {
					return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
							+ "<title>Debit</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
							+ "	<h1> Erreur lors de l'appel service </h1>" + "	</div>\r\n"
							+ "	<form action=\"/compte\" method=\"get\">\r\n"
							+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
							+ "</html>");
				}
			} else {
				return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
						+ "<title>Debit</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
						+ "	<h1> Id inconnue </h1>" + "	</div>\r\n" + "	<form action=\"/compte\" method=\"get\">\r\n"
						+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
						+ "</html>");
			}
		} else {
			return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
					+ "<title>Debit</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
					+ "	<h1> Id personne ou somme incorecte </h1>" + "	</div>\r\n"
					+ "	<form action=\"/compte\" method=\"get\">\r\n"
					+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
					+ "</html>");
		}
	}

	@PostMapping("/credit")
	@ResponseBody
	public String creditPost(@RequestParam(defaultValue = "-9999999") int idPersonne,
			@RequestParam(defaultValue = "0") double somme) {
		if (somme != 0 && idPersonne != -9999999) {
			Personne personneAvantCredit = callWebService.getPersonneWS(idPersonne);
			if (personneAvantCredit != null) {
				Personne personneApresCredit = callWebService.creditSoldePersonneWS(idPersonne, somme);
				if (personneApresCredit != null) {
					return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
							+ "<title>Credit</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "	<div>\r\n"
							+ "		<h1>>Avant Credit :</h1>\r\n" + "		<div> Id :" + personneAvantCredit.getId()
							+ " | nom : " + personneAvantCredit.getNom() + " | solde : "
							+ personneAvantCredit.getSolde() + "</div>\r\n" + "	</div>\r\n" + "	<div>\r\n"
							+ "		<h1>>Apres Credit :</h1>\r\n" + "		<div> Id :" + personneApresCredit.getId()
							+ " | nom : " + personneApresCredit.getNom() + " | solde : "
							+ personneApresCredit.getSolde() + "</div>\r\n" + "	</div>\r\n"
							+ "	<form action=\"/compte\" method=\"get\">\r\n"
							+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
							+ "</html>");
				} else {
					return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
							+ "<title>Credit</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
							+ "	<h1> Erreur lors de l'appel service </h1>" + "	</div>\r\n"
							+ "	<form action=\"/compte\" method=\"get\">\r\n"
							+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
							+ "</html>");
				}
			} else {
				return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
						+ "<title>Credit</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
						+ "	<h1> Id inconnue </h1>" + "	</div>\r\n" + "	<form action=\"/compte\" method=\"get\">\r\n"
						+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
						+ "</html>");
			}
		} else {
			return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
					+ "<title>Credit</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
					+ "	<h1> Id personne ou somme incorecte </h1>" + "	</div>\r\n"
					+ "	<form action=\"/compte\" method=\"get\">\r\n"
					+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
					+ "</html>");
		}
	}

	@PostMapping("/transaction")
	@ResponseBody
	public String transactionPost(@RequestParam(defaultValue = "-9999999") int idPersonneDebiter,
			@RequestParam(defaultValue = "-9999999") int idPersonneCrediter,
			@RequestParam(defaultValue = "0") double somme) {
		if (somme != 0 && idPersonneDebiter != -9999999 && idPersonneCrediter != -9999999) {
			Personne personneAvantDebit = callWebService.getPersonneWS(idPersonneDebiter);
			Personne personneAvantCredit = callWebService.getPersonneWS(idPersonneCrediter);
			if (personneAvantCredit != null && personneAvantCredit != null) {
				Personne personneApresDebit = callWebService.debiterSoldePersonnesWS(idPersonneDebiter,
						idPersonneCrediter, somme);
				if (personneApresDebit != null) {
					Personne personneApresCredit = callWebService.getPersonneWS(idPersonneCrediter);
					return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
							+ "<title>Transaction</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "	<div>\r\n"
							+ "		<h1>>Avant Debit :</h1>\r\n" + "		<div> Id :" + personneAvantDebit.getId()
							+ " | nom : " + personneAvantDebit.getNom() + " | solde : " + personneAvantDebit.getSolde()
							+ "</div>\r\n" + "	</div>\r\n" + "	<div>\r\n" + "		<h1>>Apres Debit :</h1>\r\n"
							+ "		<div> Id :" + personneApresDebit.getId() + " | nom : " + personneApresDebit.getNom()
							+ " | solde : " + personneApresDebit.getSolde() + "</div>\r\n" + "	</div>\r\n"
							+ "	<div>\r\n" + "		<h1>>Avant Credit :</h1>\r\n" + "		<div> Id :"
							+ personneAvantCredit.getId() + " | nom : " + personneAvantCredit.getNom() + " | solde : "
							+ personneAvantCredit.getSolde() + "</div>\r\n" + "	</div>\r\n" + "	<div>\r\n"
							+ "		<h1>>Apres Credit :</h1>\r\n" + "		<div> Id :" + personneApresCredit.getId()
							+ " | nom : " + personneApresCredit.getNom() + " | solde : "
							+ personneApresCredit.getSolde() + "</div>\r\n" + "	</div>\r\n"
							+ "	<form action=\"/compte\" method=\"get\">\r\n"
							+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
							+ "</html>");
				} else {
					return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
							+ "<title>Transaction</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
							+ "	<h1> Erreur lors de l'appel service </h1>" + "	</div>\r\n"
							+ "	<form action=\"/compte\" method=\"get\">\r\n"
							+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
							+ "</html>");
				}
			} else {
				return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
						+ "<title>Transaction</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
						+ "	<h1> Id inconnue </h1>" + "	</div>\r\n" + "	<form action=\"/compte\" method=\"get\">\r\n"
						+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
						+ "</html>");
			}
		} else {
			return ("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
					+ "<title>Transaction</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
					+ "	<h1> Id personne ou somme incorecte </h1>" + "	</div>\r\n"
					+ "	<form action=\"/compte\" method=\"get\">\r\n"
					+ "		<button type=\"submit\">Retour</button>\r\n" + "	</form>\r\n" + "</body>\r\n"
					+ "</html>");
		}
	}

}
