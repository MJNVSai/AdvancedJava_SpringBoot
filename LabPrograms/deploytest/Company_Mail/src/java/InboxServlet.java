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

public class InboxServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	request.getRequestDispatcher("header.html").include(request, response);
	request.getRequestDispatcher("link.html").include(request, response);
		
	HttpSession session = request.getSession(false);
        if(session == null)
        {
            response.sendRedirect("./index.html");
        }
        else
        {
            String email = (String)session.getAttribute("email");
            String mainrec = (String)session.getAttribute("Torec");
	    out.print("<span style='float:right'> <h2> <b> Hi, " + email + "</b> </h2> </span>");
	    out.print("<h1> <strong> Inbox <strong> </h1>");
			
	    String msg = (String)request.getAttribute("msg");
            if(msg != null)
           {
		out.print("<h3> <b> " +msg+ "</b> </h3>");
	   }
           
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/companymail", "root", "");
                
                String selectsql = "select * from company_mailer_message where sender = ? and trash = 'no' order by id desc";
                PreparedStatement ps = con.prepareStatement(selectsql);
                //ps.setString(1, "manju@gmail.com");
                ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
                
                out.println("<center>");
                out.print("<table border='1' style='width:700px;'>");
		out.print("<tr style='background-color:grey;color:white'><td> <center> Sender </center> </td><td> <center> Subject </center> </td></tr>");
		while(rs.next())
                {
                    out.print("<tr><td>" +rs.getString(2)+ "</td><td> <a href='ViewMailServlet?id=" +rs.getString(1)+ "'>" +rs.getString(4)+ "</a></td></tr>");
		}
		out.print("</table>");
                out.println("</center>");
				
		con.close();
            }
            catch(Exception e)
            {
                out.println("<h1> An Error Occured " + e + "</h1>");
            }
        }
        request.getRequestDispatcher("footer.html").include(request, response);
	out.close();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
	doGet(request,response);
    }
}