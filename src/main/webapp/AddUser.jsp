<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>
<html>
<head>
    <title>Add user</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="generalBlock">
    <springForm:form action="AddUserAction" method="POST" commandName="validationForm">
        <p align="right">
            Admin: ${user.login}
            (<a href="/j_spring_security_logout">logout</a>)
        </p>
        <br/><br/>

        <h1>Add user</h1>
        <table>
            <tr>
                <td>
                    Login
                </td>
                <td>
                    <springForm:input path="login" />
                </td>
                <td class="error"><springForm:errors path="login"/></td>
            </tr>
            <tr>
                <td>
                    Password
                </td>
                <td>
                    <springForm:input path="password"/>
                </td>
                <td><span class="error"><springForm:errors path="password"/></span></td>
            </tr>
            <tr>
                <td>
                    Password again
                </td>
                <td>
                    <springForm:input path="passwordAgain"/>
                </td>
                <td><span class="error"><springForm:errors path="passwordAgain"/></span></td>
            </tr>
            <tr>
                <td>
                    Email
                </td>
                <td>
                    <springForm:input path="email"/>
                </td>
                <td><span class="error"><springForm:errors path="email"/></span></td>
            </tr>
            <tr>
                <td>
                    First name
                </td>
                <td>
                    <springForm:input path="firstName"/>
                </td>
                <td><span class="error"><springForm:errors path="firstName"/></span></td>
            </tr>
            <tr>
                <td>
                    Last name
                </td>
                <td>
                    <springForm:input path="lastName"/>
                </td>
                <td><span class="error"><springForm:errors path="lastName"/></span></td>
            </tr>
            <tr>
                <td>
                    Birthday (YYYY-MM-DD)
                </td>
                <td>
                    <springForm:input path="birthday"/>
                </td>
                <td><span class="error"><springForm:errors path="birthday"/></span></td>
            </tr>
            <tr>
                <td>
                    Role
                </td>
                <td>
                    <springForm:select style="width: 90%;" path="role">
                        <springForm:option value="administrator">Administrator</springForm:option>
                        <springForm:option value="user">User</springForm:option>
                    </springForm:select> *
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="OK"/>
                    <input type="button" onclick="window.location.replace('Admin.jsp')" value="Cancel"/>
                </td>
            </tr>
        </table>
    </springForm:form>
</div>
</body>
</html>
