package banque.services;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class User {
	
	private int id;
	
	private String nom;
	
	private double solde;
	
	private ArrayList<Transaction> listeTransaction;
	
	public User() {
		id = -1;
		nom = null;
		solde = -1;
		setListeTransaction(null);
	}
	
	public User(final int id, final String nom, final double solde) {
		this.setId(id);
		this.setNom(nom);
		this.setSolde(solde);
		this.setListeTransaction(new ArrayList<Transaction>());
	}
	
	public User(final int id, final String nom, final double solde, final ArrayList<Transaction> listeTransactions) {
		this.setId(id);
		this.setNom(nom);
		this.setSolde(solde);
		this.setListeTransaction(listeTransactions);
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@XmlElement
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
