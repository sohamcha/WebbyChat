/**
 * 
 */

// ChatArea ID : chatText
// Send Button ID : send
// Participant Window ID : users
// chat Input ID : chatMsg
// Changelog 1.1 : Added Notification API support

var count = parseInt(document.getElementById("chatCount"));
var keyCount = 0;

function chatInit(){

document.getElementById('chatMsg').onkeypress=function(keyCode){
    if(keyCode.keyCode==13){
    	sendChat(); // Fix for Enter - Send behavior
    }
}


// For getting Participants list
var getParticipants = function(){
	var xmlhttp = new XMLHttpRequest();
	constructXHRRequest(xmlhttp,"participants");
	xmlhttp.send("type=participants");
}

setInterval(getParticipants, 1000);

//For getting the chat String
var getChatString = function(){
	var xmlhttp = new XMLHttpRequest();
	constructXHRRequest(xmlhttp,"get");
	xmlhttp.send("type=get&msgCount="+count);
}

setInterval(getChatString, 400);

checkNotificationsAvailable(); // Notification API Initialization

// For getting the Keystroke Information if Someone is typing

var getKeyStrokeInfo = function(){
	var xmlhttp = new XMLHttpRequest();
	constructXHRRequest(xmlhttp,"keyStroke");
	xmlhttp.send("type=get&msgCount="+count);
}

setInterval(getKeyStrokeInfo, 400);
	
}


function sendChat(){
	
	if(document.getElementById("chatMsg").value==""){
		return;
	}
	var xmlhttp = new XMLHttpRequest();
	constructXHRRequest(xmlhttp, "send");
	xmlhttp.send("type=send&chatMsg="+document.getElementById("chatMsg").value);
	document.getElementById("chatMsg").value="";
	// Resetting KeyStroke Count
	keyCount=0;

}


function logout(){
	var xmlhttp = new XMLHttpRequest();
	constructXHRRequest(xmlhttp, "send");
	xmlhttp.send("type=logout");
	alert("You have been logged out. Kindly close the browser window");
	window.location="index.jsp";
}


function constructXHRRequest(xhr,type){
	if(type=="participants"){
		xhr.open("POST","chatServer",true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.onreadystatechange=function()
		  {
		  if (xhr.readyState==4 && xhr.status==200)
		    {
		    document.getElementById("users").value = xhr.responseText;
		    
		    // Auto Scroll
		    document.getElementById("users").scrollTop = document.getElementById("users").scrollHeight;
		    }
		  }
		
	}
	else if(type=="get"){
		xhr.open("POST","chatServer",true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.onreadystatechange=function()
		  {
		  if (xhr.readyState==4 && xhr.status==200)
		    {			  

			var parsedResponse = JSON.parse(xhr.responseText);   // Parse the JSON coming from ChatServer
			count = parseInt(parsedResponse.chatCount);
			var newChatText = parsedResponse.chatString;
			if(newChatText!="" && newChatText!=null){
		    document.getElementById("chatText").value += newChatText;
		    
		    // AutoScroll feature	
		    document.getElementById("chatText").scrollTop = document.getElementById("chatText").scrollHeight;
		    
		    //Ping Sound
		    document.getElementById("ping").play();
		    
		    // Notification Show
		    var who = newChatText.substring(0,newChatText.indexOf(":")-1);
		    if(userName!=who){
		    var notificationBox = new Notification(parsedResponse.chatString);
			}		    
			}
		    }
		  }
		
	}
	else if(type=="send" || type=="logout"){
		xhr.open("POST","chatServer",true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	}
	
}

function checkNotificationsAvailable(){
	
	if (!("Notification" in window)) {
	    alert("You cannot receive Notifications.");
	  }

	  // Let's check if the user is okay to get some notification
	 else if (Notification.permission !== 'denied') {
		 Notification.requestPermission(checkNotificationsAvailable());
	 }
	
}

// For Keystroke Detection
function detectChange(){
	
	keyCount++;
	
	if(keyCount>4){
		// Send the user name who is typing
	}
	
}