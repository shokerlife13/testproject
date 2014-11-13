<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hi, user!</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="textBlock" align="center">
    Hello, ${user.login}!
    <div class="text">Click <a href="/j_spring_security_logout">here</a> to logout</div>
</div>
</body>
</html>
