import java.io.*;
import java.sql.*;
import java.lang.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

//main code starts from here

public class Transfer extends HttpServlet
{
    
    Connection con;
    public void init(ServletConfig config) throws ServletException
    {
      try
      {
        String driver = config.getInitParameter("Driver");
        String url = config.getInitParameter("DriverUrl");
        String user = config.getInitParameter("Username");
        String pwd = config.getInitParameter("Password");
        
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, pwd);
      }
      catch(Exception e)
      {
        System.out.println(e);
      }     
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
    {
      Statement st = null;
      int sbaccno = Integer.parseInt(req.getParameter("sbno"));
      int caccno = Integer.parseInt(req.getParameter("cbno"));
      float amount = Float.parseFloat(req.getParameter("amount"));
      
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();
      
      out.println("<html>");
      //out.println("<body bgcolor='azure'>");
      try
      {
        st = con.createStatement();
        String sql1 = "update savings set balance = balance-" +amount+ "where accno=" +sbaccno;
        String sql2 = "update current set balance = balance+" +amount+ "where accno=" +caccno;
        
        if(sbaccno != caccno)
        {    
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);
            out.println("<h2> <b> <i> Funds Transfered Successfully ! </i> </b> </h2>");
        }
        else
        {
          out.println("Saving or Current Bank Account Number was incorrect. Please check and try again ! <a href='index.html'> Click here </a>");
        }
        
       
      }
      catch(Exception e)
      {
        System.out.println(e);
      }
      finally
      {
        try
        {
         if(st!=null)
         {
          st.close();
         }    
        }
        catch(Exception e)
        {
         e.printStackTrace();
        }    
      }
      out.println("</body>");
      out.println("</html>");
    }
    public void destroy()
    {
      try
      {
       if(con!=null)
       {
         con.close();
       }    
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }    
    }        
    
}