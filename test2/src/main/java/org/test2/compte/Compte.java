package org.test2.compte;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;


@WebService
public class Compte implements ICompte{
	
	private double solde;
	private ArrayList<String> operationList=new ArrayList<String>();  

	public Compte() {
		this.solde = 0;
	}

	public double getSolde() {
		return solde;
	}

	public void débit(double debit) {
		this.solde =this.solde+ debit;
		this.operationList.add("un débit de "+debit+" € a été fait, soit "+solde  + " € sur votre compte.");
	}
	
	@WebMethod
	public String virementAutreCompte(Compte c, double somme)
	{
		try {
		if (somme<this.solde)
		{
			this.solde=this.solde-somme;
			c.solde = c.solde+somme;
			this.operationList.add("un virement de " + solde +" à été fait");
		}
		return "virement réussi";
		}
		catch(Exception e)
		{
			return "Solde insuffisant";
		}
	}
	
	

}
