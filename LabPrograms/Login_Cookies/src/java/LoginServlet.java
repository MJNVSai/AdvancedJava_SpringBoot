import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        request.getRequestDispatcher("link.html").include(request, response);
        
        String s1 = request.getParameter("un");
        String s2 = request.getParameter("up");
        
        if(s2.equals("admin"))
        {
            out.println("You Are Sucessfully Login");
            out.println("<br> Welcome " + s1);
            
            Cookie ck = new Cookie("name", s1);
            response.addCookie(ck);
        }
        else
        {
            out.println("Error !!! UserName Or password Must be Wrong Please Check It Again ");
            request.getRequestDispatcher("login.html").include(request, response);
        }
        out.close();
    }

}