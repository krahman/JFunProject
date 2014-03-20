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
public class ShowProfileServlet extends HttpServlet {	

	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException
	{   
		HttpSession session = request.getSession();
		if(session == null)
		{
			response.sendRedirect("register.html");
			return;
		}
		
		// If session exists but userid does not, go back to login
		String userName = (String)session.getAttribute("userName");		
		if(isMissing(userName))
		{
			response.sendRedirect("register.html");
			return;
		}
		
		// START XML FILE
		
		// get profile table
		ProfileClass profile = new ProfileClass();
		List<String> detail = profile.getProfile(userName);
		String details = ""; 
		for (int i = 0; i < detail.size(); i++){
			details +="<detail>";
			details +="<address>"+detail.get(0)+"</address>";
			details +="<dob>"+detail.get(1)+"</dob>";
			details +="<program>"+detail.get(2)+"</program>";
			details +="<school>"+detail.get(3)+"</school>";
			details +="<faculty>"+detail.get(4)+"</faculty>";
			details +="</detail>";
		}
		
		// get bookmark table
		BookmarkClass bookmark = new BookmarkClass();		
		TreeMap<Integer, List<String>> bookmarks = bookmark.getBookmarks(userName);		
		Iterator bmKey = bookmarks.keySet().iterator();
		Object bmObj;		
		String listofboookmarks = details;			
		while(bmKey.hasNext()){
			bmObj = bmKey.next();
			
			List<String> lBookmark = bookmarks.get(bmObj);
			//System.out.println(lBookmark);
			listofboookmarks +="<bookmarks>";		
			listofboookmarks +="<uri>"+lBookmark.get(0)+"</uri>";
			listofboookmarks +="<title>"+lBookmark.get(1)+"</title>";
			listofboookmarks +="<description>"+lBookmark.get(2)+"</description>";
			listofboookmarks +="</bookmarks>";
		}
				
		UserClass user = new UserClass();		
		List<String> personal = user.getDetail(userName);
		String me = "";
		me +="<me>";
		me +="<fname>"+personal.get(0)+"</fname>";
		me +="<lname>"+personal.get(1)+"</lname>";
		me +="<email>"+personal.get(2)+"</email>";
		me += listofboookmarks;
		me +="</me>";						
		
		String myProfile = "<profile>";		
		myProfile += me;
		myProfile +="</profile>";
		// END OF XML FILE
		
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(myProfile);            
             

	}

	private boolean isMissing(String param)
	{
		return((param == null) || (param.trim().equals("")));
	}	
	
}
