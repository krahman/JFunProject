<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/style.css" type="text/css"></link>
<script type="text/javascript" src="./resources/jscripts/eventHandler.js"></script>
<script type="text/javascript" src="./resources/jscripts/showMyBookmark.js"></script>

<title>Logged in</title>
</head>
<body onload="loadMyTagCloud();">
	<div id="wrapper">
		<jsp:include page="/WEB-INF/view/include/loginmenubar.jsp" flush="true"></jsp:include>
		<%String userName = (String)session.getAttribute("userName"); 
		if (userName != null || !userName.trim().equals("")){%>
		<jsp:include page="/WEB-INF/view/include/ContentFiltering.jsp" flush="true"></jsp:include>
		<%}%>
		<div id="menus">			
			<div class="menu-item"><label>PROFILE</label></div>
			<div class="menu-item"><label onclick="showMyBookmarks()">BOOKMARKS</label></div>
			<div class="menu-item"><label>SAVE LINK</label></div>
			<div class="menu-item"><label>PEOPLE</label></div>
			<div class="menu-item"><label>TAGS</label></div>			
			<div class="menu-item"><label>INBOX</label></div>
		</div>
		<jsp:include page="/WEB-INF/view/include/headerarea.jsp" flush="true"></jsp:include>		
		<div id="middle-column">		
			<div class="tab-panel">
				<div class="tab-panel-content" id="one">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Profile</div>
        				<p>Profile</p>
        				<div id="myProfile">        					
        				</div> 	  	
					</div>
				</div>				
				<div class="tab-panel-content" id="two">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Bookmarks</div>
        				<p>List of Bookmarks here</p>
        				<div id="myBookmarks">        					
        				</div> 	  	
					</div>
				</div>
				<div class="tab-panel-content" id="three">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Save Link</div>
        				<p>Insert link:</p>
        				<div id="mySaveLink">
        					<form>
								<div id="save-left">
									<div class="save-link-left"><label>Title</label></div>									
									<div class="save-link-left"><label>Link</label></div>
									<div class="save-link-left"><label>Tag</label></div>
									<div class="save-link-left"><label>Description</label></div>
								</div>								
								<div id="save-right">
									<div class="save-link-right"><input type="text" id="title"></input></div>									
									<div class="save-link-right"><input type="text" id="link"></input></div>
									<div class="save-link-right"><input type="text" id="tag"></input></div>
									<div class="save-link-right"><textarea id="desc"></textarea></div>
								</div>							
								<div class="save-link-button"><input type="button" value="Save" onclick="saveBookmark();"></input></div>				
							</form>
        				</div> 	  	
					</div>
				</div>				
				<div class="tab-panel-content" id="four">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">People</div>
        				<p>List of people is here</p>
        				<div id="myPeople"></div> 	  	
					</div>
				</div>
				<div class="tab-panel-content" id="five">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Tags</div>
        				<p>List of tags is here</p>
        				<div id="myTags"></div> 	  	
					</div>
				</div>
				<div class="tab-panel-content" id="six">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Inbox</div>
        				<p>Inbox</p>
        				<div id="myInbox"></div> 	  	
					</div>
				</div>
			</div>
			<div class="middle-column-box-white">
											
			</div>				
				
		</div>
		<jsp:include page="/WEB-INF/view/include/footerbar.jsp" flush="true"></jsp:include>
	</div>
</body>
</html>