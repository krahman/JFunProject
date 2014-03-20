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
public class FindGroupServlet extends HttpServlet {	

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
		
		// get group list
		String groupname = request.getParameter("groupname");
		GroupClass group = new GroupClass();		
		TreeMap<Integer, List<String>> groups = group.findGroup(groupname);		
		Iterator grKey = groups.keySet().iterator();
		Object grValue;		
		String listofgroup = "";			
		while(grKey.hasNext()){
			grValue = grKey.next();			
			List<String> lGroups = groups.get(grValue);			
			listofgroup +="<group>";		
			listofgroup +="<id>"+grValue+"</id>";
			listofgroup +="<groupname>"+lGroups.get(0)+"</groupname>";
			listofgroup +="<description>"+lGroups.get(1)+"</description>";
			listofgroup +="<date>"+lGroups.get(2)+"</date>";
			listofgroup +="<time>"+lGroups.get(3)+"</time>";
			listofgroup +="<creator>"+lGroups.get(4)+"</creator>";
			listofgroup +="</group>";
		}
		// END OF XML FILE
		
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(listofgroup);            
             

	}

	private boolean isMissing(String param)
	{
		return((param == null) || (param.trim().equals("")));
	}	
	
}
