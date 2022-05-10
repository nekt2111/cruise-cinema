<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="<c:url value="../../resources/css/commonStyle.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="../../resources/css/buyTickets.css"/>" rel="stylesheet" type="text/css">
    <title>View Books</title>
</head>
<body>
<h1>Tickets for seance <span><c:out value="${seanceName}"></c:out></span></h1>
<div class="tickets">
    <c:forEach items="${tickets}" var="ticket">
        <div class="ticket" class="${ticket.isBought() ? 'boughtTicket' : ''}">
                ${ticket.number}
                ${ticket.isBought()}
        </div>
    </c:forEach>
</div>
<form action="${pageContext.request.contextPath}/ticket/confirm">
    <div>You picked ticket with number: </div>
    <span class="ticket-picked" id="ticked-picked" name="ticketNumber"></span>
    <input type="hidden" name="seanceId" value="${seanceId}">
    <input type="hidden" name="ticketNumber" id="pickedNumberInput">
    <button id="buy-btn">Buy</button>
</form>
</body>
<script type="text/javascript" src="../../resources/js/buyTicket.js"></script>
</html>