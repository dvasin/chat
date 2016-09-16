<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head><title>chat</title></head>
<body>


	<div>
		<c:forEach var="elem" items="${messages}">
		    <c:out value="${elem.user.nickname}: "/>
		    <c:out value="${elem.textMessage}"/><br>
	    </c:forEach>
	</div>
	<form name="sendMessageForm" method="POST" action="chat.jsp">
		<input type="hidden" name="command" value="sendMessage" />
		Сообщение:<br>
		<textarea  type="text" name="message" value="" cols="40" rows="3"></textarea>
		<input type="submit" value="->"/>
	</form>

    <form name="refresh" method="POST" action="chat.jsp">
        <input type="hidden" name="command" value="getlastmessages" />
        <p><input type="submit" value="&#8646;"></p>
    </form>

	<form id="logoutForm" action="chat.jsp" method="post">
        <a href="javascript:;" onclick="document.getElementById('logoutForm').submit();">Logout</a>
        <input type="hidden" name="command" value="logout"/>
    </form>

    <c:out value="${messages}"/>



</body>
</html>