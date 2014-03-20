package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MyBookmarksClass;
import model.PopularListClass;


/**
 * Servlet implementation class SaveCommentServlet
 */
public class MyBookmarksServlet extends HttpServlet {	

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException
	{    	
		// If no session exists, go back to login
    	HttpSession session = request.getSession(false);
		if(session == null)
		{
			response.sendRedirect("login.html");
			return;
		}
		
		// If session exists but username does not, go back to login
		String userName = (String)session.getAttribute("userName");
		if(isMissing(userName))
		{
			response.sendRedirect("login.html");
			return;
		}
		
		List<String> l = null;
		String id = "";
		String link = "";
		String title = "";
		String username = "";
		String description = "";
		String bookmarktime = "";
		String bookmarkdate = "";

		MyBookmarksClass myBookmarks = new MyBookmarksClass();
		TreeMap<Integer, List<String>> records =  myBookmarks.getList(userName);	
		
		String xmlData="";
		String listOfXmlData = "";		
		
		Iterator iterator = records.keySet().iterator();
		Object obj;		
		listOfXmlData = "<list>";
		while (iterator.hasNext()){			
			// populate tree key and values 
			obj = iterator.next();			
			
			l = records.get(obj);
			// generate columns table
			id = l.get(0);
			link = l.get(1);
			title = l.get(2);
			username = l.get(3);
			description = l.get(4);
			bookmarktime = l.get(5);
			bookmarkdate = l.get(6);
			// setup xml document			
			xmlData="";
			xmlData=				
				"<bookmark>" +
					"<id>"+id+"</id>" +
					"<title>"+title+"</title>" +
					"<link>"+link+"</link>" +
					"<username>"+username+"</username>" +
					"<description>"+description+"</description>" +
					"<time>"+bookmarktime+"</time>" +
					"<date>"+bookmarkdate+"</date>" +
				"</bookmark>";		
			listOfXmlData += xmlData;			
			//System.out.println(obj+":"+username+":"+bookmarktime);
		}		 
		listOfXmlData += "</list>";
		
        if ((id != null) && (link != null) && (username != null) && 
        		(description != null) && (bookmarktime != null) 
        		&& (bookmarkdate != null)) {
        	response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(listOfXmlData);
        } else {
             return;
        }
	}

	private boolean isMissing(String param)
	{
		return((param == null) || (param.trim().equals("")));
	}

}
