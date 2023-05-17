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

public class Register extends HttpServlet
{   
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
//	request.getRequestDispatcher("./header.html").include(request, response);
        
        String name = request.getParameter("p1");
        String email = request.getParameter("p2");
        String pass = request.getParameter("p3");
        String cpass = request.getParameter("p4");
        String gender = request.getParameter("radio");
        String dob = request.getParameter("p5");
        String city = request.getParameter("p6");
        String country = request.getParameter("p7");
        String address = request.getParameter("p8");
        
        try
        {
            int status = 0;
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "");
            
            String insertquery = "insert into register values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertquery);
            
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setString(4, cpass);
            ps.setString(5, gender);
            ps.setString(6, dob);
            ps.setString(7, city);
            ps.setString(8, country);
            ps.setString(9, address);
            
            status = ps.executeUpdate();
            if(status > 0)
            {
                out.println("<h1> You are Sucessfully Registred </h1>");
                request.getRequestDispatcher("index.html").include(request, response);
            }
        }
        catch(Exception e)
        {
            out.println("<h1> An Error Occured " + e + "</h1>");
        }
    }
}