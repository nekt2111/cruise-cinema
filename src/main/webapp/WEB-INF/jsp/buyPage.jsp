<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="../../resources/css/commonStyle.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="../../resources/css/buyTickets.css"/>" rel="stylesheet" type="text/css">
    <title>Thank for buying ticket</title>
</head>
<body>
<c:choose>
    <c:when test="${ticketWasBought}">
        <div>
            <h1>Thank you for buying ticket <c:out value="${ticket.getUserEmail()}"/></h1>
            <h2>You bought ticket with number <span>${ticket.getNumber()}</span></h2>
            <h2>on seance <c:out value="${seance.name}"/></h2>
            <h2>that will be <span>${seance.getDate()}</span></h2>
            <h2>on <span>${seance.getTime()}</span></h2>
            <h2>Ticket was sent you to the email. Have a great movie :)</h2>
            <a href="/">Back home</a>
        </div>
    </c:when>
    <c:otherwise>
        <div>Ticket was already bought. Sorry</div>
    </c:otherwise>

</c:choose>
</body>
</html>