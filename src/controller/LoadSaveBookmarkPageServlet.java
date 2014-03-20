package controller;
/**
 * @author khalilur
 *
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*; 

public class LoadSaveBookmarkPageServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			response.sendRedirect("login.html");
			return;
		}
			
		String userName = (String)session.getAttribute("userName");
		if(isMissing(userName))
		{
			response.sendRedirect("login.html");
			return;
		}
		String title = request.getParameter("title");
		String link = request.getParameter("link");
		String description = request.getParameter("description");
		session.setAttribute("title", title);
		session.setAttribute("link", link);
		session.setAttribute("description", description);
		String address = "WEB-INF/view/SaveBookmarkPage.jsp";		
		String urlEncoding = response.encodeURL(address);		
		RequestDispatcher dispatcher = request.getRequestDispatcher(urlEncoding);
		dispatcher.forward(request, response);	
		
	}

	private boolean isMissing(String param)
	{
		return((param == null) || (param.trim().equals("")));
	}
}
