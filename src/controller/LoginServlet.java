package controller;
/**
 * @author khalilur
 *
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginClass;

public class LoginServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException
	{
		
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
			
		// check whether the fields are complete
		if(isMissing(userName)){
			response.sendRedirect("login.html");
			return;
		}
		
		if(isMissing(userName) || isMissing(userPassword)){
			response.sendRedirect("login.html");
			return;
		}
		
		// Verify credentials (user name == password)
		if(!isTrue(userName, userPassword)){
			response.sendRedirect("login.html");
			return;
		}
		
		// Create new session and store the user id.
		// Then send user to main page
		HttpSession session = request.getSession();
		session.setAttribute("userName", userName);
		String urlEncoding = response.encodeURL("doLoginMain");
		response.sendRedirect(urlEncoding);		
	}

	private boolean isMissing(String param){
		return((param == null) || (param.trim().equals("")));
	}
	
	private boolean isTrue(String username, String password){
		LoginClass login = new LoginClass();		
		return(login.Authenticate(username, password));		
	}
			
}
