/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

/**
 *
 * @author itadmin
 */
public class Servlet1 extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");          //setting the content type  
        PrintWriter pw = response.getWriter();         //get the stream to write the data
        
        int range = Integer.parseInt(request.getParameter("sal"));
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/it", "root", "");
            
            PreparedStatement ps = con.prepareStatement("select * from javaemployee where salary >= ?");
            ps.setInt(1, range);
            
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                pw.println("<center>");
                pw.println("<h1> Employeee Id : " + rs.getInt(1) + "</h1> <br> <br>");
                pw.println("<h1> Employeee Name : " + rs.getString(2) + "</h1> <br> <br>");
                pw.println("<h1> Employeee City : " + rs.getString(3) + "</h1> <br> <br>");
                pw.println("<h1> Employeee Company : " + rs.getString(4) + "</h1> <br> <br>");
                pw.println("<h1> Employeee Salary : " + rs.getFloat(5) + " Lakhs </h1> <br> <br>");
                pw.println("<hr>");
                pw.println("</center>");
            }
            
            pw.println("<center>");
            //pw.println("<h1> Execution Completed </h1>");
            pw.println("<br> <br>");
            pw.println("<form action = 'index.html'>");
            pw.println("<input type = 'submit' value = 'HOME' />");
            pw.println("</form>");
            pw.println("</center>");
        }
        catch(Exception e)
        {
            pw.println(e);
        }
    }
}
