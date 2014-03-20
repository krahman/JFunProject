package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PopularListClass;
import model.SaveBookmarkClass;
import model.SaveCommentClass;

import controller.SaveCommentServlet;
/**
 * Servlet implementation class SaveCommentServlet
 */
public class FriendBookmarkServlet extends HttpServlet {	

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException
	{    	
		String requestParam = request.getParameter("username");
		PopularListClass popularList = new PopularListClass();
		TreeMap<Integer, List<String>> friendBookmarks=  popularList.getFriendBookmarks(requestParam);
				
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
		String listOfXmlData = "";		
		
		Iterator iterator = friendBookmarks.keySet().iterator();
		Object obj;		
		listOfXmlData = "<list>";
		while (iterator.hasNext()){			
			// populate tree key and values 
			obj = iterator.next();			
			
			l = friendBookmarks.get(obj);
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
			if(isHasComment(Integer.parseInt(id))){
				xmlData += getCommentList(Integer.parseInt(id));
			}
			if(!isMissing(tag))
				xmlData += getTagList(tag, id);
			
			xmlData += "</bookmark>";		
			listOfXmlData += xmlData;		
			
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
	
	private boolean isHasComment(int id){
		boolean hasComment=false;
		TreeMap<Integer, List<String>> commentList = new TreeMap<Integer, List<String>>();
		SaveCommentClass saveComment = new SaveCommentClass();	
		commentList = saveComment.getCommentList(id);
		if (commentList.isEmpty()) hasComment = false; else hasComment = true;
			
		return hasComment;
	}
	
	private String getCommentList(int id){
		String xmlData=null;
		SaveCommentServlet saveComment = new SaveCommentServlet();
		xmlData = saveComment.getCommentList(id);
		return xmlData;
	}
	
	private String getTagList(String tag, String id){
		String xmlData=null;
		SaveBookmarkServlet saveBookmark = new SaveBookmarkServlet();
		xmlData = saveBookmark.getTagList(tag, id);
		return xmlData;
	}
}
