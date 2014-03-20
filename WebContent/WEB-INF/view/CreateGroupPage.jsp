<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/style.css" type="text/css"></link>
<script type="text/javascript" src="./resources/jscripts/eventHandler.js"></script>
<script type="text/javascript" src="./resources/jscripts/showMyBookmark.js"></script>
<title>My Groups</title>
</head>
<body onload="myGroups()">
	<div id="wrapper">
		<jsp:include page="/WEB-INF/view/include/loginmenubar.jsp" flush="true"></jsp:include>
		<%String userName = (String)session.getAttribute("userName"); 
		if (userName != null || !userName.trim().equals("")){%>
		<jsp:include page="/WEB-INF/view/include/ContentFiltering.jsp" flush="true"></jsp:include>
		<%}%>
		<jsp:include page="/WEB-INF/view/include/searchbar.jsp" flush="true"></jsp:include>
		<div id="menus">			
			<div class="menu-item"><label>My Groups</label></div>							
		</div>
		<jsp:include page="/WEB-INF/view/include/headerarea.jsp" flush="true"></jsp:include>
		<div id="middle-column">
			<div class="tab-panel">
				<div class="tab-panel-content" id="one">
					<div class="middle-column-box-white">        				
        				<div class="middle-column-box-title-grey">My Groups</div>
        				<div id="myGroups"></div> 	 
        			</div>
        			<div class="middle-column-box-white">
        				<div class="middle-column-box-title-yellow">Find group</div>
        				<div id="goToGroups">
        					<p>Type a group name below :</p>								
								<input id="groupInput" type="text" size="60"></input>
	        					<input type="button" value="Find Group" onclick="findGroup()"></input>													
        				</div>
        				<div id="foundGroups"></div>      						        				        												        				        	 
					</div>
					<div class="middle-column-box-white">
						<div class="middle-column-box-title-blue">Create a new group</div>        				
        				<div class="createGroup">
        					<div id="entryForm">
	        					<p>Type the name of the group below :</p>							
								<input id="groupNameInput" type="text" size="60"></input>
		        				<p>Description :</p>	
								<textarea id="groupDescription" type="text" width="60"></textarea><br/>	        					
		        				<input type="button" value="Create" onclick="createGroup()"></input>
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