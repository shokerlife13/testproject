<%@ page import="laba17.repository.HibernateUserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/customtaglist.tld" prefix="t" %>
<html>
<head>
    <title>Administrator</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="generalBlock">
    <p align="right">
        Admin: ${user.login}
        (<a href="/j_spring_security_logout">logout</a>)
    </p>
    <br/><br/>
    <a href="GoToAddPage">Add new user</a>
    <br/><br/>
    <c:set var="list"  value="${usersList}" />
    <t:customTag usersList="${list}"/>
</div>
</body>
</html>
