<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/loginStyle.css" type="text/css"></link>
<script type="text/javascript" src="./resources/jscripts/eventHandler.js"></script>
<title>Logged in</title>
</head>
<body>
	<div id="wrapper">
		<div id="top-menu">
			<div class="top-menu-item" id="topMenu1"><a href="doLogout">Logout</a></div>
			<div class="top-menu-item" id="topMenu2"><a href="help.html">Help</a></div>
			<div class="top-menu-item" id="topMenu3"><a href="learnmore.html">Learn more</a></div>
			<div class="top-menu-item" id="topMenu4"><a href="whatsnew.html">What's New</a></div>
			<div class="top-menu-item" id="topMenu5"><a> Logged in as <%= session.getAttribute("userName") %></a></div>
			<div class="top-menu-item" id="topMenu6"><a href="index.html">Home</a></div>			
		</div>
		<div id="header-section">
			<a href="index.html"><img id="header-background-left" src="" alt="logo"/></a>
		</div>
		<div id="menus"> 
			<div class="menu-item"><label>HOME</label></div>
			<div class="menu-item"><label>BOOKMARKS</label></div>
			<div class="menu-item"><label>PEOPLE</label></div>
			<div class="menu-item"><label>TAGS</label></div>			
		</div>
		<div id="middle-column">		
			<div class="tab-panel">
				<div class="tab-panel-content" id="one">				
					<div class="middle-column-box-white">
						<div class="middle-column-box-title-grey">Home</div>
						<p></p>
						<div class="go-to-login"><a href="doLoginMain" onclick="doLogin">Goto Login</a></div>			        	
			        	<div id="home-content"></div>			        	 	  	
					</div>				
				</div>
				<div class="tab-panel-content" id="two">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Bookmarks</div>
        				<p>${result}</p>
        				<div id="bookmarks-content"></div> 	  	
					</div>
				</div>				
				<div class="tab-panel-content" id="three">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">People</div>
        				<p>${result}</p>
        				<div id="people-content"></div> 	  	
					</div>
				</div>
				<div class="tab-panel-content" id="two">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Tags</div>
        				<p>${result}</p>
        				<div id="tags-content"></div> 	  	
					</div>
				</div>
			</div>
			<div class="middle-column-box-white">
											
			</div>				
				
		</div>
		<div id="footer">Share-IT <br> Nanyang Technological University <br> &copy 2008</div>
	</div>
</body>
</html>