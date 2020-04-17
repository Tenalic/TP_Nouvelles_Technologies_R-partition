package org.test2.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.test2.client.Client;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;


//PARTI JSON

public class Main {

	public static void main(String[] args) throws FileNotFoundException, Exception {
		// TODO Auto-generated method stub
		 

		ArrayList<Client> clientList=new ArrayList<Client>();
		
		try {
		    //on recup les variables stocker dans le json
			
			 JSONParser parser = new JSONParser();
		        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("C:\\Users\\Larcy\\Desktop\\TpNTR\\test.json"));
			
			//recuperation de chaque client stocker dans le fichier json
		 
		        for (Object o : jsonArray) {
		            JSONObject client = (JSONObject) o;
		            
		            int id = ((Number) client.get("id")).intValue();
		            String name = (String) client.get("name");
		            double solde = ((Number) client.get("solde")).doubleValue();
		            
		            Client c = new Client(id,name);
		            c.createAccount(solde);
		            clientList.add(c);
		            
		           

		        }
		    
		   
		    
		}
		catch (Exception e) {e.printStackTrace();}
		
		
 System.out.println(clientList.get(0).getNameClient());
		
		
		//remplacer le json actuelle avec en rajoutant le nouveau solde et la liste des opération.
	
		//arriver a mettre jojo dans le site web 
		//savoir comment mettre php dans le html 
		//code en brut un client en json fait
		//modifier les valeurs json
		
 
 //web service
 //jersey
		
	}	

}