<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/loginStyle.css" type="text/css"></link>
<script type="text/javascript" src="./resources/jscripts/eventHandler.js"></script>
<title>Exception Page</title>
</head>
<body>
	<% int errorNumber = (Integer)session.getAttribute("errorNumber"); %>
	<% String errorDescription = (String)session.getAttribute("errorDescription"); %>
	<% String attr = (String)session.getAttribute("attr"); %>
	<div id="wrapper">
		<jsp:include page="/WEB-INF/view/include/loginmenubar.jsp" flush="true"></jsp:include>
		<%String userName = (String)session.getAttribute("userName"); 
		if (userName != null || !userName.trim().equals("")){%>
		<jsp:include page="/WEB-INF/view/include/ContentFiltering.jsp" flush="true"></jsp:include>
		<%}%>
		<jsp:include page="/WEB-INF/view/include/searchbar.jsp" flush="true"></jsp:include>
		<jsp:include page="/WEB-INF/view/include/headerarea.jsp" flush="true"></jsp:include>		
		<div id="middle-column">		
			<div class="middle-column-box-white">
				<div class="middle-column-box-title-grey">Failed</div>
				<div class="exception">
				<%if (errorNumber==1062){%>
					<p><strong>'<%=attr%>'</strong> is already exist.</p>					
				<%}else{%>					
					<p><%=errorDescription%></p>
				<%} %>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/include/footerbar.jsp" flush="true"></jsp:include>
	</div>
</body>
</html>