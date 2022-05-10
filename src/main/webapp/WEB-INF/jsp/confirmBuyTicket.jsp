<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="../../resources/css/commonStyle.css"/>" rel="stylesheet" type="text/css">
    <meta charset="UTF-8">
    <title>Cruise Cinema</title>
</head>
<body>
<h1>You really want to buy ticket for <span><c:out value="${seance.name}"/></span></h1>
<h2>Ticket with №<span><c:out value="${ticket.number}"/>
</span> on <span><c:out value="${ticket.getPlace().getRow()}"/></span> row and <span>
    <c:out value="${ticket.getPlace().getColumn()}"/> </span> column</h2>
<h2>Price for ticket is <span><c:out value="${ticket.price}"/> </span></h2>
<form method="POST" action="/ticket/buy">
    <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$" name="userEmail" placeholder="Write your email">
    <button>Confirm</button>
    <input type="hidden" name="seanceId" value="${seance.id}">
    <input type="hidden" name="ticketNumber" value="${ticket.number}">
</form>
<form action="/ticket/select">
    <button id="cancelBtn">Cancel</button>
    <input type="hidden" name="seanceId" value="${seance.id}">
</form>
</body>
<script type="text/javascript" src="/js/confirmTicket.js"></script>
</html>