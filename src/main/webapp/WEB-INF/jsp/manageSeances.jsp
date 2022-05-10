<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manages seances</title>
    <link href="<c:url value="../../resources/css/commonStyle.css"/>" rel="stylesheet" />
</head>
<body>
<div class="seances">
    <c:forEach items="${seances}" var="seance">
    <div class="seance" id="<c:out value="${seance.id}"/>">
            <a href="/seance/admin/manage/<c:out value="${seance.getId()}"/>" class="seance-name">
                <c:out value="${seance.name}"/>
            </a>
            <div class="seance-date"><c:out value="${seance.date}"/></div>
            <div class="seance-time"><c:out value="${seance.time}"/></div>
            <div class="seance-description"><c:out value="${seance.description}"/></div>
    </div>
    </c:forEach>
</div>
<form action="/seance/admin/add" method="GET">
    <button>Add new seance</button>
</form>
</body>
</html>