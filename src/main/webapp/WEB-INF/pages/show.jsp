<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista studentów</title>
    </head>

    <body>
        <h1>Lista studentów</h1>
        <table>
            <tr>
                <td>Imię</td>
                <td>Nazwisko</td>
                <td>Uczelnia</td>
            </tr>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>${student.firstName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.universityName}</td>
                    <td><a href="/student/edit/${student.id}">EDYTUJ</a></td>
                    <td><a href="/student/delete/${student.id}">USUŃ</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="/student/new/">Dodaj nowego studenta</a>
    </body>
</html>
