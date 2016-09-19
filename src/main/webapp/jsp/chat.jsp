<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head><title>chat</title></head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<body>


	<div id="messages">
		<c:forEach var="elem" items="${sessionScope.messages}">
		    <c:out value="${elem.user.nickname}: "/>
		    <c:out value="${elem.textMessage}"/><br>
	    </c:forEach>
	</div>
    <!--send message-->
	<form name="sendMessageForm" method="POST" action="chat">
		<input type="hidden" name="command" value="sendMessage" />
		<textarea name="message" cols="40" rows="3"></textarea>
		<input type="submit" value="->"/>
	</form>
	<!--refresh messages-->
    <form name="refresh" method="POST" action="chat">
        <input type="hidden" name="command" value="getlastmessages" />
        <!--<p><input type="submit" value="&#8646;"></p>-->
        <p><input type="button" onclick="getLastMessages()" value="&#8646;"></p>--
    </form>

	<!--logout-->
	<form id="logoutForm" action="chat" method="post">
        <a href="javascript:;" onclick="document.getElementById('logoutForm').submit();">Logout</a>
        <input type="hidden" name="command" value="logout"/>
    </form>

    <script type="text/javascript">
        function show()
        {
            $.ajax({
                type: "GET",
                url: "chat",
                data: {"command":"getlastmessages"},
                success: function(html){
                    //alert(html.substring(html.indexOf('<div id="messages">') + '<div id="messages">'.length, html.indexOf('</div>')))
                    $('#messages').html(html.substring(html.indexOf('<div id="messages">') + '<div id="messages">'.length, html.indexOf('</div>')))
                 }
            });
        }

        $(document).ready(function(){
            show();
            setInterval('show()',7000);
        });
    </script>

    </body>
</html>