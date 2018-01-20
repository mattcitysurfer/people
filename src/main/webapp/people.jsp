<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Manager</title>
</head>
<body>
	<h3>Users app</h3>

	<h5>
		<c:if test="${sessionScope.warning!=''}"><c:out value="${sessionScope.warning}"/></c:if>
	</h5>
	<h4>Add user:</h4>
	<form action="api/persons" method="POST">
		<input type="text" name="name" placeholder="imię"><br/>
		<input type="text" name="surname" placeholder="nazwisko"><br/>
		<input type="text" name="number" placeholder="numer"><br/>
		<input type="submit" value="Add"><br />
	</form>

	<h4>Users already added:</h4>
	<c:forEach items="${sessionScope.persons}" var="person">	
		<c:out value="${person}"/><br/>
	</c:forEach>
</body>
</html>
