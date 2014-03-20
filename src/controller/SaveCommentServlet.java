package controller;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
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

import model.SaveCommentClass;

/**
 * Servlet implementation class SaveCommentServlet
 */
public class SaveCommentServlet extends HttpServlet {	

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
		
		
		String listOfXmlData="";
    	String bookmarkid = request.getParameter("bookmarkid");
		String commentField = request.getParameter("comment");		
		
		SaveCommentClass saveComment = new SaveCommentClass();
		saveComment.insertComment(Integer.parseInt(bookmarkid), userName, commentField);		
		
		listOfXmlData=getCommentList(Integer.parseInt(bookmarkid));
		
		int errorNumber = saveComment.getErrorNumber();		
		String errorDescription = saveComment.getErrorDescription();
				
		
        if ((bookmarkid != null) && (commentField != null)) {
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
	
	public String getCommentList(int id){
		SaveCommentClass saveComment = new SaveCommentClass();
		TreeMap<Integer, List<String>> commentList = new TreeMap<Integer, List<String>>();
		commentList = saveComment.getCommentList(id);
		String commentid = "";
		String bookmark_id = "";
		String username = "";
		String comment = "";
		String commenttime = "";
		String commentdate = "";
		String xmlData="";
		String listOfXmlData = ""; 
		List<String> l = null;
		
		Iterator iterator = commentList.keySet().iterator();
		Object obj;		
		listOfXmlData = "<commentList>";
		while (iterator.hasNext()){			
			// populate tree key and values 
			obj = iterator.next();			
			
			l = commentList.get(obj);
			// generate columns table
			commentid = l.get(0);
			bookmark_id = l.get(1);
			username = l.get(2);
			comment = l.get(3);
			commenttime = l.get(4);
			commentdate = l.get(5);			
			// setup xml document			
			xmlData="";
			xmlData=			
					"<commententry>" +
						"<commentid>"+commentid+"</commentid>" +
						"<bookmarkid>"+bookmark_id+"</bookmarkid>" +
						"<comment>"+comment+"</comment>" +
						"<user>"+username+"</user>" +
						"<time>"+commenttime+"</time>" +						
						"<date>"+commentdate+"</date>" +						
					"</commententry>";
						
			listOfXmlData += xmlData;	
			
		}
		listOfXmlData += "</commentList>";
		return listOfXmlData;

	}
}
