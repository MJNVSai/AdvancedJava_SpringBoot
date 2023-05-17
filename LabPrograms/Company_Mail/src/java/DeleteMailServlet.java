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

public class DeleteMailServlet extends HttpServlet
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
                
                String delquery = "update company_mailer_message set trash = ? where id = ?";
                PreparedStatement ps = con.prepareStatement(delquery);
                ps.setString(1, "yes");
		ps.setInt(2, id);
		int i = ps.executeUpdate();
                
                if(i > 0)
                {
                    request.setAttribute("msg","Mail successfully deleted!");
		    request.getRequestDispatcher("InboxServlet").forward(request, response);
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
