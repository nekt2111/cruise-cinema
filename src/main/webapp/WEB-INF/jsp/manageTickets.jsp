<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage tickets</title>
    <link href="<c:url value="../../../resources/css/commonStyle.css"/>" rel="stylesheet" />
</head>
<body>
<c:choose>
    <c:when test="${!wrongRequest}">
        <div>
            <h1>Manage tickets for seance <span><c:out value="${seanceName}"/></span></h1>
            <form method="POST" action="${pageContext.request.contextPath}/ticket/admin/generateTickets">
                <input type="hidden" value="<c:out value="${seanceId}"/>" name="seanceId">
                <input placeholder="amountOfTickets" name="amountOfTickets" id="amountOfTickets">
                <input placeholder="priceForTickets" name="priceForTickets" id="priceForTickets">
                <button type="submit">Submit</button>
            </form>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            Seance doesn't exists!
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>