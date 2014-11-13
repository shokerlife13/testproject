<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Access denied</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
<center><h1 class="access">HTTP Status 403 - Access denied!</h1>
    <br/>
    <a href="/j_spring_security_logout">Back to login page</a>
</center>
</body>
</html>
