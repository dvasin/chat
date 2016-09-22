<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>chat</title>
	<meta charset="utf-8">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
	<style type="text/css">
		html {

		}
		.mesdiv {
			overflow: auto;
		    width: 400px;
		    height: 300px;
		    background: #ccc;
		    padding: 5px;
		    border: solid 1px black;
		    float: left;
		    position: relative;
		    word-wrap: break-word;
		}
	   .userdiv {
	   		overflow: auto;
		    width: 200px;
		    height: 300px;
		    background: #fc0;
		    padding: 5px;
		    border: solid 1px black;
		    float: left;
		    position: relative;

		}
		.sendmesdiv {

			float: button;
		    border: solid 1px black;
		    width: 600px;
		    height: 150px;
		}
		textarea {
			resize: none;
			width: 610px;
			height: 70px;
			padding: 5px;
		}
		.send_button {
			width: 620px;
			height: 40px;
		}

	</style>
</head>
<body>
<div class="container">
	<!--messages & users-->
	<div>
		<div class="mesdiv" id="messages">
			<c:forEach var="elem" items="${sessionScope.messages}">

			    <c:set var="date" value="${elem.date}" />
                <fmt:formatDate type="both" value="${date}" />

			    <c:out value="${elem.user.nickname}: "/>
			    <c:out value="${elem.textMessage}"/><br>
		    </c:forEach>
		</div>
		<div class="userdiv" id="users">
			<c:forEach var="elem" items="${sessionScope.users}">
               <a href="http://" style="text-decoration: none;">${elem.nickname}</a><br>
            </c:forEach>
		</div>
		<form name="sendMessageForm" method="POST" action="chat" class="sendmesdiv">
			<input type="hidden" name="command" value="sendMessage" />
			<textarea name="message" autofocus></textarea>
			<input type="submit" value="->" class="send_button"/>
		</form>
	</div>
		<!--logout-->
	<form id="logoutForm" action="chat" method="post">
        <a href="javascript:;" onclick="document.getElementById('logoutForm').submit();">Logout</a>
        <input type="hidden" name="command" value="logout"/>
    </form>
</div>
<script type="text/javascript">
        function show()
        {
            $.ajax({
                type: "GET",
                url: "chat",
                data: {"command":"refresh"},
                success: function(html){
                    $('#messages').html(html.substring(html.indexOf('id="messages">') + 'id="messages">'.length, html.indexOf('</div>')))
                    $('#users').html(html.substring(html.indexOf('id="users">') + 'id="users">'.length, html.indexOf('</div>', html.indexOf('id="users">'))))
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