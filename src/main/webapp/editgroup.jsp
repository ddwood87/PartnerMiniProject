<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit group</title>
</head>
<body>
		<form action="editGroupServlet" method="post">
		<input type="hidden" name="id" value="${groupToEdit.id}"> 
		
		Players:<br /> <select name="allPlayers" multiple size="6">
		
			<c:forEach items="${requestScope.allPlayers}" var="currentplayer">
				<option ${currentplayer.groups.contains(groupToEdit) ? "selected" : ""} value="${currentplayer.id}">${currentplayer.fname} ${currentplayer.lname}</option>
			</c:forEach>
			
		</select> <br /> <input type="submit" value="Edit Group">
	</form>	
	<p><a href="index.html">Add a new player</a></p>
	<p><a href="listPlayersServlet">Players list</a></p>
	<p><a href="listGroupsServlet">Groups list</a></p>
	<p><a href="addPlayersForGroupServlet">Create a new group</a></p>
</body>
</html>