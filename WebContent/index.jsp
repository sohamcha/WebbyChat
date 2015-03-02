<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
var errorText = "${requestScope.error}";
var userName = "${sessionScope.id}";

function trimString(x) {
    return x.replace(/^\s+|\s+$/gm,'');
}

function checkErrors(){
if(errorText != ""){
	alert("The UserName is already taken. Try with a different one.")
}

if(userName != "" && userName != null){
	document.getElementById("name").readOnly=true;
}
}

function checkUserName(){
	if(trimString(document.getElementById("name").value)==""){
		alert("Enter some username.");
		return false;
	}
}

</script>

</head>
<body onload="checkErrors();">
<h2 align="center">Welcome to TinyChat</h2>
<div style="margin-top:40px;margin-bottom: 40px ">
<form action="chatInit" method="post">
<div>
<span style="padding-right: 5px;">UserName:</span>
<span><input name="name" type="text" id="name" value="${sessionScope.id}"></input></span>
</div>
<div style="margin-top: 30px;margin-left: 82px;">
<input type="submit" value="JOIN" onclick="return checkUserName();"></input>
</div>

</form>
</div>
</body>
</html>