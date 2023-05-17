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

public class DeleteOrder extends HttpServlet
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
	    out.print("<h1 style='font-style: cursive; font-family: sans-serif;'> <strong> Cancellation Sucessfull <strong> </h1>");
            
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "");
                
                String qu1 = "select * from book";
                PreparedStatement ps = con.prepareStatement(qu1);
                
                ResultSet rs = ps.executeQuery();
                
                out.print("<head>");
                out.print("<title> Books Cart </title>");
                out.print("<link rel = \"stylesheet\" href = \"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css\" >");
                out.print("<style>");
                out.print("body {");
                out.print("font-family: sans-serif;");
                out.print("}");
                out.print("");
                out.print("th, td {");
                out.print("border: 3px solid red;");
                out.print("padding:5px;");
                out.print("text-align:center;");
                out.print("}");
                out.print("");
                out.print("table {");
                out.print("border-collapse: collapse;");
                out.print("border: 1px solid violet;");
                out.print("}");
                out.print("");
                out.print("thead th {");
                out.print("width: 30%;");
                out.print("}");
                out.print("");
                out.print(".cell-highlight {");
                out.print("background-color: gold;");
                out.print("font-weight: bold;");
                out.print("}");
                out.print("");
                out.print("caption {");
                out.print("font-weight: bold;");
                out.print("font-size: 24px;");
                out.print("text-align: left;");
                out.print("color: #333;");
                out.print("margin-bottom: 16px;");
                out.print("}");
                out.print("");
                out.print("thead {");
                out.print("background-color: #339;");
                out.print("color: white;");
                out.print("font-size: 0.875rem;");
                out.print("'	text-transform: uppercase;'");
                out.print("'	letter-spacing: 3px;'");
                out.print("}");
                out.print("");
                out.print("tbody tr:nth-child(odd) {");
                out.print("background-color: #fff;");
                out.print("}");
                out.print("");
                out.print("tbody tr:nth-child(even) {");
                out.print("background-color: #eee;");
                out.print("}");
                out.print("");
                out.print("tbody th {");
                out.print("background-color: #36c;");
                out.print("color: #fff;");
                out.print("text-align: center");
                out.print("}");
                out.print("");
                out.print("tbody tr:nth-child(even) th {");
                out.print("background-color: #25c;");
                out.print("}");
                out.print("");
                out.print(".cen{");
                out.print("margin-top:25px;");
                out.print("}");
                out.print("");
                out.print(".anchor\n" +
"		{\n" +
"			\n" +
"			  padding-top: 8px; padding-bottom: 8px; padding-left:10px; padding-right:10px;");
                
                out.print("margin-left: 40px;	/* 20px */\n" +
"		    border-radius: 9px; /* 520px */\n" +
"		    border-color: blue;\n" +
"		    border-style: double;\n" +
"		    border-width: 2px;	/* 5px */\n" +
"\n" +
"		    color: crimson;\n" +
"		    text-decoration: none;\n" +
"		    letter-spacing: 2px;\n" +
"   		}\n" +
"\n" +
"		.anchor:hover\n" +
"		{\n" +
"			color: white;\n" +
"			background-color: indianred;\n" +
"			/*transition-delay: 0.7s;*/\n" +
"		}");
                
                out.print("input{\n" +
"    width: 25%;\n" +
"    border: 2px solid #aaa;\n" +
"    border-radius: 4px;\n" +
"    margin: 8px 0;\n" +
"    outline: none;\n" +
"    padding: 8px;\n" +
"    box-sizing: border-box;\n" +
"    transition: 0.3s;\n" +
"  }\n" +
"  \n" +
"  input:focus {\n" +
"    border-color: #834d9b;\n" +
"    box-shadow: 0 0 8px 0 #834d9b;\n" +
"  }");
                
                out.print("</style>");
                out.print("</head>");
                out.print("<body>");
                out.print("<center class='cen'>");
                out.print("<table>");
                out.print("<caption> <center>  </center> </caption>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th> ISBN </th>");
                out.print("<th> Book Name </th>");
                out.print("<th> Quantity </th>");
                out.print("<th> Price </th>");
                out.print("<tr>");
                out.print("</thead>");
                
                
                
                while(rs.next())
                {
                    out.print("<tbody>");
                    out.print("<tr>");
                    out.print("<td>" +rs.getString(1)+ "</td>");
                    out.print("<td>" +rs.getString(2)+ "</td>");
                    out.print("<td>" +rs.getString(3)+ "</td>");
                    out.print("<td>" +rs.getFloat(4)+ "</td>");
                    
                    out.print("</tr>");
                    out.print("</tbody>");
                }
                out.print("</table>");
                
                
                
                out.print("<br> <br>");
                out.print("<div style='width:40%;'>");
                
                out.print("<a href=\"Home\" class=\"anchor\" style='float:left;'> Back </a>");
                out.print("</div>");
                
                out.print("</center>");
                
              
                
                out.print("</body>");
                
                con.close();
                
                
            }
            catch(Exception e)
            {
                out.println("<h1> An Error Occured " + e + "</h1>");
            }
            
        }
    }
}