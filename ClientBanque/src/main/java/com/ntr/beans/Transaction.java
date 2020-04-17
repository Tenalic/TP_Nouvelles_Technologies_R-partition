package com.ntr.beans;

public class Transaction {
	
	private int idOrigine;
	
	private double somme;
	
	private int idDestinataire;

	
	
	public Transaction() {
		super();
		this.idOrigine = -1;
		this.somme = -1;
		this.idDestinataire = -1;
	}

	public Transaction(int idOrigine, double somme, int idDestinataire) {
		super();
		this.idOrigine = idOrigine;
		this.somme = somme;
		this.idDestinataire = idDestinataire;
	}

	public int getIdOrigine() {
		return idOrigine;
	}

	public void setIdOrigine(int idOrigine) {
		this.idOrigine = idOrigine;
	}

	public double getSomme() {
		return somme;
	}

	public void setSomme(double somme) {
		this.somme = somme;
	}

	public int getIdDestinataire() {
		return idDestinataire;
	}

	public void setIdDestinataire(int idDestinataire) {
		this.idDestinataire = idDestinataire;
	}
	
	

}
