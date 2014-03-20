package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PopularListClass;
import model.SaveBookmarkClass;
import model.SaveCommentClass;

/**
 * Servlet implementation class SaveCommentServlet
 */
public class SaveBookmarkServlet extends HttpServlet {	

	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
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
		String tag = "";
		String xmlData="";		
		
		SaveBookmarkClass saveBookmark = new SaveBookmarkClass();
    			
		String inlink = request.getParameter("link");
		String intitle = request.getParameter("title");
		String intag = request.getParameter("tag");
		String indescription = request.getParameter("description");
		int share = Integer.parseInt(request.getParameter("share"));
		int inuserid = saveBookmark.getUserid(userName);
		System.out.println(inuserid+" :"+userName);
		saveBookmark.insertBookmark(inlink, intitle, indescription, inuserid, intag, share);		
		
		PopularListClass popularList = new PopularListClass();
		TreeMap<Integer, List<String>> tree =  popularList.getList();
		
		String listOfXmlData = "";	
		Iterator iterator = tree.keySet().iterator();
		Object obj;		
		listOfXmlData = "<list>";
		while (iterator.hasNext()){			
			// populate tree key and values 
			obj = iterator.next();			
			
			l = tree.get(obj);
			// generate columns table
			id = l.get(0);
			link = l.get(1);
			title = l.get(2);
			username = l.get(3);
			description = l.get(4);
			bookmarktime = l.get(5);
			bookmarkdate = l.get(6);
			tag = l.get(7);
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
					"<date>"+bookmarkdate+"</date>";			
			xmlData += "</bookmark>";
			if(!isMissing(tag))
				xmlData += getTagList(tag, id);
			listOfXmlData += xmlData;			
			//System.out.println(obj+":"+username+":"+bookmarktime);
		}		 
		listOfXmlData += "</list>";
		System.out.println("Bookmarklist loaded.");
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
	
	public String getTagList(String tag, String id){				
		String xmlData="";
		String listOfXmlData = "";			
		listOfXmlData = "<tagList>";
		
		String[] tags = tag.split(",");
		for (int i = 0; i < tags.length; i++){
			xmlData="";
			xmlData=						
				"<tagentry>" +						
				"<bookmarkid>"+id.toString()+"</bookmarkid>" +
				"<tagname>"+tags[i].toString().toLowerCase()+"</tagname>" +																	
			"</tagentry>";
			
			listOfXmlData += xmlData;
		}
		listOfXmlData += "</tagList>";
		return listOfXmlData;		

	}
}
