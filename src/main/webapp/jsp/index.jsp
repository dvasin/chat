<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>LOG IN</title></head>
<body>
	<form name="loginForm" method="POST" action="chat">
		<input type="hidden" name="command" value="login" />
		Login:<br/><input type="text" name="nick" value=""/><br/>
		<br/>${errorLoginPassMessage}
		<br/>${wrongAction}
		<br/>${nullPage}
		<br/><input type="submit" value="Log in"/>
	</form><hr/>
</body>
</html>