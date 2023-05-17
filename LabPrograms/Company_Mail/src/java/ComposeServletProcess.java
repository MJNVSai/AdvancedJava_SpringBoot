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

public class ComposeServletProcess extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	request.getRequestDispatcher("header.html").include(request, response);
        
        String receiver = request.getParameter("to");
        String id1 = request.getParameter("id1");
	String subject = request.getParameter("subject");
	String message = request.getParameter("message");
        message = message.replaceAll("\n", "<br>");
        String email = (String)request.getSession(false).getAttribute("email");
        
        try
        {
            int status = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/companymail", "root", "");
            
            String insertmsg = "insert into company_mailer_message(id,sender,receiver,subject,message,trash,messagedate) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(insertmsg);
            
            java.sql.Date sqlCurrentDate = null;
            java.util.Date utilDate = java.util.Calendar.getInstance().getTime();
            sqlCurrentDate = new java.sql.Date(utilDate.getTime());
            
            ps.setString(1, id1);
            ps.setString(2, email);
	    ps.setString(3, receiver);
	    ps.setString(4, subject);
	    ps.setString(5, message);
	    ps.setString(6, "no");
            ps.setDate(7, sqlCurrentDate);
            
            status = ps.executeUpdate();
            
            if(status > 0)
            {
                request.setAttribute("msg", "message successfully sent");
                request.setAttribute("Torec", receiver);
		request.getRequestDispatcher("ComposeServlet").forward(request, response);
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