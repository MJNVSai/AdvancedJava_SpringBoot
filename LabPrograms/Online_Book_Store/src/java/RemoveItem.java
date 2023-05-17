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

public class RemoveItem extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
//	request.getRequestDispatcher("header.html").include(request, response);
//	request.getRequestDispatcher("link.html").include(request, response);
		
	HttpSession session = request.getSession(false);
        if(session == null)
        {
            response.sendRedirect("./index.html");
        }
        else
        {
            String email = (String)session.getAttribute("email");
//	    out.print("<span style='float:right'> <h1 style='font-style: cursive; font-size: 20px; letter-spacing: 2px; color: darkblue;'> <b> Hi," +email+ "</b> </h1> </span>");
//	    out.print("<h1 style='font-style: cursive;'> <strong> Home <strong> </h1>");
            
            try
            {
                String isb = request.getParameter("aisb");
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "");
//                out.print("<h1 style='font-style: cursive;'> <strong> Home " +isb+ "<strong> </h1>");

                String qur = "update newbook set trash = 'yes' where isbn = ?";
                PreparedStatement ps = con.prepareStatement(qur);
                ps.setString(1, isb);
                int i = ps.executeUpdate();
                
                if(i > 0)
                {
                    request.getRequestDispatcher("Home").include(request, response);
                }
                con.close();
            }
            catch(Exception e)
            {
                out.println("<h1> An Error Occured " + e + "</h1>");
            }
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
	doGet(request,response);
    }
}