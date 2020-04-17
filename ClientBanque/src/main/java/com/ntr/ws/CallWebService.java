package com.ntr.ws;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import org.springframework.web.client.RestTemplate;

import com.ntr.beans.Personne;

public class CallWebService {

	public boolean connection(int numCompte, String mdp) {
		if (numCompte == 123 && "admin".equals(mdp)) {
			return true;
		}
		return false;
	}

	public Personne getPersonneWS(int id) {
		RestTemplate restTemplate = new RestTemplate();
		Personne personne = restTemplate.getForObject("http://localhost:8090/RestServer/api/users/" + id,
				Personne.class);
		return personne;
	}

	public Personne creditSoldePersonneWS(int id, double somme) {

		Personne personne = null;

		try {
			String URL = "http://localhost:8090/RestServer/api/users/credit/" + id + "&" + somme;

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL);

			// normalize XML response
			doc.getDocumentElement().normalize();

			// read course details first
			personne = new Personne(Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent()),
					doc.getElementsByTagName("nom").item(0).getTextContent(),
					Double.parseDouble(doc.getElementsByTagName("solde").item(0).getTextContent()));
		} catch (ParserConfigurationException e) {

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personne;
	}

	public Personne debiterSoldePersonneWS(int id, double somme) {
		Personne personne = null;

		try {
			String URL = "http://localhost:8090/RestServer/api/users/debit/" + id + "&" + somme;

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL);

			// normalize XML response
			doc.getDocumentElement().normalize();

			// read course details first
			personne = new Personne(Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent()),
					doc.getElementsByTagName("nom").item(0).getTextContent(),
					Double.parseDouble(doc.getElementsByTagName("solde").item(0).getTextContent()));
		} catch (ParserConfigurationException e) {

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personne;
	}

	public Personne debiterSoldePersonnesWS(int idDebit, int idCredit, double somme) {
		Personne personne = null;

		try {
			String URL = "http://localhost:8090/RestServer/api/users/transaction/" + idDebit + "&" + idCredit + "&" + somme;

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL);

			// normalize XML response
			doc.getDocumentElement().normalize();

			// read course details first
			personne = new Personne(Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent()),
					doc.getElementsByTagName("nom").item(0).getTextContent(),
					Double.parseDouble(doc.getElementsByTagName("solde").item(0).getTextContent()));
		} catch (ParserConfigurationException e) {

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personne;
	}

}
