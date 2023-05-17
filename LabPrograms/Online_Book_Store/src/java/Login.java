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

public class Login extends HttpServlet
{   
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	request.getRequestDispatcher("./header.html").include(request, response);
        
        String email = request.getParameter("p1");
        String pass = request.getParameter("p2");
        
        try
        {
            boolean status = false;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "");
            
            String checkquery = "select * from register where email = ? and pass = ?";
            PreparedStatement ps = con.prepareStatement(checkquery);
            
            ps.setString(1, email);
            ps.setString(2, pass);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                status = true;
            }
            
            if(status)
            {
               request.getSession().setAttribute("login", "true");
	       request.getSession().setAttribute("email", email);
               response.sendRedirect("Home");
            }
            else
            {
               out.print("<h1> Sorry, username or password Invalid </h1>");
	       request.getRequestDispatcher("index.html").include(request, response);
            }
        }
        catch(Exception e)
        {
            out.println("<h1> An Error Occured " + e + "</h1>");
        }
    }
}