package org.test2.client;

import org.test2.compte.Compte;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Client implements IClient{
	
	protected String nameClient;
	protected Compte compte = new Compte();
	protected int id;
	
	
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	
	public Client(int id,String nameClient) {
		super();
		this.id = id;
		this.nameClient = nameClient;	
	}

	
	public void createAccount(double solde)
	{
		compte.débit(solde);
		
	}
	
}
