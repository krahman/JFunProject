<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/regStyle.css" type="text/css"></link>
<script type="text/javascript" src="./resources/jscripts/eventHandler.js">
</script>
<title>Register</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/WEB-INF/view/include/loginmenubar.jsp" flush="true"></jsp:include>		
		<div id="header-section">
			<a href="index.html"></a>
		</div>
		<%String userName = (String)session.getAttribute("userName"); 
		if (userName != null || !userName.trim().equals("")){%>
		<jsp:include page="/WEB-INF/view/include/ContentFiltering.jsp" flush="true"></jsp:include>
		<%}%>
		<div id="menus"> 
			<div class="menu-item"><label>PROFILE</label></div>
			<div class="menu-item"><label>BOOKMARKS</label></div>
			<div class="menu-item"><label>SAVE LINK</label></div>
			<div class="menu-item"><label>PEOPLE</label></div>
			<div class="menu-item"><label>TAGS</label></div>			
		</div>
		<div id="middle-column">			
			<div class="tab-panel">
				<div class="tab-panel-content" id="one">
					<div class="middle-column-box-white">
						<div class="middle-column-box-title-grey">You are registered.</div>						
						<div class="go-to-login"><a href="login.html" onclick="doLogin">Goto Login</a></div>			        	
			        	<div id="home-content"></div>			        	 	  	
					</div>
				</div>
				<div class="tab-panel-content" id="two">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Bookmarks</div>
        				<p>List of Bookmarks here</p>
        				<div id="bookmarks-content"></div> 	  	
					</div>
				</div>
				<div class="tab-panel-content" id="three">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Save Links</div>
        				<p>Insert link:</p>
        				<div id="saveLink">
        					<form>
								<div id="save-left">
									<div class="save-link-left"><label>Title</label></div>									
									<div class="save-link-left"><label>Link</label></div>
									<div class="save-link-left"><label>Tag</label></div>
									<div class="save-link-left"><label>Description</label></div>
								</div>								
								<div id="save-right">
									<div class="save-link-right"><input type="text" id="title"></input></div>									
									<div class="save-link-right"><input type="text" link="link"></input></div>
									<div class="save-link-right"><input type="text" tag="tag"></input></div>
									<div class="save-link-right"><textarea type="text" description="description"></textarea></div>
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
        				<div id="people-content"></div> 	  	
					</div>
				</div>
				<div class="tab-panel-content" id="five">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Tags</div>
        				<p>List of tags is here</p>
        				<div id="tags-content"></div> 	  	
					</div>
				</div>
			</div>						
		</div>
		<div id="footer">Share-IT <br> Nanyang Technological University <br> &copy 2008</div>
	</div>
</body>
</html>