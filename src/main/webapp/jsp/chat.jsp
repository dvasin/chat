<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head><title>chat</title></head>
<body>

	<div>
		<c:forEach var="elem" items="${lastmessages}">
		    <c:out value="${elem.user.nickName}: "/>
		    <c:out value="${elem.text}"/><br>
	    </c:forEach>
	</div>
	<form name="loginForm" method="POST" action="chat.jsp">
		<input type="hidden" name="command" value="sendMessage" />
		Сообщение:<br>
		<textarea  type="text" name="message" value="" cols="40" rows="3"></textarea>
		<input type="submit" value="->"/>
	</form>

	<form id="logoutForm" action="chat.jsp" method="post">
        <a href="javascript:;" onclick="document.getElementById('logoutForm').submit();">Logout</a>
        <input type="hidden" name="command" value="logout"/>
    </form>
</body>
</html>