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
	<form method="post">
		<table>
			<c:forEach items="${requestScope.groups}" var="currentgroup">
				<tr>
					<td>${currentgroup.id}</td>
				</tr>
				<c:forEach var="playerlist" items="${currentgroup.players}">
					<tr>
						<td></td>
						<td>Player Id: ${playerlist.id}</td>
					</tr>
					<tr>
						<td>Player name: ${playerlist.fname} ${playerlist.lname}</td>
					</tr>
					<tr>
						<td>Player user name: ${playerlist.username}</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
	</form>
</body>
</html>
