<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-15">
<title>Mon compte</title>
</head>
<body>
	<div>
		Vous consultez le compte numero 
		<c:out value="${ utilisateur.numCompte }" default="?"></c:out>
	</div>
</body>
</html>