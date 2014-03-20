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
import model.SendMessageClass;
import model.ShowTagsClass;

/**
 * Servlet implementation class SaveCommentServlet
 */
public class PushMessageServlet extends HttpServlet {	

	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException
	{   
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("userName");
		SendMessageClass msg = new SendMessageClass();
		TreeMap<Integer, List<String>> inboxes = msg.getInbox(username);
		TreeMap<Integer, List<String>> outboxes = msg.getOutbox(username);
		
		Iterator inboxKey = inboxes.keySet().iterator();
		Iterator outboxKey = outboxes.keySet().iterator();
		Object inboxObj;
		Object outboxObj;
		String messages = "<messages>";
		String inbox = messages;		
		while(inboxKey.hasNext()){
			inboxObj = inboxKey.next();
			List<String> inboxList = inboxes.get(inboxObj);
			inbox +="<inbox>";
			inbox += "<id>"+inboxList.get(0)+"</id>";
			inbox += "<recipient>"+inboxList.get(1)+"</recipient>";
			inbox += "<sender>"+inboxList.get(2)+"</sender>";
			inbox += "<message>"+inboxList.get(3)+"</message>";
			inbox += "<date>"+inboxList.get(4)+"</date>";
			inbox += "<time>"+inboxList.get(5)+"</time>";
			inbox += "<status>"+inboxList.get(6)+"</status>";
			inbox +="</inbox>";			
		}
		
		String outbox = inbox;		
		while (outboxKey.hasNext()){
			outboxObj = outboxKey.next();
			List<String> outboxList = outboxes.get(outboxObj);
			outbox += "<outbox>";
			outbox += "<id>"+outboxList.get(0)+"</id>";
			outbox += "<recipient>"+outboxList.get(1)+"</recipient>";
			outbox += "<sender>"+outboxList.get(2)+"</sender>";
			outbox += "<message>"+outboxList.get(3)+"</message>";
			outbox += "<date>"+outboxList.get(4)+"</date>";
			outbox += "<time>"+outboxList.get(5)+"</time>";
			outbox += "<status>"+outboxList.get(6)+"</status>";
			outbox += "</outbox>";
		}
		messages = outbox;
		messages += "</messages>";	
		
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(messages);            
             

	}

	private boolean isMissing(String param)
	{
		return((param == null) || (param.trim().equals("")));
	}	
	
}
