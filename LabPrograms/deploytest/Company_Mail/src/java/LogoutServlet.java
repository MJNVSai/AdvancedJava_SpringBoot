import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.SimpleDateFormat;
//import java.util.Formatter;
//import java.util.*;
import java.time.*;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	request.getRequestDispatcher("header.html").include(request, response);
        
        request.getSession().invalidate();
	out.print("<center> <h1> <b> You are successfully logged out! </b> </h1> </center>");
		
	request.getRequestDispatcher("login.html").include(request, response);
		
	request.getRequestDispatcher("footer.html").include(request, response);
	out.close();
    }
}