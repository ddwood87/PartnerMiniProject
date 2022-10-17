<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
				<option value="${currentplayer.id}">${currentplayer.fname} ${currentplayer.lname}</option>
			</c:forEach>
			
		</select> <br /> <input type="submit" value="Edit Group">
	</form>
	<a href="index.html">Add new players</a>
</body>
</html>