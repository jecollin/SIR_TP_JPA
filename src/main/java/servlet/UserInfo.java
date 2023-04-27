package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserInfo
 */
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		writer.write("<html>\n<body>\n"
				+ "<h1>Summary of information</h1>\n"
				+ "<ul>\n"
				+ "<li>Name:" + request.getParameter("name")
				+ "</li>\n"
				+ "<li>First name: " + request.getParameter("firstname") + "</li>\n"
						+ "</ul>\n"
						+ "</body>\n"
						+ "<html>");
	}

}
