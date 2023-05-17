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

public class ViewMailServlet extends HttpServlet
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
			
	    int id = Integer.parseInt(request.getParameter("id"));
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/companymail", "root", "");
                
                String selectview = "select * from company_mailer_message where id = ?";
                PreparedStatement ps = con.prepareStatement(selectview);
                ps.setInt(1, id);
                
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    out.print("<style>");
                    out.println(".anchor{padding: 5px; margin: 10px; border-radius: 9px; border-color: blue; border-style: double; border-width: 2px; color: crimson; text-decoration: none; letter-spacing: 2px;}");
                    out.println("</style>");
                    
                    out.print("<h1> <b> " +rs.getString("subject")+ "</b> </h1> <hr/>");
                    
                    out.print("<center>");
                    out.print("<p><b> <h3> Message: </h3> </b> " +rs.getString("message")+ "</p>");
                    out.println("<p> <b> Sender : </b> " +rs.getString("sender")+ "</p> ");
                    out.println("<p> <b> Receiver : </b> " +rs.getString("receiver")+ "</p> ");

                    out.print("<p><a href='DeleteMailServlet?id=" +rs.getString(1)+ "' class='anchor'> Delete Mail </a></p>");
                    out.print("</center>");
									
		}
				
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