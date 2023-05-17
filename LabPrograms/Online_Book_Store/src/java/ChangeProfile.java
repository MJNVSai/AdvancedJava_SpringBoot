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

public class ChangeProfile extends HttpServlet
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
	    out.print("<h1 style='font-style: cursive; font-family: sans-serif;'> <strong> Change Profile <strong> </h1>");
            
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "");
                
                String cq = "select * from register where email = ?";
                PreparedStatement ps = con.prepareStatement(cq);
                ps.setString(1, email);
                
                ResultSet rs = ps.executeQuery();
                
                out.print("<head>");
                out.print("    <title> Register Tab </title>");
                out.print("    <link rel='stylesheet' href='styles.css' >"); 
                out.print("    <link rel = 'stylesheet' href = 'https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css' >");
                out.print("</head>");
                
                out.print("<body>");
                out.print("<div class='glass' style='margin-top:0;'>" + 
                        "<h3> Profile </h3>");
                out.print("");
                
                
                while(rs.next())
                {
                    out.print("<form action='UpdateProfile' method='post'>" + 
                            "<div class='inputWithIcon inputIconBg'>" + 
                            "<input type='text' name = 'p1' placeholder='Enter Your Full Name' value='" +rs.getString(1)+ "'>" +
                            "<i class='fa fa-user fa-lg fa-fw' aria-hidden='true'></i>" +
                            "</div>");
                    out.print("");
                    
                    out.print("<div class='inputWithIcon inputIconBg'>" +
                            "<input type='text' name = 'p2' placeholder='Enter Your Email' value='" +rs.getString(2)+ "' readonly>" +
                            "<i class='fa fa-envelope fa-lg fa-fw' aria-hidden='true'></i>" + 
                            "</div>");
                    out.print("");
                    
                    out.print("<div class='inputWithIcon inputIconBg'>" + 
                            "<input type='password' name = 'p3' placeholder='Enter Your Password' value='" +rs.getString(3)+ "'>" + 
                            "<i class='fa fa-key fa-lg fa-fw' aria-hidden='true'></i>" + 
                            "</div>");
                    out.print("");
                    
                    out.print("<div class='inputWithIcon inputIconBg'>" +
                            "<input type='password' name = 'p4' placeholder='Enter Confirm Password' value='" +rs.getString(4)+ "'>" +
                            "<i class='fa fa-key fa-lg fa-fw' aria-hidden='true'></i>" + 
                            "</div>");
                    out.print("");
                    
//                    switch (rs.getString(5)) 
//                    {
//                        case "Male":
//                            
//                            out.print(" <div class='rgn'>" + "<span class='gname'> Gender: </span>" + "<div class='radio-inputs'>" + "<label class='radio'>" + "<input type='radio' name='radio' checked=''>" + "<span class='name'> Male </span>" + "</label>" + "<label class='radio'>" + "<input type='radio' name='radio'>" + "<span class='name'> Female </span>" + "</label>" + "<label class='radio'>" + "<input type='radio' name='radio'>" + "<span class='name'> Others </span>" + "</label>" + "</div>" + "</div>");
//                            
//                            break;
//                        case "Female":
//                            
//                            out.print(" <div class='rgn'>" + "<span class='gname'> Gender: </span>" + "<div class='radio-inputs'>" + "<label class='radio'>" + "<input type='radio' name='radio' checked=''>" + "<span class='name'> Male </span>" + "</label>" + "<label class='radio' checked=''>" + "<input type='radio' name='radio'>" + "<span class='name'> Female </span>" + "</label>" + "<label class='radio'>" + "<input type='radio' name='radio'>" + "<span class='name'> Others </span>" + "</label>" + "</div>" + "</div>");
//                            
//                            break;
//                        case "Others":
//                            
//                            out.print(" <div class='rgn'>" + "<span class='gname'> Gender: </span>" + "<div class='radio-inputs'>" + "<label class='radio'>" + "<input type='radio' name='radio' checked=''>" + "<span class='name'> Male </span>" + "</label>" + "<label class='radio'>" + "<input type='radio' name='radio'>" + "<span class='name'> Female </span>" + "</label>" + "<label class='radio' checked=''>" + "<input type='radio' name='radio'>" + "<span class='name'> Others </span>" + "</label>" + "</div>" + "</div>");
//                            
//                            break;
//                        default:
//                            break;
//                    }
                    
                    out.print("");
                    out.print("<div class='rgn'>" + "<span class='gname'> Date Of Birth : </span>" + "<input type='date' name = 'p5' id='exampleInputDOB1' placeholder='Date of Birth' value='" +rs.getString(6)+ "' />" + "</div>");
                    out.print("");
                    
                    out.print("<div class='inputWithIcon inputIconBg'>" + 
                            "<input type='text' name = 'p6' placeholder='Enter Your City Name' value='" +rs.getString(7)+ "'>" +
                            "<i class='fa fa-building fa-lg fa-fw' aria-hidden='true'></i>" +
                            "</div>");
                    out.print("");
                    
                    out.print("<div class='inputWithIcon inputIconBg'>" + 
                            "<input type='text' name = 'p7' placeholder='Enter Your Country Name' value='" +rs.getString(8)+ "'>" + 
                            "<i class='fa fa-globe fa-lg fa-fw' aria-hidden='true'></i>" + 
                            "</div>");
                    out.print("");
                    
                    out.print("<div class='rgn'>" + 
                            "<span class='gname'> Address </span>" + 
                            "<textarea id='w3review' name='p8' rows='2' cols='50'>" +rs.getString(9)+ "</textarea>" + 
                            "</div>");
                    out.print("");
                    
                    out.print("<p> <button type='submit' style='margin-bottom:10px;'> Update </button> </p>");
                    
                    out.print("        </form>");
                    out.print("    </div> <br> <br> <br> <br>");
                    out.print("</body>");
                }
                
            }
            catch(Exception e)
            {
                out.println("<h1> An Error Occured " + e + "</h1>");
            }
        }
    }
}