<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Manager</title>
</head>
<body>
	<h3>Users app</h3>

	<c:if test="${sessionScope.message!=''}"><c:out value="${sessionScope.message}"/></c:if>
	
	
	<h4>Add user:</h4>
	<form action="api/persons" method="POST">
		<input type="text" name="name" placeholder="imię">*<br/>
		<input type="text" name="surname" placeholder="nazwisko">*<br/>
		<input type="text" name="number" placeholder="numer"><br/>
		<input type="submit" value="Add">
	</form>

	<p/>

	<h4>Users already added:</h4>
	<c:if test="${empty sessionScope.persons}"><c:out value="Brak użytkowników w bazie. Dodaj nowych używając powyższego formularza"/></c:if>
	<c:forEach items="${sessionScope.persons}" var="person">	
		<c:out value="${person}"/>
		<form action="api/persons/${person.id}" method="POST">
			<input type="submit" value="Delete"/>
		</form>
		<br/>
	</c:forEach>
</body>
</html>
