<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="<c:url value="../../resources/css/commonStyle.css"/>" rel="stylesheet" type="text/css">
    <title>View Books</title>
</head>
<body>
<h1>Manage seances:</h1>
<form action="${doesSeanceAlreadyExists ? '/seance/admin/update' : '/seance/admin/addSeance'}" method="POST">
    <input placeholder="Film name" name="name" value="${seance.name}">
    <input placeholder="Film description" name="description" value="${seance.description}">
    <input type="date" name="date" value="${seance.date}">
    <input type="time" name="time" value="${seance.time}">
    <input type="hidden" name="id" value="${seance.id}">
    <button type="submit">${doesSeanceAlreadyExists ? 'Configure' : 'Add new'}</button>
</form>
</body>
</html>