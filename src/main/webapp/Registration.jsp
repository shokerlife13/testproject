<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm" %>
<%@ taglib prefix='tags' tagdir='/WEB-INF/tags' %>
<html>
<head>
    <title>Registration</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="generalBlock">
    <springForm:form action="RegistrationAction" method="POST" commandName="validationForm">
        <br/><br/>

        <h1>Registration</h1>
        <table>
            <tr>
                <td>
                    Login
                </td>
                <td>
                    <springForm:input path="login"/>
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
                <td colspan="2">
                    <tags:reCaptcha privateKey='6Lc8U_sSAAAAAFhwYwVvOq-iiT7ndFo8vvOGuRBk'
                                    publicKey='6Lc8U_sSAAAAAKbBpsYsz9_FuKVZSGKFftrL03Fl'/>
                    <span class="error">${err_message}</span>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="OK"/>
                    <input type="button" onclick="window.location.replace('Login.jsp')" value="Cancel"/>
                </td>
            </tr>
        </table>
    </springForm:form>
</div>
</body>
</html>
