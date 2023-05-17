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
import javax.servlet.http.HttpSession;

public class AddBook extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	request.getRequestDispatcher("header.html").include(request, response);
	request.getRequestDispatcher("link.html").include(request, response);
		
	HttpSession session = request.getSession(false);
        if(session == null)
        {
            response.sendRedirect("./index.html");
        }
        else
        {
            String email = (String)session.getAttribute("email");
	    out.print("<span style='float:right'> <h1 style='font-style: cursive; font-family: sans-serif; font-size: 20px; letter-spacing: 2px; color: darkblue;'> <b> Hi," +email+ "</b> </h1> </span>");
	    out.print("<h1 style='font-style: cursive; font-family: sans-serif;'> <strong> Add a new Book <strong> </h1>");
            
            
            out.print("<head>");
                out.print("    <title> Register Tab </title>");
                out.print("    <link rel='stylesheet' href='styles.css' >"); 
                out.print("    <link rel = 'stylesheet' href = 'https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css' >");
                out.print("</head>");
                
                out.print("<body>");
                out.print("<div class='glass' style='width:40%; height:80%;'>" + 
                        "<h3> New Book </h3>");
                out.print("");
                
                out.print("<form action='AddBook' method='get'>" + 
                            "<div class='inputWithIcon inputIconBg'>" + 
                            "<input type='text' name = 'p1' placeholder='Enter Book ISBN Number'>" +
                            "<i class='fa fa-book fa-lg fa-fw' aria-hidden='true'></i>" +
                            "</div>");
                    out.print("");
                    
                    out.print("<div class='inputWithIcon inputIconBg'>" +
                            "<input type='text' name = 'p2' placeholder='Enter Book Title Name'>" +
                            "<i class='fa fa-book fa-lg fa-fw' aria-hidden='true'></i>" + 
                            "</div>");
                    out.print("");
                    
                    
                    out.print("<div class='inputWithIcon inputIconBg'>" +
                            "<input type='text' name = 'p3' placeholder='Enter Author's Name'>" +
                            "<i class='fa fa-user fa-lg fa-fw' aria-hidden='true'></i>" + 
                            "</div>");
                    out.print("");
                    
                    out.print("<div class='inputWithIcon inputIconBg'>" +
                            "<input type='text' name = 'p4' placeholder='Category Name'>" +
                            "<i class='fa fa-list-alt fa-lg fa-fw' aria-hidden='true'></i>" + 
                            "</div>");
                    out.print("");
                    
                    out.print("<div class='inputWithIcon inputIconBg'>" +
                            "<input type='text' name = 'p5' placeholder='Qantity'>" +
                            "<i class='fa fa-sort-amount-desc fa-lg fa-fw' aria-hidden='true'></i>" + 
                            "</div>");
                    out.print("");
                    
                    out.print("<div class='inputWithIcon inputIconBg'>" +
                            "<input type='text' name = 'p6' placeholder='Book Price'>" +
                            "<i class='fa fa-money fa-lg fa-fw' aria-hidden='true'></i>" + 
                            "</div>");
                    out.print("");
                    
                    
                    out.print("<p> <button type='submit' style='margin-bottom:10px;'> ADD </button> </p>");
                    
                    out.print("        </form>");
                    out.print("    </div> <br> <br> <br> <br>");
                    out.print("</body>");
            
            try
            {
                
                String isbn = request.getParameter("p1");
                String title = request.getParameter("p2");
                String author = request.getParameter("p3");
                String category = request.getParameter("p4");
                String quantity = request.getParameter("p5");
                float price = Float.parseFloat(request.getParameter("p6"));
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "");
                
                String cq = "insert into newbook values(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(cq);
                ps.setString(1, isbn);
                ps.setString(2, title);
                ps.setString(3, author);
                ps.setString(4, category);
                ps.setString(5, quantity);
                ps.setFloat(6, price);
                ps.setString(7, "yes");
                
                int status = ps.executeUpdate();
                
                if(status > 0)
                {
                    out.println("<h1> Sucessfully ADDED New Book  </h1>");
                    response.sendRedirect("./Browse");
                }      
                
                
            }
            catch(Exception e)
            {
                out.println("<h1 style='color: transparent;'> An Error Occured " + e + "</h1>");
            }
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
	doGet(request,response);
    }
}