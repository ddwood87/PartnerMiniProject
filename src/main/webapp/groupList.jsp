<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of groups</title>
</head>
<body>
	<form method="post" action="groupNavigationServlet">
		
			<c:forEach items="${requestScope.allGroups}" var="currentgroup">
				<h3><input type ="radio" name ="id" value = "${currentgroup.id}">Group Id: ${currentgroup.id}</h3>
				<table>
					<tr>
						<td>Player Id:</td>
						<td>Player name:</td>
						<td>Player user name:</td>
					</tr>
					
					<c:forEach var="currentplayer" items="${currentgroup.players}">
					
					<tr>
						<td>${currentplayer.id}</td>
						<td>${currentplayer.fname} ${currentplayer.lname}</td>
						<td>${currentplayer.username}</td>
					</tr>
				
					</c:forEach>
					
				</table>
			</c:forEach>
			<input type="submit" value="edit" name="action"> 
			<input type="submit" value="delete" name="action"> 
	</form>
	<p><a href="index.html">Add a new player</a></p>
	<p><a href="listPlayersServlet">Players list</a></p>
	<p><a href="listGroupsServlet">Groups list</a></p>
	<p><a href="addPlayersForGroupServlet">Create a new group</a></p>
</body>
</html>
