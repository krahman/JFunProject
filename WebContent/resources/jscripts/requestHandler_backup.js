
var READY_STATE_UNINITIALIZED=0;
var READY_STATE_LOADING=1;
var READY_STATE_LOADED=2;
var READY_STATE_INTERACTIVE=3;
var READY_STATE_COMPLETE=4;
var rootdir = "http://localhost:8080/preciproject/";

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



function loadDocument() {
	var url = rootdir+"popularList";	
	request = createRequest();
	if(request == null)	{
		return;
	}

	request.onreadystatechange = processRequestChange;
    request.open("GET", url, true);
    request.send(null);    
}

function processRequestChange() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var popularList = document.getElementById("popularList");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseList(data);
	} else {
		data = "Loading...";		
		popularList.innerHTML = "";
		popularList.innerHTML = data;	
	}	
}
//var id;
function parseList(xmldata){
	if(xmldata == null)	{		
		return;
	}	
	//populate bookmark list
	var items = xmldata.getElementsByTagName("bookmark");
	var comments= xmldata.getElementsByTagName("commententry");
	var tags = xmldata.getElementsByTagName("tagentry");
	var divs= "<div class='middle-content-box-list' id='parent'>";	
	for(var i=0; i < items.length; i++)	{		
		var id = items[i].getElementsByTagName("id")[0].firstChild.nodeValue;		
			//*******************************************
			// COMMENT LIST
			//*******************************************		
			var list = "";
			var commentList = "<div><p><strong>Comment List: </strong></p>";
								
			for(var j=0; j < comments.length; j++)	{				
				var bookmarkid = comments[j].getElementsByTagName("bookmarkid")[0].firstChild.nodeValue;
				if (bookmarkid == id){									
					var commentid = comments[j].getElementsByTagName("commentid")[0].firstChild.nodeValue;					
					var comment = comments[j].getElementsByTagName("comment")[0].firstChild.nodeValue;
					var user = comments[j].getElementsByTagName("user")[0].firstChild.nodeValue;
					var time = comments[j].getElementsByTagName("time")[0].firstChild.nodeValue;
					var date = comments[j].getElementsByTagName("date")[0].firstChild.nodeValue;		
					var row = "<div class='commentList' id='commentList"+id+"'>"+ 	
								"<p>"+comment+"</p>" +
								"<p>Comment by <a href='#'>" + user+ "</a> on "+time+" | "+date+"</p>" +
							  "</div>";				  
					commentList += row;
					commentList += "</div>";
					list = commentList;
				}
			}				
			
			//*******************************************
			// TAG LIST
			//*******************************************		
			var tagEntry  = "";
			for(var k=0; k < tags.length; k++)	{					
				var bookmarkid_tag = tags[k].getElementsByTagName("bookmarkid")[0].firstChild.nodeValue;				
				if (bookmarkid_tag == id){
					var tagname = tags[k].getElementsByTagName("tagname")[0].firstChild.nodeValue;					
					tagEntry += "<a href='#'>"+tagname+" </a>"; 
				}
			}		
			
			// ******************************************
			
		var title = items[i].getElementsByTagName("title")[0].firstChild.nodeValue;
		var link = items[i].getElementsByTagName("link")[0].firstChild.nodeValue;		
		var username = items[i].getElementsByTagName("username")[0].firstChild.nodeValue;
		var description = items[i].getElementsByTagName("description")[0].firstChild.nodeValue;
		var bookmarktime = items[i].getElementsByTagName("time")[0].firstChild.nodeValue;
		var bookmarkdate = items[i].getElementsByTagName("date")[0].firstChild.nodeValue;
		var line= 
					"<div class='postingContainer' id='posting"+i+"'>" +
						"<div id='bookmarkid' value='"+id+"'></div>" +
						"<div class='middle-content-box-list-post' id='header-post'>" + "<a href='" + link + "'>" + title + "</a></div>" +
				     	"<p>"+description+"</p><br/>" +
				     	"<p>Posted by <a href='#'>" + username+ "</a> on "+bookmarktime+" | "+bookmarkdate+"</p>" +
				     	"<p class='post'>" +
				     	"<a href='#' class='suggests' id='suggest"+id+"' onclick='createSuggestion("+id+"); " +
								"document.suggest_form.suggest_text.focus();'>Suggest</a>" +
						"<a href='#' class='comments' id='comment"+id+"' onclick='writeComment("+id+"); " +
								"document.comment_form.comment_text.focus();'>Post Comment</a>" +
						"<a href='"+link+"' class='readmores' id='readmore"+id+"'>Read more</a>" +
						"</p>" +
						"<div class ='tagEntry' id='tagEntry"+id+"'>Tag : "+tagEntry+"</div><br/>" +	
						"<div class ='commentEntry' id='commentEntry"+id+"'>"+list+"</div>" +					
						"<div id='commentField"+id+"'></div>" +
						"<div id='suggestionField"+id+"'></div><br/>" +
					"</div>";				  
		divs += line;
	}
	divs += "</div>";	
		
	var popularList = document.getElementById("popularList");
	
	popularList.innerHTML = "";
	popularList.innerHTML = divs;
	loadTagCloud();
}

function createSuggestion(id){
	var suggest = document.getElementById("suggestionField"+id);
	var divs2 = "<div>" +
					"<form method='get' name='suggest_form'>" +
						"<input name='suggest_text' id='suggestColumn"+id+"' type='text' " +
						"width ='30' onblur='outSuggest("+id+");'></input>" +
						"<input type='button' value='Send' onclick='setId("+id+");sendSuggestion();'></input>" +
						"<input type='button' value='Cancel' onclick='cancelSuggest("+id+");'></input>" +
					"</form>" +
				"</div>";
	suggest.innerHTML="";
	suggest.innerHTML=divs2;
}

function cancelSuggest(id){	
	var suggest = document.getElementById("suggestionField"+id);
	suggest.innerHTML ="";	 	
}

function outSuggest(id){
	var suggest = document.getElementById("suggestionField"+id);
	if(document.suggest_form.suggest_text.value==""){
		suggest.innerHTML ="";
	}
}


var id;
function setId(currId){
	id = currId;	
}

function writeComment(id){
	var comment = document.getElementById("commentField"+id);
	var divs2 = "<div>" +
					"<form method='get' name='comment_form'>" +
						"<textarea name='comment_text' id='commentArea"+id+"' type='textarea' " +
						"width ='100em' cols='50' onblur='outComment("+id+");'></textarea><br/>" +
						"<input type='button' value='Comment' onclick='setId("+id+");saveComment();'></input>" +
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

function saveComment(){	
	var comment = document.getElementById("commentArea"+id);	
	var url = rootdir+"saveComment?comment=" + comment.value +"&bookmarkid="+id;	
	request = createRequest();
	if (request == null){		
		return;
	}	
	request.onreadystatechange = showCommentRequest;	
	request.open("GET", url, true);
	request.send(null);
}

function showCommentRequest(){
	
	if (request == null){		
		return;
	}
	 var ready = request.readyState;
	 var xmlData = null;
	 var commentEntry = document.getElementById("commentEntry"+id); 
	  
	 if(ready == READY_STATE_COMPLETE){		 
		 xmlData = request.responseXML;	 		 
		 parseComment(xmlData);		 
		 //alert(xmlData);
	 }else {		 
		 data = "Please wait...";		 
		 commentEntry.innerHTML ="";
		 commentEntry.innerHTML = data;
	 }
	 
}

function parseComment(xmldata){
	
	if(xmldata == null)	{
		return;
	}
	
	var comments= xmldata.getElementsByTagName("commententry");
	var divs  =  "<div><p><strong>Comment List: </strong></p>";		
	for(var i=0; i < comments.length; i++)	{		
		var comment = comments[i].getElementsByTagName("comment")[0].firstChild.nodeValue;
		var user = comments[i].getElementsByTagName("user")[0].firstChild.nodeValue;
		var time = comments[i].getElementsByTagName("time")[0].firstChild.nodeValue;
		var date = comments[i].getElementsByTagName("date")[0].firstChild.nodeValue;		
		var line = "<div class='commentList' id='commentList"+id+"'>"+ 	
					"<p>"+comment+"</p>" +
					"<p>Comment by <a href='#'>" + user+ "</a> on "+time+" | "+date+"</p>" +
				  "</div>";				  
		divs += line;		
	}

	divs += "</div><br/>";	
		
	var commentEntry = document.getElementById("commentEntry"+id);		
	commentEntry.innerHTML ="";
	commentEntry.innerHTML = divs;	
	cancelComment(id);
	
}

function getTagsList() {
	var tagname = document.getElementById("tagname");
	var url = rootdir+"showTagsList?tagname="+tagname.value;	
	request = createRequest();
	if(request == null)	{
		return;
	}

	request.onreadystatechange = processTags;
    request.open("GET", url, true);
    request.send(null);    
}

function processTags() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var exploreTags = document.getElementById("exploreTags");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseTag(data);
	} else {
		data = "Loading...";		
		exploreTags.innerHTML = "";
		exploreTags.innerHTML = data;	
	}	
}

function parseTag(xmldata){
	if(xmldata == null)	{		
		return;
		
	}
		
	//populate bookmark list
	var items = xmldata.getElementsByTagName("bookmark");
	var comments= xmldata.getElementsByTagName("commententry");
	var tags = xmldata.getElementsByTagName("tagentry");
	var divs= "<div class='middle-content-box-list' id='parent'>";	
	for(var i=0; i < items.length; i++)	{		
		var id = items[i].getElementsByTagName("id")[0].firstChild.nodeValue;		
			//*******************************************
			// COMMENT LIST
			//*******************************************		
			var list = "";
			var commentList = "<div><p><strong>Comment List: </strong></p>";
								
			for(var j=0; j < comments.length; j++)	{				
				var bookmarkid = comments[j].getElementsByTagName("bookmarkid")[0].firstChild.nodeValue;
				if (bookmarkid == id){									
					var commentid = comments[j].getElementsByTagName("commentid")[0].firstChild.nodeValue;					
					var comment = comments[j].getElementsByTagName("comment")[0].firstChild.nodeValue;
					var user = comments[j].getElementsByTagName("user")[0].firstChild.nodeValue;
					var time = comments[j].getElementsByTagName("time")[0].firstChild.nodeValue;
					var date = comments[j].getElementsByTagName("date")[0].firstChild.nodeValue;		
					var row = "<div class='commentList' id='commentList"+id+"'>"+ 	
								"<p>"+comment+"</p>" +
								"<p>Comment by <a href='#'>" + user+ "</a> on "+time+" | "+date+"</p>" +
							  "</div>";				  
					commentList += row;
					commentList += "</div>";
					list = commentList;
				}
			}				
			
			//*******************************************
			// TAG LIST
			//*******************************************		
			var tagEntry  = "";
			for(var k=0; k < tags.length; k++)	{					
				var bookmarkid_tag = tags[k].getElementsByTagName("bookmarkid")[0].firstChild.nodeValue;				
				if (bookmarkid_tag == id){
					var tagname = tags[k].getElementsByTagName("tagname")[0].firstChild.nodeValue;					
					tagEntry += "<a href='#'>"+tagname+" </a>"; 
				}
			}		
			
			// ******************************************
			
		var title = items[i].getElementsByTagName("title")[0].firstChild.nodeValue;
		var link = items[i].getElementsByTagName("link")[0].firstChild.nodeValue;		
		var username = items[i].getElementsByTagName("username")[0].firstChild.nodeValue;
		var description = items[i].getElementsByTagName("description")[0].firstChild.nodeValue;
		var bookmarktime = items[i].getElementsByTagName("time")[0].firstChild.nodeValue;
		var bookmarkdate = items[i].getElementsByTagName("date")[0].firstChild.nodeValue;
		var line= 
					"<div class='postingContainer' id='posting"+i+"'>" +
						"<div id='bookmarkid' value='"+id+"'></div>" +
						"<div class='middle-content-box-list-post' id='header-post'>" + "<a href='" + link + "'>" + title + "</a></div>" +
				     	"<p>"+description+"</p><br/>" +
				     	"<p>Posted by <a href='#'>" + username+ "</a> on "+bookmarktime+" | "+bookmarkdate+"</p>" +
				     	"<p class='post'>" +
				     	"<a href='index.html' class='suggests' id='suggest"+id+"'>Suggest</a>" +
						"<a href='#' class='comments' id='comment"+id+"' onclick='writeComment("+id+"); " +
								"document.comment_form.comment_text.focus();'>Post Comment</a>" +
						"<a href='"+link+"' class='readmores' id='readmore"+id+"'>Read more</a>" +
						"</p>" +
						"<div class ='tagEntry' id='tagEntry"+id+"'>Tag : "+tagEntry+"</div><br/>" +	
						"<div class ='commentEntry' id='commentEntry"+id+"'>"+list+"</div>" +					
						"<div id='commentField"+id+"'></div>" +
					"</div>";				  
		divs += line;
	}
	divs += "</div>";	
		
	var exploreTags = document.getElementById("exploreTags");
	
	exploreTags.innerHTML = "";
	exploreTags.innerHTML = divs;
}

function loadTagCloud() {	
	var url = rootdir+"tagCloud";	
	request = createRequest();
	if(request == null)	{
		return;
	}

	request.onreadystatechange = processTagCloud;
    request.open("GET", url, true);
    request.send(null);    
}

function processTagCloud() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var tagCloudBox = document.getElementById("tagCloud");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseTagCloud(data);		
	} else {
		data = "Loading...";		
		tagCloudBox.innerHTML = "";
		tagCloudBox.innerHTML = data;	
	}	
}

function parseTagCloud(xmldata){
	
	if(xmldata == null)	{
		return;
	}
	
	var tagcloud= xmldata.getElementsByTagName("tagEntryTerm");
	
	var tagEntry  = "";
	for(var k=0; k < tagcloud.length; k++)	{					
		var tagname = tagcloud[k].getElementsByTagName("tagname")[0].firstChild.nodeValue;
		tagEntry += "<a href='#'>"+tagname+" </a>";		
	}
	
	var tagCloudBox = document.getElementById("tagCloud");		
	tagCloudBox.innerHTML ="";
	tagCloudBox.innerHTML = tagEntry;	
	
}

function findFriend() {
		
	var username = document.getElementById("friendColumnInput");
	var url = rootdir+"friendBookmark?username="+username.value;
	request = createRequest();
	if(request == null)	{
		return;
	}	
	request.onreadystatechange = findFriendRequestChange;
    request.open("GET", url, true);
    request.send(null);    
}

function findFriendRequestChange() {	
	if(request == null)	{
		return;
	}	
	var ready = request.readyState;
	var data = null;
	var friendBookmark = document.getElementById("foundFriendBookmarks");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseFriendData(data);
	} else {
		data = "Loading...";		
		friendBookmark.innerHTML = "";
		friendBookmark.innerHTML = data;	
	}	
}
//var id;
function parseFriendData(xmldata){
	if(xmldata == null)	{		
		return;
	}
	
	//populate bookmark list
	var items = xmldata.getElementsByTagName("bookmark");
	var comments= xmldata.getElementsByTagName("commententry");
	var tags = xmldata.getElementsByTagName("tagentry");
	var divs= "<div class='middle-content-box-list' id='parent'>";	
	
	for(var i=0; i < items.length; i++)	{		
		var id = items[i].getElementsByTagName("id")[0].firstChild.nodeValue;		
			//*******************************************
			// COMMENT LIST
			//*******************************************		
			var list = "";
			var commentList = "<div><p><strong>Comment List: </strong></p>";
								
			for(var j=0; j < comments.length; j++)	{				
				var bookmarkid = comments[j].getElementsByTagName("bookmarkid")[0].firstChild.nodeValue;
				if (bookmarkid == id){									
					var commentid = comments[j].getElementsByTagName("commentid")[0].firstChild.nodeValue;					
					var comment = comments[j].getElementsByTagName("comment")[0].firstChild.nodeValue;
					var user = comments[j].getElementsByTagName("user")[0].firstChild.nodeValue;
					var time = comments[j].getElementsByTagName("time")[0].firstChild.nodeValue;
					var date = comments[j].getElementsByTagName("date")[0].firstChild.nodeValue;		
					var row = "<div class='commentList' id='commentList"+id+"'>"+ 	
								"<p>"+comment+"</p>" +
								"<p>Comment by <a href='#'>" + user+ "</a> on "+time+" | "+date+"</p>" +
							  "</div>";				  
					commentList += row;
					commentList += "</div>";
					list = commentList;
				}
			}				
			
			//*******************************************
			// TAG LIST
			//*******************************************		
			var tagEntry  = "";
			for(var k=0; k < tags.length; k++)	{					
				var bookmarkid_tag = tags[k].getElementsByTagName("bookmarkid")[0].firstChild.nodeValue;				
				if (bookmarkid_tag == id){
					var tagname = tags[k].getElementsByTagName("tagname")[0].firstChild.nodeValue;					
					tagEntry += "<a href='#'>"+tagname+" </a>"; 
				}
			}		
			
			// ******************************************
			
		var title = items[i].getElementsByTagName("title")[0].firstChild.nodeValue;
		var link = items[i].getElementsByTagName("link")[0].firstChild.nodeValue;		
		var username = items[i].getElementsByTagName("username")[0].firstChild.nodeValue;
		var description = items[i].getElementsByTagName("description")[0].firstChild.nodeValue;
		var bookmarktime = items[i].getElementsByTagName("time")[0].firstChild.nodeValue;
		var bookmarkdate = items[i].getElementsByTagName("date")[0].firstChild.nodeValue;
		
		var line= 
					"<div class='postingContainer' id='posting"+i+"'>" +
						"<div id='bookmarkid' value='"+id+"'></div>" +
						"<div class='middle-content-box-list-post' id='header-post'>" + "<a href='" + link + "'>" + title + "</a></div>" +
				     	"<p>"+description+"</p><br/>" +
				     	"<p>Posted by <a href='#'>" + username+ "</a> on "+bookmarktime+" | "+bookmarkdate+"</p>" +
				     	"<p class='post'>" +
				     	"<a href='#' class='savelinks' id='savelink"+id+"' onclick='savelink()'><strong>SAVE</strong></a>" +
				     	"<a href='#' class='suggests' id='suggest"+id+"' onclick='createSuggestion("+id+"); " +
								"document.suggest_form.suggest_text.focus();'>Suggest</a>" +
						"<a href='#' class='comments' id='comment"+id+"' onclick='writeComment("+id+"); " +
								"document.comment_form.comment_text.focus();'>Post Comment</a>" +
						"<a href='"+link+"' class='readmores' id='readmore"+id+"'>Read more</a>" +						
						"</p>" +
						"<div class ='tagEntry' id='tagEntry"+id+"'>Tag : "+tagEntry+"</div><br/>" +	
						"<div class ='commentEntry' id='commentEntry"+id+"'>"+list+"</div>" +					
						"<div id='commentField"+id+"'></div>" +
						"<div id='suggestionField"+id+"'></div><br/>" +
					"</div>";				  
		divs += line;
	}
	divs += "</div>";	
		
	var friendBookmark = document.getElementById("foundFriendBookmarks");
	
	friendBookmark.innerHTML = "";
	friendBookmark.innerHTML = divs;
	loadFriendTagCloud();
}

function loadFriendTagCloud() {
	var username = document.getElementById("friendColumnInput");
	var url = rootdir+"friendTagCloud?username="+username.value;	
	request = createRequest();
	if(request == null)	{
		return;
	}

	request.onreadystatechange = processFriendTagCloud;
    request.open("GET", url, true);
    request.send(null);    
}

function processFriendTagCloud() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var tagCloudBox = document.getElementById("myTagCloud");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseTagCloud(data);		
	} else {
		data = "Loading...";		
		tagCloudBox.innerHTML = "";
		tagCloudBox.innerHTML = data;	
	}	
}

function parseFriendTagCloud(xmldata){
	
	if(xmldata == null)	{
		return;
	}
	
	var tagcloud= xmldata.getElementsByTagName("tagEntryTerm");
	
	var tagEntry  = "";
	for(var k=0; k < tagcloud.length; k++)	{					
		var tagname = tagcloud[k].getElementsByTagName("tagname")[0].firstChild.nodeValue;
		tagEntry += "<a href='#'>"+tagname+" </a>";		
	}
	
	var tagCloudBox = document.getElementById("myTagCloud");		
	tagCloudBox.innerHTML ="";
	tagCloudBox.innerHTML = tagEntry;	
	
}

function createGroup() {	
	alert("test");
	var groupname = document.getElementById("groupNameInput");
	var description = document.getElementById("groupDescription");
	var url = rootdir+"createGroup?groupname="+username.value+"&description="+description.value;	
	request = createRequest();
	if(request == null)	{
		return;
	}

	request.onreadystatechange = processCreateGroup;
    request.open("GET", url, true);
    request.send(null);    
}

function processCreateGroup() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var entryFormObject = document.getElementById("entryForm");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseText;		
		parseCreateGroupData(data);		
	} else {
		data = "Loading...";		
		entryFormObject.innerHTML = "";
		entryFormObject.innerHTML = data;	
	}	
}

function parseCreateGroupData(data){
	var entryFormObject = document.getElementById("entryForm");
	entryFormObject.innerHTML="";
	entryFormObject.innerHTML=data;
}
