<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="../../resources/css/commonStyle.css"/>" rel="stylesheet" type="text/css">
<body>
<h1>You really want to buy ticket for <span th:text="${seance.getName()}"></span></h1>
<h2>Ticket with №<span th:text="${ticket.getNumber()}">
</span> on <span th:text="${ticket.getPlace().getRow()}"></span> row and <span
        th:text="${ticket.getPlace().getColumn()}"></span> column</h2>
<h2>Price for ticket is <span th:text="${ticket.getPrice()}"></span></h2>
<form method="POST" th:action="@{/ticket/buy}">
    <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$" name="userEmail" placeholder="Write your email">
    <button>Confirm</button>
    <input type="hidden" name="seanceId" th:value="${seance.getId()}">
    <input type="hidden" name="ticketNumber" th:value="${ticket.getNumber()}">
</form>
<form th:action="@{/ticket/select}">
    <button id="cancelBtn">Cancel</button>
    <input type="hidden" name="seanceId" th:value="${seance.getId()}">
</form>
</body>
<script type="text/javascript" th:src="@{/js/confirmTicket.js}"></script>
</html>