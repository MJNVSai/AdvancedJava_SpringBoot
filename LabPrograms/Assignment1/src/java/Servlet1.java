import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*;  
import java.sql.*;

public class Servlet1 extends HttpServlet
{  
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res)  throws ServletException,IOException  
    {  
        res.setContentType("text/html");   //setting the content type  or mine type
        PrintWriter pw=res.getWriter();     //get the stream to write the data 
        
        int id = Integer.parseInt(req.getParameter("id"));
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/it", "root", "");
            
            PreparedStatement ps = con.prepareStatement("select * from javaemployee where id = ?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                pw.println("<center>");
                pw.println("<h1> Employeee Id : " + rs.getInt(1) + "</h1> <br> <br>");
                pw.println("<h1> Employeee Name : " + rs.getString(2) + "</h1> <br> <br>");
                pw.println("<h1> Employeee City : " + rs.getString(3) + "</h1> <br> <br>");
                pw.println("<h1> Employeee Company : " + rs.getString(4) + "</h1> <br> <br>");
                pw.println("<h1> Employeee Salary : " + rs.getFloat(5) + " Lakhs </h1> <br> <br>");
                pw.println("</center>");
            }
        }
        catch(Exception e)
        {
            pw.println("An unexcepted Error Occured");
        }
    }
}
