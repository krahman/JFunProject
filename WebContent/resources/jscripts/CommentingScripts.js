var READY_STATE_UNINITIALIZED=0;
var READY_STATE_LOADING=1;
var READY_STATE_LOADED=2;
var READY_STATE_INTERACTIVE=3;
var READY_STATE_COMPLETE=4;

var request = createRequest();
if( request == null){
	alert("XMLHttpRequest object cannot be created. Application cannot run.");
}

function createRequest(){
	var req = null;

	// Non-Microsoft browsers
	try	{
		req = new XMLHttpRequest();
	}catch(ex) {
		// Microsoft browsers
		try {
			req = new ActiveXObject("Msxml2.XMLHTTP");
		}catch(ex1){
			// Other Microsoft versions
			try {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			} catch(ex2){
				req = null;
			}
		}
	}
	return req;
}

function writeComment(id){
	var comment = document.getElementById("commentField"+id);
	var divs2 = "<div>" +
					"<form method='get' name='comment_form'>" +
						"<textarea name='comment_text' id='commentArea"+id+"' type='textarea' " +
						"width ='100em' cols='50' onblur='outComment("+id+");'></textarea><br/>" +
						"<input type='button' value='Comment' onclick='saveComment("+id+");'></input>" +
						"<input type='button' value='Cancel' onclick='cancelComment("+id+");'></input>" +
					"</form>" +
				"</div>";	
	
	comment.innerHTML ="";
	comment.innerHTML = divs2;
	
}

function cancelComment(id){	
	var comment = document.getElementById("commentField"+id);
	comment.innerHTML ="";	 	
}

function outComment(id){
	var comment = document.getElementById("commentField"+id);
	if(document.comment_form.comment_text.value==""){
		comment.innerHTML ="";
	}
}

function saveComment(id){	
	var comment = document.getElementById("commentArea"+id);	
	var url = "saveComment?comment=" + comment.value +"&bookmarkid="+id;
	
	request = createRequest();
	if (request == null){		
		return;
	}
	
	request.onreadystatechange = showCommentRequest(id);
	request.open("GET", url, true);
	request.send(null);
}

function showCommentRequest(id){	
	if (request == null){
		return;
	}
	 var ready = request.readyState;
	 var xmlData = null;
	 var commentEntry = document.getElementById("commentEntry"+id); 
	 alert("test");
	 if(ready == READY_STATE_COMPLETE){
		 xmlData = request.responseXML;	 		 
		 parseComment(xmlData, id);
		 
	 }else {		 
		 data = "Please wait...";
		 commentEntry.innerHTML = "";
		 commentEntry.innerHTML = xmlData;
	 }
}

function parseComment(xmldata, id){
	
	if(xmldata == null)	{
		return;
	}
	
	var comments= xmldata.getElementsByTagName("commententry"+id);
	var divs  =  "<div class='commentList' id='commentList"+id+"'>";		
	for(var i=0; i < comments.length; i++)	{		
		var comment = comments[i].getElementsByTagName("comment")[0].firstChild.nodeValue;
		var user = comments[i].getElementsByTagName("user")[0].firstChild.nodeValue;
		var time = comments[i].getElementsByTagName("time")[0].firstChild.nodeValue;
		var date = comments[i].getElementsByTagName("date")[0].firstChild.nodeValue;
		
		var line = 	"<p>"+comment+"</p>" +
				  "</div>";				  
		divs += line;
		alert(divs);
	}

	divs += "</div>";	
		
	var commentEntry = document.getElementById("commentEntry");
	
	commentEntry.innerHTML = "";
	commentEntry.innerHTML = divs;	
	
}

