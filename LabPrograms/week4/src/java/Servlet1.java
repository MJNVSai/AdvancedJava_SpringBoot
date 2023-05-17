import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*;  
import java.sql.*;

public class Servlet1 extends HttpServlet
{  
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res)  throws ServletException,IOException  
    {  
        res.setContentType("text/html");//setting the content type  or mine type
        PrintWriter pw = res.getWriter();//get the stream to write the data  

        //writing html in the stream  
        //pw.println("<html><body>");  
        String s1 = req.getParameter("u1");
        String s2 = req.getParameter("u2");
        
        boolean status = false;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/it", "root", "");
            
            String checksql = "select * from login where user = ? and pass = ?";
            PreparedStatement ps = con.prepareStatement(checksql);
            ps.setString(1, s1);
            ps.setString(2, s2);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                status = true;
            }
            
            pw.print("<center> <h1> User Login Sucessfull </h1> </center>");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}