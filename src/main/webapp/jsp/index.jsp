<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>chat</title></head>
<body>

<c:set var="user" scope="page" value="${sessionScope.user}"/>
<c:if test="${ not empty user }">
     <jsp:forward page="chat.jsp"/>
</c:if>
<jsp:forward page="login.jsp"/>
</body>
</html>