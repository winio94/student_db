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
            <c:forEach items="${studenci}" var="student">
                <tr>
                    <td>${student.imie}</td>
                    <td>${student.nazwisko}</td>
                    <td>${student.uczelnia}</td>
                    <td><a href="/SSI_Spring/student/edytuj/${student.id}">EDYTUJ</a></td>
                    <td><a href="/SSI_Spring/student/usun/${student.id}">USUŃ</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="/SSI_Spring/student/nowy">Dodaj nowego studenta</a>
    </body>
</html>
