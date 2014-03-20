<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/style.css" type="text/css"></link>
<script type="text/javascript" src="./resources/jscripts/eventHandler.js"></script>
<script type="text/javascript" src="./resources/jscripts/showMyBookmark.js"></script>
<title>Profile</title>
</head>
<body onload="showProfile()">
	<div id="wrapper">
		<jsp:include page="/WEB-INF/view/include/loginmenubar.jsp" flush="true"></jsp:include>
		<jsp:include page="/WEB-INF/view/include/ContentFiltering.jsp" flush="true"></jsp:include>
		<jsp:include page="/WEB-INF/view/include/searchbar.jsp" flush="true"></jsp:include>
		<div id="menus">			
			<div class="menu-item"><label>PROFILE</label></div>
			<div class="menu-item"><label onclick="showMyBookmarks()">BOOKMARKS</label></div>
			<div class="menu-item"><label>SAVE LINK</label></div>
			<div class="menu-item"><label>PEOPLE</label></div>
			<div class="menu-item"><label>TAGS</label></div>
			<div class="menu-item" id="myInbox"><label>INBOX</label></div>				
		</div>
		<jsp:include page="/WEB-INF/view/include/headerarea.jsp" flush="true"></jsp:include>
		<jsp:include page="/WEB-INF/view/include/contentareauser.jsp" flush="true"></jsp:include>	
		<jsp:include page="/WEB-INF/view/include/rightsidebaruser.jsp" flush="true"></jsp:include>		
		<jsp:include page="/WEB-INF/view/include/footerbar.jsp" flush="true"></jsp:include>
	</div>
</body>
</html>