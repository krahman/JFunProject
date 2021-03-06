package controller;

import java.io.IOException;
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

import model.BookmarkClass;
import model.ForumClass;
import model.GroupClass;
import model.PopularListClass;
import model.ProfileClass;
import model.SaveBookmarkClass;
import model.SaveCommentClass;
import model.SendMessageClass;
import model.ShowTagsClass;
import model.TagClass;
import model.UserClass;

/**
 * Servlet implementation class SaveCommentServlet
 */
public class MyForumServlet extends HttpServlet {	

	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException
	{   
		HttpSession session = request.getSession();
		if(session == null)
		{
			response.sendRedirect("index.html");
			return;
		}
		
		// If session exists but userid does not, go back to login
		String userName = (String)session.getAttribute("userName");		
		if(isMissing(userName))
		{
			response.sendRedirect("index.html");
			return;
		}		
		
		// START XML DATA HERE
		
		ForumClass forum = new ForumClass();		
		TreeMap<Integer, List<String>> forums = forum.getMyForums(userName);		
		Iterator grKey = forums.keySet().iterator();
		Object grValue;		
		String listofforum = "<forums>";			
		while(grKey.hasNext()){
			grValue = grKey.next();			
			List<String> lForums = forums.get(grValue);			
			listofforum +="<forum>";		
			listofforum +="<id>"+grValue+"</id>";
			listofforum +="<forumname>"+lForums.get(0)+"</forumname>";
			listofforum +="<description>"+lForums.get(1)+"</description>";
			listofforum +="<date>"+lForums.get(2)+"</date>";
			listofforum +="<time>"+lForums.get(3)+"</time>";
			listofforum +="<creator>"+lForums.get(4)+"</creator>";
			listofforum +="</forum>";
		}
		String xmlForum = listofforum;
		xmlForum += "</forums>";
		// END OF XML FILE
		
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(xmlForum);            
             

	}

	private boolean isMissing(String param)
	{
		return((param == null) || (param.trim().equals("")));
	}	
	
}
