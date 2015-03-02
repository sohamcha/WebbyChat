<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chat</title>
<script type="text/javascript">
var userName = "${sessionScope.id}";
</script>
<script type="text/javascript" src="ChatLogic.js"></script>
</head>

<body onload="chatInit();">

<input type="hidden" id="chatCount" value="${requestScope.chatCount}">

<audio  id="ping" width="0" height="0">
<source src="sounds/ping.mp3"/>
Not Supported HTML5 Audio
</audio>

<h1>Chat Home</h1>
<div style="margin-top: 60px;margin-bottom: 60px;">
<span><input type="button" value="LOGOUT" onclick="logout();"></span>
</div>
<div>
<div>
<textarea style="width: 900px;height: 350px;" id="chatText" name="chatText" readonly="readonly"></textarea>
</div>
<div style="padding-top: 10px;"><span><input type="text" style="width: 845px;" name="chatMsg" id="chatMsg" onkeydown="detectChange();"/></span><span style="padding-left: 3px;"><input type="button" value="SEND" id="send" onclick="sendChat();"/></span></div>
</div>
<div style="margin-top: 10px;">
<div><span style="font-size: larger;font-weight: bolder;">Participants:</span></div>
<div style="float: left;">
<textarea cols="35" rows="10" id="users" readonly="readonly"></textarea>
</div>
<div style="float: left;margin-left: 5px">
<textarea rows="5" cols="40" id="typing" readonly="readonly"></textarea>
</div>
</div>
</body>
</html>