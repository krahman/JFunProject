package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class FileServlet extends HttpServlet
{
	/**
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException
	{
		String filename = request.getParameter("file");
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/xml");
		response.setHeader("Cache-Control", "no-cache");
		
        InputStream in = null;
        try
        {
        	in = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/"+filename)));
            int ch;
            while ((ch = in.read()) !=-1)
            {
                out.print((char)ch);                
            }
        }
        finally
        {
        	if (in != null)
        		in.close();  
        }
	}
}

