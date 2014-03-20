			<div id="top-menu">
			<% 
			String userName = (String)session.getAttribute("userName");
			if(userName==null){			
			%>
				<div class="top-menu-right">	        		
	        		<input id="queryColumn" type="text" size="25"></input>
	        		<input id="buttonQuery" type="button" value="Search" onclick="search()"></input>	        		
	        	</div>	
				<div class="top-menu-left">
				   	<div class="top-menu-item" id="loginTopMenu"><a href="doLoginMain">Login</a></div>
				   	<div class="top-menu-item" id="registerTopMenu"><a href="doRegisterMain">Register</a></div>
				   	<div class="top-menu-item" id="homeTopMenu"><a href="index.html">Home</a></div>
			   	</div>			   	
			   	
			   				   	
			<% 
			} else {
			%>			   	
				<div class="top-menu-right">	        		
	        		<input id="queryColumn" type="text" size="25"></input>
	        		<input id="buttonQuery" type="button" value="Search" onclick="search()"></input>	        		
	        	</div>
				<div class="top-menu-left">
					<div class="top-menu-item" id="logoutTopMenu"><a href="doLogout">Logout</a></div>
					<div class="top-menu-item" id="logoutLoggedMenu"><a href="http://localhost:8080/preciproject/doLoginMain">Logged in as <%=userName %></a></div>
				   	<div class="top-menu-item" id="registerTopMenu"><a href="doRegisterMain">Register</a></div>
				   	<div class="top-menu-item" id="homeTopMenu"><a href="index.html">Home</a></div>
			   	</div>
			   	
			<%
			}
			%>	
										
			</div>