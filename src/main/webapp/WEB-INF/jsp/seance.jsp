<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Cruise Cinema</title>
    <link href="<c:url value="/styles/css/commonStyle.css"/>" rel="stylesheet" />
</head>
<body>
    <h1><c:out value="${seance.name}"/></h1>
    <h2><c:out value="${seance.name}"/></h2>
    <h2><c:out value="${seance.name}"/></h2>
    <h2>Description:</h2>
    <h2><c:out value="${seance.description}"/></h2>
    <h3>Amount of tickets: <span><c:out value="${tickets.size}"/></span></h3>
    <h3>Tickets left: <span><c:out value="${(tickets.size - amountOfBoughtTickets)}"/></span></h3>
    <form action="<c:url value="/ticket/select"/>">
        <input type="hidden" name="seanceId" id="seanceId" value="<c:out value="${seance.id}"/>">
        <button type="submit">Buy ticket</button>
    </form>
</body>
</html>