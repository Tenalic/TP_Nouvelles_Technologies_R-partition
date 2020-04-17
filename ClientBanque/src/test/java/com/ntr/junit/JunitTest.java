package com.ntr.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ntr.beans.Personne;
import com.ntr.ws.CallWebService;

class JunitTest {

	@Test
	void getPersonne() {
		CallWebService call = new CallWebService();
		Personne personne = call.getPersonneWS(1);
		assertEquals("cocquebert", personne.getNom());
	}
	
	@Test
	void getPersonne2() {
		CallWebService call = new CallWebService();
		Personne personne = call.getPersonneWS(2);
		assertEquals("larcy", personne.getNom());
	}

	@Test
	void creditSolde() {
		CallWebService call = new CallWebService();
		Personne personneAvant = call.getPersonneWS(1);
		Personne personneAprèt = call.creditSoldePersonneWS(1, 1500);
		assertEquals(personneAvant.getSolde() + 1500, personneAprèt.getSolde());
	}

	@Test
	void debitSolde() {
		CallWebService call = new CallWebService();
		Personne personneAvant = call.getPersonneWS(1);
		Personne personneAprèt = call.debiterSoldePersonneWS(1, 1500);
		assertEquals(personneAvant.getSolde() - 1500, personneAprèt.getSolde());
	}

	@Test
	void debitSoldePersonnes() {
		CallWebService call = new CallWebService();
		double somme = 1500;
		int idDebit = 1;
		int idCredit = 2;
		Personne personnDebiteAvant = call.getPersonneWS(idDebit);
		Personne personneCreditAvant = call.getPersonneWS(idCredit);
		Personne personneDebitAprèt = call.debiterSoldePersonnesWS(idDebit, idCredit, somme);
		Personne personneCreditApres = call.getPersonneWS(idCredit);
		assertEquals(personnDebiteAvant.getSolde() - somme, personneDebitAprèt.getSolde());
		assertEquals(personneCreditAvant.getSolde() + somme, personneCreditApres.getSolde());
	}
	
	@Test
	void debitSoldePersonnesImpossible() {
		CallWebService call = new CallWebService();
		double somme = 999999;
		int idDebit = 1;
		int idCredit = 2;
		Personne personneCreditAvant = call.getPersonneWS(idCredit);
		Personne personneDebitAprèt = call.debiterSoldePersonnesWS(idDebit, idCredit, somme);
		Personne personneCreditApres = call.getPersonneWS(idCredit);
		assertNull(personneDebitAprèt);
		assertEquals(personneCreditAvant.getSolde(), personneCreditApres.getSolde());
	}

}
