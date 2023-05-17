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
        String s1 = req.getParameter("r1");
        String s2 = req.getParameter("r2");
        String s3 = req.getParameter("r3");
        String s4 = req.getParameter("r4");
        String s5 = (String)req.getParameter("r5");
        String s6 = req.getParameter("r6");
        String s7 = req.getParameter("r7");
        String s8 = req.getParameter("r8");
        String s9 = req.getParameter("r9");
        
        boolean status = false;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/it", "root", "");
            
            String checksql = "insert into register values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(checksql);
            ps.setString(1, s1);
            ps.setString(2, s2);
            ps.setString(3, s3);
            ps.setString(4, s4);
            ps.setString(5, s5);
            ps.setString(6, s6);
            ps.setString(7, s7);
            ps.setString(8, s8);
            ps.setString(9, s9);
            
            int result = ps.executeUpdate();
            while(result > 0)
            {
                status = true;
            }
            
            pw.print("<center> <h1> Your Registration is Sucessfull !!! </h1> </center>");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}