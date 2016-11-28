<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <form:form commandName="student" method="post" action="/student" >
            <form:hidden path="id" id="id" />
            <table>
                <tr>
                    <td><label>Imię</label></td>
                    <td><form:input path="firstName" id="firstName"/></td>
                </tr>
                <tr>
                    <td><label>Nazwisko</label></td>
                    <td><form:input path="lastName" id="lastName"/></td>
                </tr>
                <tr>
                    <td><label>Uczelnia</label></td>
                    <td><form:input path="universityName" id="universityName"/></td>
                </tr><tr>
                    <td><input type="submit" name="zapisz" value="Zapisz"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
