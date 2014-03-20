package controller;
/**
 * @author khalilur
 *
 */
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.ws.Response;

import model.DatabaseClass;

public class CheckSessionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {	
		
		DatabaseClass db = new DatabaseClass();
		if(db.isConnected()){
			String address ="WEB-INF/view/index.jsp";
			String urlEncoding = response.encodeURL(address);
			RequestDispatcher dispatcher = request.getRequestDispatcher(urlEncoding);
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			int errorNumber = db.getErrorNumber();
			String errorDescription = "The database is misconfigured. " +
						"Please setup database at '/model/SetupDatabaseClass.java' in your solution.";
			session.setAttribute("errorNumber", errorNumber);
			session.setAttribute("errorDescription", errorDescription);
			
			String address = "/WEB-INF/view/ShowException.jsp";			
			String urlEncoding = response.encodeURL(address);
			RequestDispatcher dispatcher = request.getRequestDispatcher(urlEncoding);
			dispatcher.forward(request, response);
		}
	}
}
