import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*;  
import java.sql.*;

public class loginservlet extends HttpServlet
{  
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res)  throws ServletException,IOException  
    {  
        res.setContentType("text/plain");//setting the content type  or mine type
        PrintWriter pw=res.getWriter();//get the stream to write the data  

        //writing html in the stream  
        //pw.println("<html><body>");  
        String name = req.getParameter("R1");
        String user = req.getParameter("R2");
        String pass = req.getParameter("R3");
        String repass = req.getParameter("R4");
        String gender = req.getParameter("inlineRadioOptions");
        String skills = req.getParameter("c1") + " " + req.getParameter("c2") + " " + req.getParameter("c3") + " " + req.getParameter("c4");
        String contact = req.getParameter("R5");
        String email = req.getParameter("R6");
        String college = req.getParameter("R7");
        
        pw.println("Sucessfully Saved The Registration Details Credentials");
        pw.println(name + " $$$ " + user + " $$$ " + pass + " $$$ " + repass + " $$$ " + gender + " $$$ " + skills + " $$$ " + contact + " $$$ " + email + " $$$ " + college);
        
       
        
        pw.close();//closing the stream  
    }
}  