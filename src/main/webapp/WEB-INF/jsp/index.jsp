<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="<c:url value="../../resources/css/commonStyle.css"/>" rel="stylesheet" type="text/css">
    <title>View Books</title>
</head>
<body>

<h1>Cruise Cinema</h1>
<h2>Place where all movies are better :)</h2>

<h3>All seances for today</h3>
<div class="seances">
    <c:forEach items="${seances}" var="seance">
        <div class="seance" id="${seance.id}">
            <a href="/seance/${seance.id}" class="seance-name">${seance.name}</a>
            <div class="seance-date">${seance.date}</div>
            <div class="seance-time">${seance.time}</div>
            <div class="seance-description">${seance.description}</div>
        </div>
    </c:forEach>
</div>
</body>
</html>