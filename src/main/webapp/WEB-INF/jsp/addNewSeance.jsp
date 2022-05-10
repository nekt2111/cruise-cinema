<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="<c:url value="../../resources/css/commonStyle.css"/>" rel="stylesheet" type="text/css">
    <title>View Books</title>
</head>
<body>
<h1>Manage seances:</h1>
<form action="${doesSeanceAlreadyExists ? '/seance/admin/update' : '/seance/admin/addSeance'}" method="POST" th:object="${seance}">
    <input placeholder="Film name" name="${seance.name}" value="${seance.getName()}">
    <input placeholder="Film description" th:field="*{description}">
    <input type="date" th:field="*{date}">
    <input type="time" th:field="*{time}">
    <input type="hidden" th:field="*{id}">
    <button type="submit" th:text="${doesSeanceAlreadyExists ? 'Configure' : 'Add new'}"></button>
</form>
</body>
</html>