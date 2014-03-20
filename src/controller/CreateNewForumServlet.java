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

import model.ForumClass;
import model.GroupClass;
import model.SaveCommentClass;

/**
 * Servlet implementation class SaveCommentServlet
 */
public class CreateNewForumServlet extends HttpServlet {	

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
		
		
		String feedback="";
    	String forumname = request.getParameter("forumname");
		String description = request.getParameter("description");		
		
		ForumClass forum = new ForumClass();
		forum.setForum(forumname, description, userName);
		forum.joinForums(forumname, userName);
		
		
		int errorNumber = forum.getErrorNumber();		
		System.out.println(forum.getErrorDescription());
		if (!(errorNumber > 0)){			
			feedback = forumname;
			response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(feedback);
		} else {
			feedback="0";
			response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(feedback);
		}

	}

	private boolean isMissing(String param)
	{
		return((param == null) || (param.trim().equals("")));
	}	
	
}
