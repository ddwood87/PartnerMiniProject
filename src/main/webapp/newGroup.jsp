<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create a new Group</title>
</head>
<body>
	<form action="createNewGroupServlet" method="post">
		Players:<br /> <select name="allPlayersToAdd" multiple size="6">
		
			<c:forEach items="${requestScope.allPlayers}" var="currentplayer">
				<option value="${currentplayer.id}">${currentplayer.fname} ${currentplayer.lname}</option>
			</c:forEach>
			
		</select> <br /> <input type="submit" value="Create group">
	</form>
	<p><a href="index.html">Add a new player</a></p>
	<p><a href="listPlayersServlet">Players list</a></p>
	<p><a href="listGroupsServlet">Groups list</a></p>
	<p><a href="addPlayersForGroupServlet">Create a new group</a></p>
</body>
</html>