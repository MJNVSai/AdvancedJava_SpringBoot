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

public class RegisterServlet extends HttpServlet
{   
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	request.getRequestDispatcher("header.html").include(request, response);
		
        String rid = request.getParameter("regid");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String email = request.getParameter("email") + "@cmailer.com";
	String gender = request.getParameter("gender");
	//String dob = request.getParameter("dob");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        
	String addressLine = request.getParameter("addressLine");
	String city = request.getParameter("city");
	String state = request.getParameter("state");
	String country = request.getParameter("country");
	String contact = request.getParameter("contact");
        
        try
        {
            int status = 0;
            //java.sql.Date sqlDOB = java.sql.Date.
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//            java.util.Date ced = null;
//            ced = formatter.parse(dob);
//            java.sql.Date sqldob = new java.sql.Date(ced);

            Date sqlDob = Date.valueOf(dob);
            
            // Getting Current Date
            java.sql.Date sqlCurrentDate = null;
            java.util.Date utilDate = java.util.Calendar.getInstance().getTime();
	    sqlCurrentDate = new java.sql.Date(utilDate.getTime());

            
            String insertsql = "insert into company_mailer_user(id,name,email,password,gender,dob,addressLine,city,state,country,contact,registereddate,authorized) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/companymail", "root", "");
            PreparedStatement ps = con.prepareStatement(insertsql);
            
            ps.setString(1, rid);
            ps.setString(2, name);
	    ps.setString(3, email);
	    ps.setString(4, password);
	    ps.setString(5, gender);
	    ps.setDate(6, sqlDob);
	    ps.setString(7, addressLine);
	    ps.setString(8, city);
	    ps.setString(9, state);
	    ps.setString(10, country);
	    ps.setString(11, contact);
	    ps.setDate(12, sqlCurrentDate);
	    ps.setString(13, "yes");
            
            status = ps.executeUpdate();
            
            if(status > 0)
            {
                out.println("<h1> You are Sucessfully Registred </h1>");
                request.getRequestDispatcher("login.html").include(request, response);
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