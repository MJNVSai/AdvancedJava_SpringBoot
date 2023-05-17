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

public class LoginServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	request.getRequestDispatcher("header.html").include(request, response);
		
	String email = request.getParameter("email");
	String password = request.getParameter("password");
        
        try
        {
           boolean status = false; 
           
           Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost/companymail", "root", "");
           
           String selectsql = "select * from company_mailer_user where email = ? and password = ? and authorized = ?";
           PreparedStatement ps = con.prepareStatement(selectsql);
           
           ps.setString(1, email);
	   ps.setString(2, password);
	   ps.setString(3, "yes");
	   ResultSet rs = ps.executeQuery();
	   if(rs.next())
           {
		status = true;
	   }
           
           if(status)
           {
               request.getSession().setAttribute("login", "true");
	       request.getSession().setAttribute("email", email);
	       response.sendRedirect("InboxServlet"); //  Created The Servlet
               //response.sendRedirect("./index.html");
           }
           else
           {
               out.print("<h1> Sorry, username or password Invalid </h1>");
	       request.getRequestDispatcher("login.html").include(request, response);
           }
        }
        catch(Exception e)
        {
            out.println("<h1> An Error Occured " + e + "</h1>");
        }
        finally
        {
            request.getRequestDispatcher("footer.html").include(request, response);
	    out.close();
        }
    }
}