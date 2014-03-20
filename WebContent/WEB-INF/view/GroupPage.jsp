<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/style.css" type="text/css"></link>
<script type="text/javascript" src="./resources/jscripts/eventHandler.js"></script>
<script type="text/javascript" src="./resources/jscripts/showMyBookmark.js"></script>
<% String groupname = (String)session.getAttribute("groupname"); %>
<title><%=groupname.toUpperCase() %></title>
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
			<div class="menu-item"><label id="pageLabel"><%=groupname.toUpperCase() %> Group</label></div>							
		</div>
		<jsp:include page="/WEB-INF/view/include/headerarea.jsp" flush="true"></jsp:include>
		<div id="middle-column">
			<div class="tab-panel">
				<div class="tab-panel-content" id="one">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey" id="headerLabel">Advertisement</div>        				
        				<div class="BookCollection" id="bookShelf">
        				</div>
					</div>
					<div class="middle-column-box-white">        				
        				<div class="middle-column-box-title-yellow" id="headerLabel"><%=groupname.toUpperCase() %> Group</div>
        				<div id="groupDescription"></div> 
        				<div class="searchTags">
        					<form enctype='multipart/form-data' method='post' action='/preciproject/uploadFile'>
        						<p>Upload a file :</p>								
								<input type='file' size="60"></input>	        					
								<input type='submit' value='Upload'>
							</form>
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