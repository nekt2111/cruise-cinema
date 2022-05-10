<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
            <title>Manage seance</title>
        <link href="<c:url value="../../../resources/css/commonStyle.css"/>" rel="stylesheet" />
    </head>
    <body>
        <h1><c:out value="${seance.name}"/></h1>
        <h2><c:out value="${seance.date}"/></h2>
        <h2><c:out value="${seance.time}"/></h2>
        <h2>Description:</h2>
        <h2><c:out value="${seance.description}"/></h2>
        <h3>Amount of tickets: <span><c:out value="${tickets.size()}"/></span></h3>
        <h3>Tickets left: <span><c:out value="${tickets.size() - amountOfBoughtTickets}"/></span></h3>
        <form action="/seance/admin/add">
            <input type="hidden" name="seanceId" value="<c:out value="${seance.id}"/>">
            <button type="submit">Configure</button>
        </form>
        <form action="/seance/admin/delete" method="POST">
            <input type="hidden" name="seanceId" value="<c:out value="${seance.id}"/>">
            <button type="submit">Delete seance</button>
        </form>
    </body>
</html>