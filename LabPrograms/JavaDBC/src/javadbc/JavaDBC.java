/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbc;
import java.io.*;
import java.sql.*;

/**
 *
 * @author SHREE
 */
public class JavaDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
            // E:\venkat sai\WebDev_Program\JDBC.accdb
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://E:\\venkat sai\\WebDev_Program\\JDBC.accdb");
            Statement st = con.createStatement();
            
            // Insert Query;
            String sqlinsert = "insert into Employee values(68, 'A. Charan', 208456732, 95000)";
            //st.executeUpdate(sqlinsert);
            
            // update Query
            String sqlupdate = "update Employee set salary = 70000 where ID = 11";
            //st.executeUpdate(sqlupdate);
            
            // Delete record Query
            String sqldelete = "delete from Employee where id = 4";
            //st.executeUpdate(sqldelete);
            
            // Create Query
            String sqlcreate = "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))";
            //st.executeUpdate(sqlcreate);
            
            // Select Query
            ResultSet rs = st.executeQuery("select * from Employee");
            while(rs.next())
            {

                System.out.println("Employee ID Number is: " + rs.getInt(1));
                System.out.println("Employee name is: " + rs.getString(2));
                System.out.println("Employee Mobile Number : " + rs.getInt(3));
                System.out.println("Employee Salary : " + rs.getInt(4));
                System.out.println();
            }
        }
        catch(Exception e)
        {
            System.out.println("An unknown Exception occured : \n\n" + e);
        }
    }
    
}
