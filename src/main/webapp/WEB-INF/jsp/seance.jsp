<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cruise Cinema</title>
    <link href="<c:url value="../../resources/css/commonStyle.css"/>" rel="stylesheet" />
</head>
<body>
    <h1><c:out value="${seance.name}"/></h1>
    <h2><c:out value="${seance.date}"/></h2>
    <h2><c:out value="${seance.time}"/></h2>
    <h2>Description:</h2>
    <h2><c:out value="${seance.description}"/></h2>
    <h3>Amount of tickets: <span><c:out value="${tickets.size()}"/></span></h3>
    <h3>Tickets left: <span><c:out value="${(tickets.size() - amountOfBoughtTickets)}"/></span></h3>
    <form action="${pageContext.request.contextPath}/ticket/select">
        <input type="hidden" name="seanceId" id="seanceId" value="<c:out value="${seance.id}"/>">
        <button type="submit">Buy ticket</button>
    </form>
</body>
</html>