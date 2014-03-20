<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/loginStyle.css" type="text/css"></link>
<title>Logged Out</title>
</head>
<body>
<% String userName = (String)session.getAttribute("userName"); %>
	<div id="wrapper">
		<div id="top-menu">			
				<div class="top-menu-right">	        		
	        		<input id="queryColumn" type="text" size="25"></input>
	        		<input id="buttonQuery" type="button" value="Search" onclick="search()"></input>	        		
	        	</div>	
				<div class="top-menu-left">
				   	<div class="top-menu-item" id="loginTopMenu"><a href="doLoginMain">Login</a></div>
				   	<div class="top-menu-item" id="registerTopMenu"><a href="doRegisterMain">Register</a></div>
				   	<div class="top-menu-item" id="homeTopMenu"><a href="index.html">Home</a></div>
			   	</div>			
		</div>
		<div id="header-section">			
		</div>
		<div id="middle-column">
			<div class="middle-column-box-white">				
				<div class="middle-column-box-title-grey">Good Bye, <%=userName %></div>				
				<p>Signed Out! </p>
				<div class="go-to-login"><a href="login.html">Goto Login</a></div>											 				
			</div>					
				
		</div>
		<div id="footer">Share-IT <br> Nanyang Technological University <br> &copy 2008</div>
	</div>
</body>
</html>