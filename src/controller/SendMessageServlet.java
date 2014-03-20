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

import com.sun.xml.internal.ws.client.dispatch.DispatchContext.MessageClass;

import model.ForumClass;
import model.GroupClass;
import model.SaveCommentClass;
import model.SendMessageClass;

/**
 * Servlet implementation class SaveCommentServlet
 */
public class SendMessageServlet extends HttpServlet {	

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
    	String recipient = request.getParameter("recipient");    	
		String message = request.getParameter("description");		
		
		SendMessageClass messageClass = new SendMessageClass();
		messageClass.sendMessage(recipient, userName, message);
		messageClass.saveMessage(recipient, userName, message);
		
		int errorNumber = messageClass.getErrorNumber();		
		String errorDescription = messageClass.getErrorDescription();
		
		if (errorNumber > 0)			
			feedback = "Error : "+errorNumber+"("+errorDescription+")";
		else 		
			feedback = "Message has been sent successfully, to "+recipient.toUpperCase();;
		
        if (!(recipient.equals("") | recipient == null)) {
        	response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(feedback);            
        } else {
        	return; 
        }

	}

	private boolean isMissing(String param)
	{
		return((param == null) || (param.trim().equals("")));
	}	
	
}
