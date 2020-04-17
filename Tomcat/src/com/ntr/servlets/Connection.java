package com.ntr.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntr.beans.Utilisateur;


public class Connection extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7962514372299177580L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("personne", null);
		String auteur = request.getParameter("auteur");
		request.setAttribute("auteur", auteur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Connection.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setMdp(request.getParameter("mdp"));
		utilisateur.setNumCompte(request.getParameter("numCompte"));

		RequestDispatcher dispatcher;

		if ("123".equals(utilisateur.getNumCompte()) && "aze".equals(utilisateur.getMdp())) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/MonCompte.jsp");
			request.setAttribute("utilisateur", utilisateur);
		} else {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Connection.jsp");
			request.setAttribute("message", "Numero de compte ou mot de passe incorecte");
		}
		
		dispatcher.forward(request, response);
	}

}
