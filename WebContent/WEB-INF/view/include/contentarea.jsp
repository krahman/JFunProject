<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
			<%
			String userName = (String)session.getAttribute("userName");
			if (userName == null) {
			%>
			<div id="middle-column">		
				<div class="tab-panel">
					<div class="tab-panel-content" id="one">
						<div class="middle-column-box-white">
				        	<div class="middle-column-box-title-grey">All Bookmarks</div>
				        	<div id="popularList"></div>				        				        												        				        	 
						</div>
					</div>
					<div class="tab-panel-content" id="two">
						<div class="middle-column-box-white">
	        				<div class="middle-column-box-title-grey">Explore Tags</div>	        				
	        				<div class="searchTags">
	        					<input id="tagname" type="text" size="60"></input>
	        					<input type="button" value="Explore" onclick="getTagsList();"></input>
	        				</div>
	        				<div id="exploreTags"></div> 	  	
						</div>
					</div>				
				</div>									
			</div>
			<%} else {%>
			<div id="middle-column">		
				<div class="tab-panel">
					<div class="tab-panel-content" id="one">
						<div class="middle-column-box-white">
				        	<div class="middle-column-box-title-grey">All Bookmarks</div>
				        	<div id="popularList"></div>				        												        				        	 
						</div>
					</div>
					<div class="tab-panel-content" id="two">
						<div class="middle-column-box-white">
	        				<div class="middle-column-box-title-grey">Explore Tags</div>	        				
	        				<div class="searchTags">
	        					<input id="tagname" type="text" size="60"></input>
	        					<input type="button" value="Explore" onclick="getTagsList();"></input>
	        				</div>
	        				<div id="exploreTags"></div> 	  	
						</div>
					</div>				
				</div>									
			</div>
			
			<%}%>