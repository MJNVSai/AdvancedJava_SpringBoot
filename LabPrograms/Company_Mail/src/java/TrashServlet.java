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

public class TrashServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	request.getRequestDispatcher("header.html").include(request, response);
        request.getRequestDispatcher("link.html").include(request, response);
		
	HttpSession session=request.getSession(false);
	if(session == null)
        {
            response.sendRedirect("index.html");
	}
        else
        {
            String email = (String)session.getAttribute("email");
	    out.print("<span style='float:right'> <h2> <b> Hi, " +email+ " </b> </h2> </span>");
            out.print("<h1> <b> Trash </b> </h1>");
            
            String msg = (String)request.getAttribute("msg");
	    if(msg != null)
            {
		out.print("<p>" +msg+ "</p>");
	    }
            
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/companymail", "root", "");
                
                String trashsql = "select * from company_mailer_message where receiver = ? OR sender = ? and trash = ? order by id desc";
                PreparedStatement ps = con.prepareStatement(trashsql);
                ps.setString(1, email);
		ps.setString(2, email);
		ps.setString(3, "yes");
				
		ResultSet rs = ps.executeQuery();
                
                out.print("<center>");
		out.print("<table border='2' style='width:700px;'>");
		out.print("<tr style='background-color:grey;color:white'><td> <center> Sender </center> </td><td> <center> Subject </center></td></tr>");
                
                while(rs.next())
                {
                    out.print("<tr><td>" +rs.getString("sender")+ "</td><td>" +rs.getString("subject")+ "</td></tr>");
                }
                out.print("</table>");
		out.print("</center>");
                
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
}