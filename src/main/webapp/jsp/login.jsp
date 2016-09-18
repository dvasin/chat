<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>chat</title></head>
<body>
	<form name="loginForm" method="POST" action="chat">
		<input type="hidden" name="command" value="login" />
		Login:<br/>
		<input type="text" name="nickName" value=""/><br/>
		<br/><input type="submit" value="Login"/>
	</form><hr/>
</body>
</html>