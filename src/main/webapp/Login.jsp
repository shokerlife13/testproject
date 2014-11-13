<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<form method="post" action="/j_spring_security_check">
    <table class="table" align="center">
        <tr>
            <td>
                Login:
            </td>
            <td>
                <input type="text" name="j_username" size="20"/>
            </td>
        </tr>
        <tr>
            <td>
                Password:
            </td>
            <td>
                <input type="password" name="j_password" size="20"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right">
                <a href="GoToRegistrationPage">Registration</a>
                <button type="submit" value="Sign in" name="button">Sign in</button>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <font color="red">
                    <c:if test="${param.error == 'true'}">
                        Bad login or password!
                        <p>Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
                    </c:if>
                </font>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
