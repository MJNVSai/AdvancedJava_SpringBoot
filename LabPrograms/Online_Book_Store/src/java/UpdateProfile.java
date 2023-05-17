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

public class UpdateProfile extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
		
	HttpSession session = request.getSession(false);
        if(session == null)
        {
            response.sendRedirect("./index.html");
        }
        else
        {
            String name = request.getParameter("p1");
            String email = request.getParameter("p2");
            String pass = request.getParameter("p3");
            String cpass = request.getParameter("p4");
            
            String gender = request.getParameter("radio");
            
            String dob = request.getParameter("p5");
            String city = request.getParameter("p6");
            String country = request.getParameter("p7");
            String address = request.getParameter("p8");
//            out.println("Profile Updated");

            try
            {
                int status = 0;

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "");

                String insertquery = "update register set name = ?, pass = ?, cpass = ?, gender = ?, dob = ?, city = ?, country = ?, address = ? where email = ?";
                PreparedStatement ps = con.prepareStatement(insertquery);

                ps.setString(1, name);
                ps.setString(2, pass);
                ps.setString(3, cpass);
                ps.setString(4, gender);
                ps.setString(5, dob);
                ps.setString(6, city);
                ps.setString(7, country);
                ps.setString(8, address);
                ps.setString(9, email);

                status = ps.executeUpdate();
//                out.println(status);
                if(status > 0)
                {
//                    out.println("<h1> You Have Sucessfully Update The Profile </h1>");
                    request.getRequestDispatcher("Home").include(request, response);
                }
            }
            catch(Exception e)
            {
                out.println("<h1> An Error Occured " + e + "</h1>");
            }
        }
    }
}