<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-15">
<title>Connection</title>
</head>
<body>
	<div>
		<c:out value="${ message }" default=""></c:out>
	</div>
	<div>
		<form action="" method="post">
			<H1>Connection</H1>
			<div>
				<label> <input type="text" placeholder="Numero de compte"
					name="numCompte"></label>
			</div>
			<div>
				<label> <input type="password" placeholder="mot de passe"
					name="mdp">
				</label>
			</div>

			<button type="submit">Se connecter</button>
		</form>
	</div>
</body>
</html>