import java.io.*;
import javax.servlet.*;
import java.util.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet1 extends HttpServlet
{
    HttpSession session;
    String pCode, qty, clickButton;
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        session = req.getSession(true);
        clickButton = req.getParameter("submit");
        
        if(clickButton.equals("ADD ITEM"))
        {
            pCode = req.getParameter("pCode");
            qty = req.getParameter("qty");
            
            if(!pCode.equals("") || qty.equals(""))
            {
                session.setAttribute(pCode, qty);
                res.sendRedirect("./index.html");
            }
        }
        else if(clickButton.equals("REMOVE ITEM"))
        {
            pCode = req.getParameter("pCode");
            session.removeAttribute(pCode);
            res.sendRedirect("./index.html");
        }
        else if(clickButton.equals("SHOW ITEMS"))
        {
            Enumeration e = session.getAttributeNames();
            if(e.hasMoreElements())
            {
                pw.println("<h1> Your Shopping Cart Items </h1>");
                while(e.hasMoreElements())
                {
                    String code = (String)e.nextElement();
                    pw.println("<h1> Product Code : " + code + "</h1>");
                    pw.println("<h1> Quantity : " + session.getAttribute(code) + "</h1>");
                }
            }
            else
            {
                pw.println("<h1> No Items Please </h1>");
            }
        }
        else if(clickButton.equals("LOGOUT"))
        {
            session.invalidate();
            res.sendRedirect("./index.html");
        }
        else if(clickButton.equals("PAY AMOUNT"))
        {
            pw.println("<h1> Payment Logic Goes Here </h1>");
        }
        pw.close();
    }
}