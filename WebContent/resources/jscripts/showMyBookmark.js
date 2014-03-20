
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


function saveBookmark(){	
	var title = document.getElementById("title");
	var link = document.getElementById("link");
	var description = document.getElementById("desc");
	var tag = document.getElementById("tag");	
	var checkbox = document.getElementById("isSharing");
	var status = 1;
	if (checkbox.checked){
		status = 0;
	}
	
	var url = rootdir + "saveBookmark?" +
			"title="+title.value+"&link="+link.value+"&tag="+tag.value+"&description="+description.value+"&share="+status;	
	request = createRequest();
	if(request == null)	{
		return;
	}

	request.onreadystatechange = processSaveBookmark;
    request.open("GET", url, true);
    request.send(null);   
    
}

function processSaveBookmark() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var mySaveLink = document.getElementById("mySaveLink");
	
	if(ready == READY_STATE_COMPLETE) {	
		data = "Link is saved successfully.";
		data += "<a href='#' onclick='saveMoreLink();'>Save more link.</a><br/><br/>";
		mySaveLink.innerHTML = "";
		mySaveLink.innerHTML = data;
	} else {
		data = "Loading...";		
		mySaveLink.innerHTML = "";
		mySaveLink.innerHTML = data;	
	}	
	
}
function saveMoreLink(){
	var mySaveLink = document.getElementById("mySaveLink");
	mySaveLink.innerHTML = "";
	mySaveLink.innerHTML = 
		"<div class='mySaveForm'>"+
		"<form>"+
		"<label for='user'>Title</label>"+
		"<input name='textTitle' type='text' id='title'></input>"+
	
		"<label for='emailaddress'>Link:</label>"+
		"<input type='text' name= 'textLink' id='link'></input>"+
	
		"<label for='emailaddress'>Tag</label>"+
		"<input type='text' name= 'textTag' id='tag'></input>"+
	
		"<label for='comments'>Description</label>"+
		"<textarea name= 'textDesc' id='desc'></textarea><br />"+
	
		"<label for='terms'>Don't share</label>"+
		"<input type='checkbox' id='isSharing' name='sharing' class='boxes' /><br />"+
		"<input type='button' id='submitbutton' value=Save' onclick='saveBookmark()>"+						
				
		"</form>"+
		"</div>";
	showProfile();
}

function showMyBookmarks() {
	var url = rootdir + "myBookmarks";
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
	var myBookmarks = document.getElementById("myBookmarks");
	
	if(ready == READY_STATE_COMPLETE) {	
		data = request.responseXML;		
		parseRSS(data);
	} else {
		data = "Loading...";		
		myBookmarks.innerHTML = "";
		myBookmarks.innerHTML = data;	
	}	
}
//
function parseRSS(xmldata){
	if(xmldata == null)	{		
		return;
	}
	
	var items = xmldata.getElementsByTagName("bookmark");	
	var divs= "<div class='middle-content-box-list' id='parent'>";	
	for(var i=0; i < items.length; i++)	{		
		var id = items[i].getElementsByTagName("id")[0].firstChild.nodeValue;
		
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
				     	"<p>Posted by <a href='"+rootdir+"gotoUserPage?username="+username+"'>" + username+ "</a> on "+bookmarktime+" | "+bookmarkdate+"</p>" +
				     	"<p class='post'>" +
				     	"<a href='#' class='suggests' id=suggest"+id+">Suggest</a>" +
				     	"<a href='#' class='deletion' id=delete"+id+">Delete</a>" +
				     	"<a href='#' class='edition' id=edit"+id+">Edit</a>" +						
						"<a href='"+link+"' class='readmores' id=readmore"+id+">Read more</a>" +
						"</p>" +
						"<div id=commentEntry"+id+"></div>" +
						"<div id="+'comment_field'+id+"></div>" +
					"</div>";				  
		divs += line;
	}
	divs += "</div>";	
		
	var myBookmarks = document.getElementById("myBookmarks");
	
	myBookmarks.innerHTML = "";
	myBookmarks.innerHTML = divs;
	
}

function loadMyTagCloud() {
	
	var url = rootdir + "myTagCloud";	
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
	
	var tagCloudBox = document.getElementById("myTagCloud");		
	tagCloudBox.innerHTML ="";
	tagCloudBox.innerHTML = tagEntry;
	
	var myTagsSummary = document.getElementById("myTagsSummary");	
	myTagsSummary.innerHTML = "";
	myTagsSummary.innerHTML = tagEntry;
	
	pushMessage();
}

function createGroup() {	
	
	var groupname = document.getElementById("groupNameInput");
	var description = document.getElementById("groupDescription");	
	var url = rootdir + "createGroup?groupname="+groupname.value+"&description="+description.value;
	
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
	var result="";
	if (data !=0){
		result = "<div class='statusnotes'>" +			 	
				 	"<div class='status'>Status</div>" +
				 	"<p>"+data+" is succesfully created.</p>"+
				 "</div>";
		entryFormObject.innerHTML="";
		entryFormObject.innerHTML=result;
		
		try {
			var newli =document.createElement("li");
			var newdiv =document.createElement("div");
			var newp = document.createElement("p");
			var newa = document.createElement("a");
			var href =document.createAttribute("href");
			newa.setAttributeNode(href);
			var attrhref = newa.getAttributeNode("href");
			newa.setAttribute(attrhref, "#");
			newa.appendChild(document.createTextNode(data));
			newp.appendChild(newa);
			newdiv.appendChild(newp);	
			newli.appendChild(newdiv); 
			document.getElementById("myGroupList").appendChild(newli);
		} catch (Exception){			
			myGroups();
		}
	} else {
		result = "<div class='statusnotes'>" +			 	
				 	"<div class='status'>Status</div>" +
				 	"<p>Forum name is not available, please try another name.</p>"+
				 "</div>";
			entryFormObject.innerHTML="";
			entryFormObject.innerHTML=result;
	}
}


function createForum() {	
	
	var forumname = document.getElementById("createForumInput");
	var description = document.getElementById("forumDescription");	
	var url = rootdir + "createForum?forumname="+forumname.value+"&description="+description.value;

	request = createRequest();
	if(request == null)	{
		return;
	}
	
	request.onreadystatechange = processCreateForum;
    request.open("GET", url, true);
    request.send(null);    
}

function processCreateForum() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var entryFormObject = document.getElementById("entryForm");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseText;		
		parseCreateForumData(data);		
	} else {
		data = "Loading...";		
		entryFormObject.innerHTML = "";
		entryFormObject.innerHTML = data;	
	}	
}

function parseCreateForumData(data){
	var entryFormObject = document.getElementById("entryForm");
	var result="";
	if (data!=0){
		result = "<div class='statusnotes'>" +			 	
				 	"<div class='status'>Status</div>" +
				 	"<p>"+data+" is succesfully created.</p>"+
				 "</div>";
		entryFormObject.innerHTML="";
		entryFormObject.innerHTML=result;
		
		try {
			var newli =document.createElement("li");
			var newdiv =document.createElement("div");
			var newp = document.createElement("p");
			var newa = document.createElement("a");
			var href =document.createAttribute("href");
			newa.setAttributeNode(href);
			var attrhref = newa.getAttributeNode("href");
			newa.setAttribute(attrhref, "#");
			newa.appendChild(document.createTextNode(data));
			newp.appendChild(newa);
			newdiv.appendChild(newp);	
			newli.appendChild(newdiv); 
			document.getElementById("myForumList").appendChild(newli);
		} catch (Exception){			
			myForums();
		}
	} else {
		result = "<div class='statusnotes'>" +			 	
				 	"<div class='status'>Status</div>" +
				 	"<p>Forum name is not available, please try another name.</p>"+
				 	"</div>";
		entryFormObject.innerHTML="";
		entryFormObject.innerHTML=result;
	}
}

function sendMessage() {	
	
	var recipient = document.getElementById("messageRecipient");
	var description = document.getElementById("messageDescription");	
	var url = rootdir + "sendMessage?recipient="+recipient.value+"&description="+description.value;	
	request = createRequest();
	if(request == null)	{
		return;
	}
	
	request.onreadystatechange = sendingMessageProcess;
    request.open("GET", url, true);
    request.send(null);    
}

function sendingMessageProcess() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var entryFormObject = document.getElementById("entryForm");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseText;		
		parseResponse(data);		
	} else {
		data = "Loading...";		
		entryFormObject.innerHTML = "";
		entryFormObject.innerHTML = data;	
	}	
}

function parseResponse(data){
	var entryFormObject = document.getElementById("entryForm");
	var result="";
	result = "<div class='statusnotes'>" +			 	
			 	"<div class='status'>Status</div>" +
			 	"<p>"+data+"</p>"+
			 "</div>";
	entryFormObject.innerHTML="";
	entryFormObject.innerHTML= result;
}

function pushMessage(){	
	var url = rootdir + "pushMessage";	
	request = createRequest();
	if(request == null)	{
		return;
	}
	
	request.onreadystatechange = requestingMessageProcess;
    request.open("GET", url, true);
    request.send(null);
}

function requestingMessageProcess() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var inboxes = document.getElementById("inboxes");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseMessage(data);		
	} else {
		data = "Loading...";		
		inboxes.innerHTML = "";
		inboxes.innerHTML = data;	
	}	
}

function parseMessage(xmldata){
	if(xmldata == null)	{		
		return;
	}
	
		
	var items = xmldata.getElementsByTagName("inbox");
	var divs= "<div class='messages' id='parent'>";	
	for(var i=0; i < items.length; i++)	{		
		var id = items[i].getElementsByTagName("id")[0].firstChild.nodeValue;		
		var recipient = items[i].getElementsByTagName("recipient")[0].firstChild.nodeValue;
		var sender = items[i].getElementsByTagName("sender")[0].firstChild.nodeValue;		
		var message = items[i].getElementsByTagName("message")[0].firstChild.nodeValue;
		var date = items[i].getElementsByTagName("date")[0].firstChild.nodeValue;
		var time = items[i].getElementsByTagName("time")[0].firstChild.nodeValue;
		var status = items[i].getElementsByTagName("status")[0].firstChild.nodeValue;
		var line= 
					"<div class='inbox' id='inbox"+id+"'>" +
						"<div id='messageid"+id+"' value='"+id+"'></div>";						
						if (status=="false"){
							line += "<p><strong><a href='#'>"+message+"</a></strong></p>";
						}else{
							line += "<p><a href='#'>"+message+"</a></p>";
						}
			line +=
				     	"<p>Sent by <a href='#'>" + sender + "</a> on "+date+" | "+time+"</p>" +
				     	"<p><a href='http://localhost:8080/preciproject/loadSendMessagePage' class='replay' id=replay"+id+">Replay</a></p>" +						
					"</div>";				  
		divs += line;
	}
	divs += "</div>";	
	
	var inboxes = document.getElementById("inboxes");	
	inboxes.innerHTML = "";
	inboxes.innerHTML = divs;
}

function getSentMessage(){	
	var url = rootdir + "pushMessage";	
	request = createRequest();
	if(request == null)	{
		return;
	}
	
	request.onreadystatechange = requestingSentMessageProcess;
    request.open("GET", url, true);
    request.send(null);
}

function requestingSentMessageProcess() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var outboxes = document.getElementById("outboxes");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseSentMessage(data);		
	} else {
		data = "Loading...";		
		outboxes.innerHTML = "";
		outboxes.innerHTML = data;	
	}	
}

function parseSentMessage(xmldata){
	if(xmldata == null)	{		
		return;
	}
	
		
	var items = xmldata.getElementsByTagName("outbox");
	var divs= "<div class='messages' id='parent'>";	
	for(var i=0; i < items.length; i++)	{		
		var id = items[i].getElementsByTagName("id")[0].firstChild.nodeValue;		
		var recipient = items[i].getElementsByTagName("recipient")[0].firstChild.nodeValue;
		var sender = items[i].getElementsByTagName("sender")[0].firstChild.nodeValue;		
		var message = items[i].getElementsByTagName("message")[0].firstChild.nodeValue;
		var date = items[i].getElementsByTagName("date")[0].firstChild.nodeValue;
		var time = items[i].getElementsByTagName("time")[0].firstChild.nodeValue;
		var status = items[i].getElementsByTagName("status")[0].firstChild.nodeValue;
		var line= 
					"<div class='outbox' id='outbox"+id+"'>" +
						"<div id='messageid"+id+"' value='"+id+"'></div>";						
						if (status=="false"){
							line += "<p><strong><a href='#'>"+message+"</a></strong></p>";
						}else{
							line += "<p><a href='#'>"+message+"</a></p>";
						}
			line +=
				     	"<p>Posted to <a href='#'>" + recipient + "</a> on "+date+" | "+time+"</p>" +				     							
					"</div>";				  
		divs += line;
	}
	divs += "</div>";	
	
	var outboxes = document.getElementById("outboxes");	
	outboxes.innerHTML = "";
	outboxes.innerHTML = divs;
}

function showProfile(){	
	var url = rootdir + "showProfile";	
	request = createRequest();
	if(request == null)	{
		return;
	}
	
	request.onreadystatechange = processProfile;
    request.open("GET", url, true);
    request.send(null);
}

function processProfile() {	
	if(request == null)	{
		return;
	}

	var ready = request.readyState;
	var data = null;
	var myProfile = document.getElementById("myProfile");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseProfile(data);		
	} else {
		data = "Loading...";		
		myProfile.innerHTML = "";
		myProfile.innerHTML = data;	
	}	
}

function parseProfile(xmldata){
	if(xmldata == null)	{		
		return;
	}
	
		
	var items = xmldata.getElementsByTagName("me");
	var divs= "<div class='profile' id='parent'>";	
	for(var i=0; i < items.length; i++)	{		
		var firstname = items[i].getElementsByTagName("fname")[0].firstChild.nodeValue;		
		var lastname = items[i].getElementsByTagName("lname")[0].firstChild.nodeValue;
		var email = items[i].getElementsByTagName("email")[0].firstChild.nodeValue;		
		var line= 	
			"<p><strong><label>First Name : </label></strong><label>"+firstname+"</label></p><br/>"+
			"<p><strong><label>Last Name : </label></strong><label>"+lastname+"</label></p><br/>"+
			"<p><strong><label>Email : </label></strong><label>"+email+"</label></p><br/>";
			
		divs += line;
	}
	divs += "</div>";	
	
	var myProfile = document.getElementById("myProfile");	
	myProfile.innerHTML = "";
	myProfile.innerHTML = divs;
	
	var bookmarks = xmldata.getElementsByTagName("bookmarks");
	var divs2= "<div class='bookmarks' id='parent'><ul>";	
	for(var j=0; j < bookmarks.length; j++)	{		
		var url = bookmarks[j].getElementsByTagName("uri")[0].firstChild.nodeValue;		
		var title = bookmarks[j].getElementsByTagName("title")[0].firstChild.nodeValue;
		var description = bookmarks[j].getElementsByTagName("description")[0].firstChild.nodeValue;		
		var line2=  
						"<li>"+
							"<a href='"+url+"'><strong>"+title+"</strong></a><br/>"+
							"<p>"+description+"</p><br/>"+
						"</li>";
					
		divs2 += line2;
	}
	divs2 += "</ul></div>";	
	
	var myBookmarksSummary = document.getElementById("myBookmarkSummary");	
	myBookmarksSummary.innerHTML = "";
	myBookmarksSummary.innerHTML = divs2;
	loadMyTagCloud();
}

function findGroup() {
	
	var groupname = document.getElementById("groupInput");
	var url = rootdir + "findGroup?groupname="+groupname.value;
	request = createRequest();
	if(request == null)	{
		return;
	}	
	request.onreadystatechange = findGroupChange;
    request.open("GET", url, true);
    request.send(null);    
}

function findGroupChange() {	
	if(request == null)	{
		return;
	}	
	var ready = request.readyState;
	var data = null;
	var foundGroups = document.getElementById("foundGroups");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseGroupData(data);
	} else {
		data = "Loading...";		
		foundGroups.innerHTML = "";
		foundGroups.innerHTML = data;	
	}	
}


function parseGroupData(xmldata){
	if(xmldata == null)	{		
		return;
	}
	var divs= "<div class='FindGroup' id='parent'><ui>";
	var groups = xmldata.getElementsByTagName("group");			
	for(var i=0; i < groups.length; i++)	{		
		var id = groups[i].getElementsByTagName("id")[0].firstChild.nodeValue;
		var groupname = groups[i].getElementsByTagName("groupname")[0].firstChild.nodeValue;		
		var description = groups[i].getElementsByTagName("description")[0].firstChild.nodeValue;
		var date = groups[i].getElementsByTagName("date")[0].firstChild.nodeValue;		
		var time = groups[i].getElementsByTagName("time")[0].firstChild.nodeValue;
		var creator = groups[i].getElementsByTagName("creator")[0].firstChild.nodeValue;
		var line=
			
				"<li>"+
					"<div class='grouplist' id='"+id+"'>"+
					"<p><a href='"+rootdir +"gotoGroupPage?groupname="+groupname+"'>"+groupname+"</a></p>"+
					"<label>Created by "+creator+" on "+date+":"+time+" | </label><a href='#' onclick='joinGroup("+id+")'>Join</a>"+
					"<p>"+description+"</p>"+			
					"</div>";
				"</li>";
			
		divs += line;
	}
	divs += "</ul></div>";
	var foundGroups = document.getElementById("foundGroups");
	foundGroups.innerHTML = "";
	foundGroups.innerHTML = divs;	
}

function joinGroup(id){	
	var url = rootdir + "joinGroup?groupid="+id;	
	
	request = createRequest();
	if(request == null)	{
		return;
	}	
	request.onreadystatechange = joinGroupProcChange;
    request.open("GET", url, true);
    request.send(null);
}

function joinGroupProcChange() {	
	if(request == null)	{
		return;
	}	
	var ready = request.readyState;
	var data = null;
	var myGroupList = document.getElementById("myGroupList");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseJoinGroupData(data);
	} else {
		data = "Loading...";
		
	}	
}

function parseJoinGroupData(xmldata){
	if(xmldata == null)	{		
		return;
	}
	
	var divs= "<div class='MyGroups' id='parent'>";
	var groups = xmldata.getElementsByTagName("group");
	var groupname;
	for(var i=0; i < groups.length; i++)	{		
		groupname = groups[i].getElementsByTagName("groupname")[0].firstChild.nodeValue;		
	}	
	
	if (groups.length > 0){
		try {
			var newli =document.createElement("li");
			var newdiv =document.createElement("div");
			var newp = document.createElement("p");
			var newa = document.createElement("a");
			var href =document.createAttribute("href");
			newa.setAttributeNode(href);
			newa.appendChild(document.createTextNode(groupname));
			newp.appendChild(newa);
			newdiv.appendChild(newp);	
			newli.appendChild(newdiv); 
			document.getElementById("myGroupList").appendChild(newli);
		} catch (Exception){			
			myGroups();
		}
	} else {
		alert("You are already join the group.")
	}
	
}

function myGroups() {
	var url = rootdir + "myGroups";
	request = createRequest();
	if(request == null)	{
		return;
	}	
	request.onreadystatechange = myGroupProcChange;
    request.open("GET", url, true);
    request.send(null);    
}

function myGroupProcChange() {	
	if(request == null)	{
		return;
	}	
	var ready = request.readyState;
	var data = null;
	var myGroups = document.getElementById("myGroups");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseMyGroupData(data);
	} else {
		data = "Loading...";		
		myGroups.innerHTML = "";
		myGroups.innerHTML = data;	
	}	
}


function parseMyGroupData(xmldata){
	if(xmldata == null)	{		
		return;
	}
	var divs= "<div class='MyGroups' id='parent'><ul id='myGroupList'>";
	var groups = xmldata.getElementsByTagName("group");			
	for(var i=0; i < groups.length; i++)	{		
		var id = groups[i].getElementsByTagName("id")[0].firstChild.nodeValue;
		var groupname = groups[i].getElementsByTagName("groupname")[0].firstChild.nodeValue;		
		var description = groups[i].getElementsByTagName("description")[0].firstChild.nodeValue;
		var date = groups[i].getElementsByTagName("date")[0].firstChild.nodeValue;		
		var time = groups[i].getElementsByTagName("time")[0].firstChild.nodeValue;
		var creator = groups[i].getElementsByTagName("creator")[0].firstChild.nodeValue;
		var line=			
				"<li>"+
					"<div class='grouplist'>"+
					"<p><a href='"+rootdir +"gotoGroupPage?groupname="+groupname+"'>"+groupname+"</a></p>"+								
					"</div>";
				"</li>";
			
		divs += line;
	}
	divs += "</ul></div>";
	var myGroups = document.getElementById("myGroups");
	myGroups.innerHTML = "";
	myGroups.innerHTML = divs;	
}

function findForum() {	
	var forumname = document.getElementById("forumInput");
	var url = rootdir + "findForum?forumname="+forumname.value;
	
	request = createRequest();
	if(request == null)	{
		return;
	}	
	request.onreadystatechange = findForumChange;
    request.open("GET", url, true);
    request.send(null);    
}

function findForumChange() {	
	if(request == null)	{
		return;
	}	
	var ready = request.readyState;
	var data = null;
	var foundForums = document.getElementById("foundForums");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseForumData(data);
	} else {
		data = "Loading...";		
		foundForums.innerHTML = "";
		foundForums.innerHTML = data;	
	}	
}
//var id;
function parseForumData(xmldata){
	if(xmldata == null)	{		
		return;
	}
	
	var divs= "<div class='FindForum' id='parent'><ul id='myForumList'>";
	var forums = xmldata.getElementsByTagName("forum");	
	
		for(var i=0; i < forums.length; i++)	{		
			var id = forums[i].getElementsByTagName("id")[0].firstChild.nodeValue;
			var forumname = forums[i].getElementsByTagName("forumname")[0].firstChild.nodeValue;		
			var description = forums[i].getElementsByTagName("description")[0].firstChild.nodeValue;
			var date = forums[i].getElementsByTagName("date")[0].firstChild.nodeValue;		
			var time = forums[i].getElementsByTagName("time")[0].firstChild.nodeValue;
			var creator = forums[i].getElementsByTagName("creator")[0].firstChild.nodeValue;
			var line=				
					"<li>"+
						"<div class='forumlist'>"+
						"<p><a href='"+rootdir+"gotoForumPage?forumname="+forumname+"' >"+forumname+"</a></p>"+
						"<label>Created by "+creator+" on "+date+":"+time+" | </label><a href='#' onclick='joinForum("+id+")'>Join</a>"+
						"<p>"+description+"</p>"+			
						"</div>";
					"</li>";
				
			divs += line;
		}
		divs += "</ul></div>";	
		var foundForums = document.getElementById("foundForums");
		foundForums.innerHTML = "";
		foundForums.innerHTML = divs;
	 
}

function myForums() {
	var url = rootdir + "myForums";
	request = createRequest();
	if(request == null)	{
		return;
	}	
	request.onreadystatechange = myForumProcChange;
    request.open("GET", url, true);
    request.send(null);    
}

function myForumProcChange() {	
	if(request == null)	{
		return;
	}	
	var ready = request.readyState;
	var data = null;
	var myForums = document.getElementById("myForums");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseMyForumData(data);
	} else {
		data = "Loading...";		
		myForums.innerHTML = "";
		myForums.innerHTML = data;	
	}	
}


function parseMyForumData(xmldata){
	if(xmldata == null)	{		
		return;
	}
	var divs= "<div class='MyForums' id='parent'><ul id='myForumList'>";
	var forums = xmldata.getElementsByTagName("forum");			
	for(var i=0; i < forums.length; i++)	{		
		var id = forums[i].getElementsByTagName("id")[0].firstChild.nodeValue;
		var forumname = forums[i].getElementsByTagName("forumname")[0].firstChild.nodeValue;		
		var description = forums[i].getElementsByTagName("description")[0].firstChild.nodeValue;
		var date = forums[i].getElementsByTagName("date")[0].firstChild.nodeValue;		
		var time = forums[i].getElementsByTagName("time")[0].firstChild.nodeValue;
		var creator = forums[i].getElementsByTagName("creator")[0].firstChild.nodeValue;
		var line=
				"<li>"+
					"<div class='forumlist'>"+
					"<p><a href='"+rootdir+"gotoForumPage?forumname="+forumname+"' >"+forumname+"</a></p>"+								
					"</div>";
				"</li>";
			
		divs += line;
	}
	divs += "</ul></div>";
	var myForums = document.getElementById("myForums");
	myForums.innerHTML = "";
	myForums.innerHTML = divs;	
}

function joinForum(id){	
	var url = rootdir + "joinForum?forumid="+id;	
	
	request = createRequest();
	if(request == null)	{
		return;
	}	
	request.onreadystatechange = joinForumProcChange;
    request.open("GET", url, true);
    request.send(null);
}

function joinForumProcChange() {	
	if(request == null)	{
		return;
	}	
	var ready = request.readyState;
	var data = null;
	var myForumList = document.getElementById("myForumList");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseJoinForumData(data);
	} else {
		data = "Loading...";
		
	}	
}

function parseJoinForumData(xmldata){
	if(xmldata == null)	{		
		return;
	}
	
	var divs= "<div class='MyForums' id='parent'>";
	var forums = xmldata.getElementsByTagName("forum");
	var forumname;
	for(var i=0; i < forums.length; i++)	{		
		forumname = forums[i].getElementsByTagName("forumname")[0].firstChild.nodeValue;		
	}	
	
	if (forums.length > 0){
		try {
			var newli =document.createElement("li");
			var newdiv =document.createElement("div");
			var newp = document.createElement("p");
			var newa = document.createElement("a");
			var href =document.createAttribute("href");
			newa.setAttributeNode(href);
			newa.appendChild(document.createTextNode(forumname));
			newp.appendChild(newa);
			newdiv.appendChild(newp);	
			newli.appendChild(newdiv); 
			document.getElementById("myForumList").appendChild(newli);
		} catch (Exception){			
			myForums();
		}
	} else {
		alert("You are already join the forum.")
	}
	
}

function openGroup(id) {	
	
	var url = rootdir + "gotoGroupPage";
	alert(url);
	request = createRequest();
	if(request == null)	{
		return;
	}
	request.onreadystatechange = gotoGroupProcChange;
    request.open("GET", url, true);
    request.send(null);    
}
function gotoGroupProcChange() {	
	if(request == null)	{
		return;
	}	
	var ready = request.readyState;
	var data = null;
	var groupDescription = document.getElementById("groupDescription");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseGotoForumData(data);
	} else {
		data = "Loading...";
		
	}	
}

function parseGotoGroupData(xmldata){
	if(xmldata == null)	{		
		return;
	}	
}

function openForum(id) {
	alert("test");
	var url = rootdir + "gotoForumPage";
	request = createRequest();
	if(request == null)	{
		return;
	}
	request.onreadystatechange = gotoForumProcChange;
    request.open("GET", url, true);
    request.send(null);    
}
function gotoForumProcChange() {	
	if(request == null)	{
		return;
	}	
	var ready = request.readyState;
	var data = null;
	var forumDescription = document.getElementById("forumDescription");
	if(ready == READY_STATE_COMPLETE) {
		data = request.responseXML;		
		parseJoinForumData(data);
	} else {
		data = "Loading...";
		
	}	
}

function parseGotoForumData(xmldata){
	if(xmldata == null)	{		
		return;
	}
	
}
