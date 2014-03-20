package controller;
/**
 * @author khalilur
 *
 */
import java.io.*;
import java.util.*;
import java.net.URLDecoder;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	private static final String BASE_DIRECTORY = "/preciproject/data";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
			servletFileUpload.setSizeMax(-1);
			
			try {
				String directory = "";
				// Parse the request
				List items = servletFileUpload.parseRequest(request);
				// Process the uploaded items
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					
		          // the param tag directory is sent as a request parameter to
		          // the server
		          // check if the upload directory is available
					if (item.isFormField()) {
						String name = item.getFieldName();
						if (name.equalsIgnoreCase("directory")) {
							directory = item.getString();
						}
						// retrieve the files
						System.out.println("test");
					} else {

						// the fileNames are urlencoded
						String fileName = URLDecoder.decode(item.getName(), "UTF-8");
						File file = new File(directory, fileName);
						file = new File(BASE_DIRECTORY, file.getName());

						// retrieve the parent file for creating the directories
						File parentFile = file.getParentFile();

						if (parentFile != null) {
							parentFile.mkdirs();
						}

						// writes the file to the filesystem
						
						item.write(file);
						String address = "WEB-INF/view/ForumPage.jsp";		
						String urlEncoding = response.encodeURL(address);		
						RequestDispatcher dispatcher = request.getRequestDispatcher(urlEncoding);
						dispatcher.forward(request, response);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

			response.setStatus(HttpServletResponse.SC_OK);

		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
