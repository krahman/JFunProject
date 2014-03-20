package controller;
/**
 * @author khalilur
 *
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.*;

public class RegistrationServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException {
		HttpSession session = request.getSession();
		//session beans
		RegisterBeans nameBean = (RegisterBeans)session.getAttribute("nameBean");		
		if (nameBean == null) {
			nameBean = new RegisterBeans();
			session.setAttribute("nameBean", nameBean);			
		}	
		
		//set data to bean
		String firstName = request.getParameter("firstName");
		nameBean.setFirstName(firstName);
		String lastName = request.getParameter("lastName");
		nameBean.setLastName(lastName);
		String emailAddress = request.getParameter("emailAddress");
		nameBean.setEmailAddress(emailAddress);
		String userName = request.getParameter("userName");
		nameBean.setUserName(userName);
		String userPassword = request.getParameter("userPassword");
		nameBean.setUserPassword(userPassword);
		
		//session register
		RegisterClass register = (RegisterClass)session.getAttribute("register");
		if (register == null){
			register = new RegisterClass();
			session.setAttribute("register", register);
		}
		
		register.UserData(userName, userPassword, lastName, firstName, emailAddress);
		int errorNumber = register.getErrorNumber();
		String errorDescription = register.getErrorDescription();
		
		//redirect to register page	
		String urlEncoding = null;
		if (errorNumber > 0){			
			session.setAttribute("errorNumber", errorNumber);
			session.setAttribute("errorDescription", errorDescription);			
			session.setAttribute("attr", userName);
			String address = "/WEB-INF/view/ShowException.jsp";
			urlEncoding = response.encodeURL(address);			
			RequestDispatcher dispatcher = request.getRequestDispatcher(urlEncoding);
			dispatcher.forward(request, response);			
		} else {
			session.setAttribute("userName", userName);
			urlEncoding = response.encodeURL("doRegisterMain");
			response.sendRedirect(urlEncoding);
		}
		
	}
	
}
