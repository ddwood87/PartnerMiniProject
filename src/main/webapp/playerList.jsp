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
			<tr>
				<td>Player Id:</td>
				<td>Player name:</td>
				<td>Player user name:</td>
			</tr>
			<c:forEach items="${requestScope.allPlayers}" var="currentplayer">
				
			<tr>
				<td>${currentplayer.id}</td>
				<td>${currentplayer.fname} ${currentplayer.lname}</td>
				<td>${currentplayer.username}</td>
			</tr>
			
			</c:forEach>
			
		</table>
	</form>
	<p><a href="index.html">Add a new player</a></p>
	<p><a href="listPlayersServlet">Players list</a></p>
	<p><a href="listGroupsServlet">Groups list</a></p>
	<p><a href="addPlayersForGroupServlet">Create a new group</a></p>
</body>
</html>