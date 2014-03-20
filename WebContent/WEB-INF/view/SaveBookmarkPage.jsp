<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/style.css" type="text/css"></link>
<script type="text/javascript" src="./resources/jscripts/eventHandler.js"></script>
<script type="text/javascript" src="./resources/jscripts/showMyBookmark.js"></script>
<% String title = (String)session.getAttribute("title");
String link = (String)session.getAttribute("link");
String description = (String)session.getAttribute("description");%>
<title>Save Bookmark</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/WEB-INF/view/include/loginmenubar.jsp" flush="true"></jsp:include>
		<%String userName = (String)session.getAttribute("userName"); 
		if (userName != null || !userName.trim().equals("")){%>
		<jsp:include page="/WEB-INF/view/include/ContentFiltering.jsp" flush="true"></jsp:include>
		<%}%>
		<jsp:include page="/WEB-INF/view/include/searchbar.jsp" flush="true"></jsp:include>
		<div id="menus">			
			<div class="menu-item"><label>Save Bookmark</label></div>							
		</div>
		<jsp:include page="/WEB-INF/view/include/headerarea.jsp" flush="true"></jsp:include>
		<div id="middle-column">		
			<div class="tab-panel">				
				<div class="tab-panel-content" id="three">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Save Bookmark</div>        				
        				<div id="mySaveLink">      
        					<div class="mySaveForm">  				
	        					<form>
	        						<label for="user">Title</label>
									<input name="textTitle" type="text" id="title" value="<%=title%>"></input>
									
									<label for="emailaddress">Link:</label>
									<input type="text" name= "textLink" id="link" value="<%=link%>"></input>
									
									<label for="emailaddress">Tag</label>
									<input type="text" name= "textTag" id="tag"></input>
									
									<label for="comments">Description</label>
									<p>Please, fill 'Tag' with comma separated words.</p>
									<textarea name= "textDesc" id="desc"></textarea><br />
									
									<label for="terms">Don't share</label>
									<input type="checkbox" id="isSharing" name="sharing" class="boxes" /><br />
									<input type="button" id="submitbutton" value="Save" onclick="saveBookmark()">	
								</form>
							</div>
        				</div> 	  	
					</div>
				</div>
			</div>
		</div>	
		<jsp:include page="/WEB-INF/view/include/rightsidebar.jsp" flush="true"></jsp:include>		
		<jsp:include page="/WEB-INF/view/include/footerbar.jsp" flush="true"></jsp:include>
	</div>
</body>
</html>