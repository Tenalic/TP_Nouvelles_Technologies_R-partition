package com.ntr.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntr.beans.Personne;

public class Test extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Personne personne = new Personne();
		personne.setNom("cocquebert");
		personne.setPrenom("stephane");
		request.setAttribute("personne" , personne);
		String auteur = request.getParameter("auteur");
		request.setAttribute("auteur", auteur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Test.jsp").forward(request, response);
	}

}
