<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="../../resources/css/commonStyle.css"/>" rel="stylesheet" type="text/css">
    <meta charset="UTF-8">
    <title>Cruise Cinema</title>
</head>
<body>

<h1>You Cannot buy this ticket for <span><c:out value="${seance.name}"/></span></h1>
<h2>Ticket with №<span> ${ticket.number}
</span> on ${ticket.getPlace().getRow()} row and ${ticket.getPlace().getColumn()} column</h2>
<form action="/ticket/select">
    <button id="cancelBtn">Cancel</button>
    <input type="hidden" name="seanceId" value="${seance.id}">
</form>
</body>
<script type="text/javascript" src="/js/confirmTicket.js"></script>
</html>