<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="student.show.title"/></title>
</head>

<body>
<h1><spring:message code="student.show.title"/></h1>
<table>
    <c:if test="${not empty students}">
        <tr>
            <td><spring:message code="student.firstname"/></td>
            <td><spring:message code="student.lastname"/></td>
            <td><spring:message code="student.university"/></td>
        </tr>
    </c:if>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.universityName}</td>
            <td><a href="/student/edit/${student.id}"><spring:message code="student.edit"/> </a></td>
            <td><a href="/student/delete/${student.id}"><spring:message code="student.delete"/></a></td>
        </tr>
    </c:forEach>
</table>
<a href="/student/new/"><spring:message code="student.new"/></a>
</body>
</html>