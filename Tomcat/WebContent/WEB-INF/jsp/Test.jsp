<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-15">
<title>Page de test</title>
</head>
<body>
	<p>test</p>
	<p>${ !empty personne.nom ? personne2.nom : "Rien a afficher" }</p>
	<c:out value="<p>Je suis un 'paragraphe'.</p>" />
</body>
</html>