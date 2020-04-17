package com.ntr.beans;

import java.util.ArrayList;

public class Personne {

	private int id;

	private String nom;

	private double solde;
	
	private ArrayList<Transaction> listeTransaction;
	
	public Personne() {
		this.setId(-1);
		this.setNom(null);
		this.setSolde(-1);
		this.setListeTransaction(new ArrayList<Transaction>());
	}
	
	public Personne(int id, String nom, double solde) {
		this.setId(id);
		this.setNom(nom);
		this.setSolde(solde);
		this.setListeTransaction(new ArrayList<Transaction>());
	}
	
	public Personne(int id, String nom, double solde, ArrayList<Transaction> listeTransaction) {
		this.setId(id);
		this.setNom(nom);
		this.setSolde(solde);
		this.setListeTransaction(listeTransaction);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public ArrayList<Transaction> getListeTransaction() {
		return listeTransaction;
	}

	public void setListeTransaction(ArrayList<Transaction> listeTransaction) {
		this.listeTransaction = listeTransaction;
	}

}
