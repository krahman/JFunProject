<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
			<%
			String userName = (String)session.getAttribute("userName");
			if (userName == null){
			%>
			<div id="menus"> 
				<div class="menu-item"><label>ALL BOOKMARKS</label></div>
				<div class="menu-item"><label>EXPLORE TAGS</label></div>			
			</div>
			<%} else { %>
			<div id="menus"> 
				<div class="menu-item"><label>ALL BOOKMARKS</label></div>
				<div class="menu-item"><label>EXPLORE TAGS</label></div>						
			</div>
			
			<%
			}
			%>