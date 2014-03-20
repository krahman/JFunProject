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
import model.ShowTagsClass;
import model.TagCloudClass;

/**
 * Servlet implementation class SaveCommentServlet
 */
public class FriendTagCloudServlet extends HttpServlet {	

	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException
	{   
		
		String userName = request.getParameter("username"); 
		List<String> l = null;
		String id = "";
		String tag = "";
		String xmlData="";		
		
		TagCloudClass tagCloud = new TagCloudClass();
		TreeMap<Integer, List<String>> tagCloudList =  tagCloud.getMyTagCloud(userName);
		
		String listOfXmlData = "<tagcloud>";	
		Iterator iterator = tagCloudList.keySet().iterator();
		Object obj;		
		
		while (iterator.hasNext()){			
			// populate tree key and values 
			obj = iterator.next();			
			
			l = tagCloudList.get(obj);
			// generate columns table
			id = l.get(0);
			tag = l.get(1);
			// setup xml document			
			xmlData = getTagList(tag, id);
			listOfXmlData += xmlData;	
		}
		listOfXmlData +="</tagcloud>";		
		System.out.println("TagCloud loaded.");
        
		response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(listOfXmlData);
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
	
	public String getTagList(String tag, String id){				
		String xmlData="";
		String listOfXmlData = "<tagEntryDoc>";	
		String[] tags = tag.split(",");
		for (int i = 0; i < tags.length; i++){
			xmlData="";
			xmlData=	"<tagEntryTerm>"+	
							"<bookmarkid>"+id.toString()+"</bookmarkid>" +
							"<tagname>"+tags[i].toString().toLowerCase()+"</tagname>"+																	
						"</tagEntryTerm>";
			listOfXmlData += xmlData;
		}				
		listOfXmlData+= "</tagEntryDoc>";
		return listOfXmlData;		

	}
}
