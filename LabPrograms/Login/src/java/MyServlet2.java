import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*;  
import java.sql.*;
public class MyServlet2 extends HttpServlet
{  
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res)  throws ServletException,IOException  
    {  
        res.setContentType("text/plain");//setting the content type  or mine type
        PrintWriter pw=res.getWriter();//get the stream to write the data  

        //writing html in the stream  
        //pw.println("<html><body>");  
        String s1 = req.getParameter("P1");
        String s2 = req.getParameter("P2");
        //pw.println("Sucessfully Saved The Login Credentials");  
        pw.println("UserName : " + s1);
        pw.println("Password : " + s2);
        //pw.println("</body></html>");  

        try
        {
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/it","root",""); 
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("select * from login where email = ? and password = ?");
            
            while(rs.next())
            {
                if(rs.wasNull())
                {
                    if(s1.equals(rs.getString(1)) && s2.equals(rs.getString(2)))
                    {
                        pw.println("s");
                    }
                    else
                    {
                        pw.println("us");
                    }
                }
            }
            
        }
        catch(Exception e)
        {
            pw.println("An Unknown Error Occured : " + e);
        }
        
        pw.close();//closing the stream  
    }
}  