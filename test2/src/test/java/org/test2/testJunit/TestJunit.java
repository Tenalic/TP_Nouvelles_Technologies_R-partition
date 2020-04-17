package org.test2.testJunit;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import junit.framework.TestCase;



public class TestJunit extends TestCase {

	public void test() throws Exception{

	

	try {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:client", "SA",  "");
		Statement  statement = connexion.createStatement();
		ResultSet client = statement.executeQuery("SELECT * FROM client");
		while(client.next()){
			int idClient = client.getInt("id") ;
			System.out.println(idClient);
		}
		
		//sauvegarde et fermer la BDD
		statement.executeQuery("SHUTDOWN");
		statement.close();
		
		connexion.close();
		
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	/*
	public void testLecture() throws Exception //good
	{
		JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("C:\\Users\\Larcy\\Desktop\\TpNTR\\test.json"));
        
        for (Object o : jsonArray) {
            JSONObject person = (JSONObject) o;

            String strName = (String) person.get("name");
            System.out.println("Name::::" + strName);

           

        }
	}
	*/
	
}
