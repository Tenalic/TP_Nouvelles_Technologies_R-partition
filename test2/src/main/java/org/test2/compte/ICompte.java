package org.test2.compte;

public interface ICompte {
	
	public String virementAutreCompte(Compte c, double somme);
	
	public void débit(double debit);

}
