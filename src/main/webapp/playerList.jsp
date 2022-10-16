<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of players</title>
</head>
<body>
	<form method="post">
		<table>
			<c:forEach items="${requestScope.allPlayers}" var="currentplayer">
				<tr>
					<td>Player Id: ${currentplayer.id}</td>
				</tr>
				<tr>
					<td>Player name: ${currentplayer.fname} ${currentplayer.lname}</td>
				</tr>
				<tr>
					<td>Player user name: ${currentplayer.username}</td>
				</tr>
				<tr>
					<td><br /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<a href="index.html">Add a new player</a>
</body>
</html>