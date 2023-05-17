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

public class ComposeServlet extends HttpServlet
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
			
	    String msg = (String)request.getAttribute("msg");
	    if(msg != null)
            {
               // request.setAttribute("Torec", email);
                out.print("<p>" +msg+ "</p>");
	    }
	    request.getRequestDispatcher("composeform.html").include(request, response);
        }
        
        request.getRequestDispatcher("footer.html").include(request, response);
	out.close();
    }
}