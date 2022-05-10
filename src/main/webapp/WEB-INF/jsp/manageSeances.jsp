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
    <div class="seance" id="<c:out value="${seance.id}"/>">
        <c:forEach items="${seances}" var="seance">
            <a href="/seance/admin/manage/<c:out value="${seance.getId()}"/>" class="seance-name">
                <c:out value="seance.name"/>
            </a>
            <div class="seance-date"><c:out value="${seance.date}"/></div>
            <div class="seance-time"><c:out value="${seance.time}"/></div>
            <div class="seance-description"><c:out value="${seance.description}"/></div>
        </c:forEach>
    </div>
</div>
<form action="${pageContext.request.contextPath}/seance/admin/add}">
    <button>Add new seance</button>
</form>
</body>
</html>